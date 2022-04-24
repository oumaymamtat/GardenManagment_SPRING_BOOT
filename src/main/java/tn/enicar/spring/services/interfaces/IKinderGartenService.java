package tn.enicar.spring.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.User;


public interface IKinderGartenService {

	public int addKindergarten(KinderGarten kendergarten);
	public void updateKindergarten(String name,String adress,String email,int tel,String logo,double latitude,double longitude,int kinderId);
	public List<KinderGarten> getAllkinder();
	public void deleteKindergartenById(int kenderId);
	public KinderGarten getKindergartenById(int kinderId);
	public User delegatorsElection(int kindergartenId);
	public List<User> listDelegators(int kindergartenId);
	public void recupComptes(int idUser, int kinderId);
	public void BannedUser(int id,int kinderId);
	public void assignLogo(int id , MultipartFile file);
	public List<KinderGarten> TriKinderGartenByScoreEval();
	public List<KinderGarten> getKindergartenByResponsible(int responsibleId);
	
	public KinderGarten findUserByIdK(int responsible) ;
	
}

