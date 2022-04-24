package tn.enicar.spring.services.interfaces;

import java.util.List;

import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.User;

public interface IUserService {

	public void add(User u);

	public List<User> getAll();

	public void initialize();

	public void delete(int id);

	public void update(User u);

	public void changePassWord(int id, String pwd);

	public User findByEmail(String email);

	public void ChangeStateUser(int id);
	
	public void blockAccount(int id);
	
	public void confirmerInscriptionParMail (User u);
	
	public String RegisterKinderGarten(int iduser, int  id_kg);
	

	
	public User finduserbyid(int id);
	
	 public List<User> getParentsByKinderGarten();
	

}
