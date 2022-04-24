package tn.enicar.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicar.spring.entity.SwitchAccount;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.services.interfaces.IUserService;

@RestController
@RequestMapping("/useradmin")
@PreAuthorize("hasRole('ROLE_admin') or hasRole ('ROLE_adminGarten')")

public class UserController {
 
	
	private static Logger log = LoggerFactory.getLogger(UserResourceImpl.class);
	 
	@Autowired
	private IUserService userS;

	
	@PostMapping("/AddUser")
	@ResponseBody
	public void addUser(@RequestBody User u)
	{
		userS.add(u);
	}
	
	
	@GetMapping("/findAll")
	public List<User>findall(){
		
		
		log.info("UserResourceImpl : findaAllUser");
		
		return  userS.getAll();
	}
	
	
	
	
	
	
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		
		 userS.delete(id);
		
	}
	
	@PutMapping("/update")
	public void update(@RequestBody User user) {
		
		userS.update(user);
	}
	
	
	@PutMapping("/ChangeStateUser/{id}")
    @ResponseBody
			
	public String ChangeStateUser(@PathVariable("id") int id)
	{
		
		userS.ChangeStateUser(id);
		
		return "User state changed  sucessfully !!!";
		
		
	}
	
	@PutMapping("/blockAccount/{id}")
    @ResponseBody
			
	public String blockAccount(@PathVariable("id") int id)
	{
		
		userS.blockAccount(id);
		
		return "account locked !!";
	}
	

	
	
	

	@GetMapping("/getParentsByKinderGarten")

	
	 public List<User> parentsByKinderGarten()
	{
		return userS.getParentsByKinderGarten();
		
	}

	
}
