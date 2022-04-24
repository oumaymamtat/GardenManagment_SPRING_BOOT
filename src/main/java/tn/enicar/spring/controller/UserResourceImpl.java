package tn.enicar.spring.controller;

import java.util.Random;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicar.spring.config.springSecurity.JwtTokenProvider;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.services.interfaces.IMailService;
import tn.enicar.spring.services.interfaces.IUserService;

@RestController
@RequestMapping("/user")
public class UserResourceImpl {

	private String code;

	private static Logger log = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserService userS;

	@Autowired
	private IMailService mailS;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody User user) {

		log.info("UserResourceImpl : authenticate");
		JSONObject jsonObject = new JSONObject();
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			
			if (authentication.isAuthenticated()) {
				String email = user.getEmail();
					
				User userauthenticated = userS.findByEmail(user.getEmail());
				
				String username = userauthenticated.getFirstName()+" " +userauthenticated.getLastName();
				jsonObject.put("username", username);
				jsonObject.put("name", authentication.getName());
				jsonObject.put("authorities", authentication.getAuthorities());
				jsonObject.put("token",
						tokenProvider.createToken(email, userRepository.findByEmail(email).getRole().toString()));
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
		}
		return null;
	}

	@PostMapping("/add")
	public void addUser(@RequestBody User user) {

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		
		userS.add(user);
	}
	
	@GetMapping("/findUser/{iduser}")
	@ResponseBody
	public User findUser (@PathVariable("iduser") int id )
	{
		return userS.finduserbyid(id);
	}
	
	@GetMapping("/findUserByEmail/{email}")
	public User findUserByMail (@PathVariable("email") String email )
	{
		return userS.findByEmail(email);
	}

	@PostMapping("/sendSecretKey/{mail}")
	public void sendSecretKey(@PathVariable("mail") String mail) {

		Random r = new Random();

		code = String.valueOf(r.nextInt(9999));

		mailS.sendSimpleMail(mail, "Code", code);

	}

	@PutMapping("/changepwd/{id}/{pwd}/{code}")
	public void changePassword(@PathVariable("id") int id, @PathVariable("pwd") String pwd,
			@PathVariable("code") String code) {

		if (this.code != null && this.code.equals(code)) {

			userS.changePassWord(id, pwd);

		}

	}
	
	@PutMapping("/update")
	public void update(@RequestBody User user) {
		
		userS.update(user);
	}

}
