package tn.enicar.spring.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.Extra;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.Notification;
import tn.enicar.spring.repository.IExtraRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.repository.INotification;
import tn.enicar.spring.services.interfaces.IExtraService;
@Service
public class ExtraServiceImpl implements IExtraService {

	@Autowired
	IKinderGartenRepository kinderRepo;
	@Autowired
	IExtraRepository iExtraRepository;
	@Autowired
	INotification inotification;
	
	@Override
	public int addExtra(Extra extra,int idk) {
		extra.setKinderGarten(kinderRepo.findById(idk).orElse(null));
		iExtraRepository.save(extra);
		
		return extra.getId();
	}

	@Override
	public void updateExtra(String description, double price,int ExtraId) {
		iExtraRepository.updateExtraJPQL(description, price, ExtraId);
		
	}

	@Override
	public List<Extra> getAllextra() {
		return (List<Extra>) iExtraRepository.findAll();
	}

	@Override
	public void deleteExtraById(int extraId) {
		Extra extra = iExtraRepository.findById(extraId).get();
		iExtraRepository.delete(extra);
		
	}

	@Override
	public Extra getExtraById(int extraId) {
		return iExtraRepository.findById(extraId).get();
	}
	
	@Override
	public void affecterExtraAkinderGarten(int extraId, int kinderId) {
				KinderGarten kinderManagedEntity = kinderRepo.findById(kinderId).get();
				Extra extraManagedEntity = iExtraRepository.findById(extraId).get();
				
				extraManagedEntity.setKinderGarten(kinderManagedEntity);
				iExtraRepository.save(extraManagedEntity);
		
	}

	@Override
	public List<Extra> findAllExtraByKinderGarten(int kinderId) {
		return iExtraRepository.findAllExtraByKinderGartenJPQL(kinderId);
	}
  
}
