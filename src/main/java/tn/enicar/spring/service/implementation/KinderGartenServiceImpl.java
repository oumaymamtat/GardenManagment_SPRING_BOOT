package tn.enicar.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.enumeration.StateUser;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.services.interfaces.IKinderGartenService;

@Service
public class KinderGartenServiceImpl implements IKinderGartenService {


	@Autowired
	IKinderGartenRepository iKinderGartenRepository;
	@Autowired
	IUserRepository iUserRepository;

	@Override
	public int addKindergarten(KinderGarten kendergarten) {
		//kendergarten.setLongitude(60);
		//kendergarten.setLatitude(30);
		kendergarten.setScoreEval(0);
		iKinderGartenRepository.save(kendergarten);
		return kendergarten.getId();
	}

	@Override
	public void updateKindergarten(String name, String adress, String email, int tel, String logo,double latitude,double longitude, int kinderId) {
		iKinderGartenRepository.updateKindergartenJPQL(name, adress, email, tel, logo, latitude, longitude, kinderId);;
	}

	@Override
	public void deleteKindergartenById(int kenderId) {
		KinderGarten kinderGarten = iKinderGartenRepository.findById(kenderId).get();
		iKinderGartenRepository.delete(kinderGarten);

	}

	@Override
	public KinderGarten getKindergartenById(int kinderId) {
		return iKinderGartenRepository.findById(kinderId).get();
	}

	@Override
	public List<KinderGarten> getAllkinder() {
		return (List<KinderGarten>) iKinderGartenRepository.findAll();
	}

	@Override
	public User delegatorsElection(int kindergartenId) {
		User u = iUserRepository.delegatorsElection(kindergartenId);
		KinderGarten k = iKinderGartenRepository.findById(kindergartenId).orElse(null);
		k.setDelegate(u);
		iKinderGartenRepository.save(k);
		return u;

	}

	@Override
	public List<User> listDelegators(int kindergartenId) {
		return iUserRepository.listDelegators(kindergartenId);
	}

	@Override
	public void BannedUser(int id, int kinderId) {
		User u = iUserRepository.findById(id).orElse(null);
		if (u.getKinderGartenInscription().getId() == kinderId) {
			if (u.getStateUser() == StateUser.active) {
				iUserRepository.BannedUser(id);
				iUserRepository.save(u);
			}
		}
	}


	@Override
	public void recupComptes(int idUser, int kinderId) {
		User u = iUserRepository.findById(idUser).orElse(null);
		if (u.getKinderGartenInscription().getId() == kinderId) {
		if ((u.getStateUser() == StateUser.blocked) || (u.getStateUser() == StateUser.waiting)) {
			u.setStateUser(StateUser.active);
			iUserRepository.save(u);
		}
		}
	}

	public int updateKindergarten(KinderGarten kendergarten) {
		iKinderGartenRepository.save(kendergarten);
		return kendergarten.getId();
	}
	
	@Override
	public void assignLogo(int id, MultipartFile file) {
		KinderGarten k = iKinderGartenRepository.findById(id).orElse(null);
		if (k!=null){
			k.setLogo(file.getOriginalFilename());
			this.updateKindergarten(k);
		}
		
	}

	@Override
	public List<KinderGarten> TriKinderGartenByScoreEval() 
	{
		return iKinderGartenRepository.TriKinderGartenByScoreEval();
	}

	@Override
	public List<KinderGarten> getKindergartenByResponsible(int responsibleId) {
		return iKinderGartenRepository.getKinderGartenByResponsible(responsibleId);
	}
	@Override
	public KinderGarten findUserByIdK(int responsible) {
		return iKinderGartenRepository.findUserByIdK(responsible);
	}
}