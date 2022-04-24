package tn.enicar.spring.service.implementation;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.enicar.spring.controller.UserResourceImpl;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.enumeration.Role;
import tn.enicar.spring.entity.enumeration.StateUser;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.services.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	MailServiceImpl servicemail;

	@Autowired
	IUserRepository userR;

	@Autowired
	IKinderGartenRepository kinderRepo;
	


	@Override
	public void add(User u) {
		u.setDateC(new Date());
		u.setStateUser(StateUser.waiting);
		userR.save(u);

	}

	@Override
	public List<User> getAll() {

		return (List<User>) userR.findAll();
	}

	@Override
	public User findByEmail(String email) {

		return userR.findByEmail(email);
	}

	@Override
	public void initialize() {

		if (this.getAll().size() == 0) {

			User user1 = new User();
			user1.setEmail("testadmin@user.com");
			user1.setFirstName("Test User admin");
			user1.setTel(97874565);
			user1.setRole(Role.ROLE_admin);
			user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user1);

			User user2 = new User();
			user2.setEmail("testadmingarten@user.com");
			user2.setFirstName("Test User admin garten");
			user2.setTel(97874565);
			user2.setRole(Role.ROLE_adminGarten);
			user2.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user2);

			User user3 = new User();
			user3.setEmail("testagentcashier@user.com");
			user3.setFirstName("Test User agent cashier");
			user3.setTel(97874565);
			user3.setRole(Role.ROLE_agentCashier);
			user3.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user3);

			User user4 = new User();
			user4.setEmail("testfutureparent@user.com");
			user4.setFirstName("Test User parent");
			user4.setTel(97874565);
			user4.setRole(Role.ROLE_futurParent);
			user4.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user4);

			User user5 = new User();
			user5.setEmail("testdoctor@user.com");
			user5.setFirstName("Test User doctor");
			user5.setTel(97874565);
			user5.setRole(Role.ROLE_doctor);
			user5.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user5);

			User user6 = new User();
			user6.setEmail("testvisitor@user.com");
			user6.setFirstName("Test User visitor");
			user6.setTel(97874565);
			user6.setRole(Role.ROLE_visitor);
			user6.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user6);

			User user7 = new User();
			user7.setEmail("testparent@user.com");
			user7.setFirstName("Test User parent");
			user7.setTel(97874565);
			user7.setRole(Role.ROLE_parent);
			user7.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user7);
			
			
			User user8 = new User();
			user8.setEmail("testprovider@user.com");
			user8.setFirstName("Test User provider");
			user8.setTel(97874565);
			user8.setRole(Role.ROLE_provider);
			user8.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			this.add(user8);

		}

	}

	@Override
	public void ChangeStateUser(int id) {
		
		User u = userR.findById(id).get();

		if (u.getStateUser().equals(StateUser.waiting))

		{
			u.setStateUser(StateUser.active);
			
			confirmerInscriptionParMail(u);
			userR.save(u);
		}

		

	}
	
	
	@Override
	public void blockAccount(int id)
	{
		User u = userR.findById(id).get();
		
		if (u.getStateUser().equals(StateUser.active))
		{
			u.setStateUser(StateUser.blocked);
			log.info("account locked");
			sendingMailForBlockAccount(u);
			userR.save(u);
		}
		
	}

	@Override
	public void delete(int id) {

		User u = userR.findById(id).get();

		if (u != null) {

			userR.delete(u);
		}

	}

	@Override
	public void update(User u) {
		
		
		User userbyid = userR.findById(u.getId()).get();
			
		String pwd = new BCryptPasswordEncoder().encode(u.getPassword());
			
		u.setRole(userbyid.getRole());
		u.setPassword(pwd);
		u.setDateC(new Date());
		u.setStateUser(StateUser.waiting);
		userR.save(u);

	}

	@Override
	public void changePassWord(int id, String pwd) {

		User u = userR.findById(id).get();

		if (u != null) {

			u.setPassword(pwd);

			this.update(u);
		}

	}


	public void confirmerInscriptionParMail(User u) 
	{
	
		servicemail.sendSimpleMail(u.getEmail(), "Inscription confirmation",
				" Your account is active ! you can log on !");

	}
	
	

	public void sendingMailForBlockAccount(User u) 
	{
	
		servicemail.sendSimpleMail(u.getEmail(), "Account Locked !",
				" Your account is locked !");

	}


	

	@Override
	public String RegisterKinderGarten(int iduser, int  id_kg) 
	{
		
		User u = userR.findById(iduser).get();
		KinderGarten kg = kinderRepo.findById(id_kg).get();
		u.setKinderGartenInscription(kg);
		userR.save(u).getId();
		return "parent "+iduser +"added succesfully to kindergarten" +id_kg ;
		
	}

	@Override
	public User finduserbyid(int id) 
	{
	return userR.findById(id).get();
	}

	@Override
	public List<User> getParentsByKinderGarten() {

		return userR.getParentsByKinderGarten();
	}

}
