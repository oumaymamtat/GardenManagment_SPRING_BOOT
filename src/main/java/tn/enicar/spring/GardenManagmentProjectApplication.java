package tn.enicar.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tn.enicar.spring.services.interfaces.IUserService;



@SpringBootApplication


public class GardenManagmentProjectApplication implements CommandLineRunner{

	
	@Autowired
	IUserService users;
	
	public static void main(String[] args) {
		SpringApplication.run(GardenManagmentProjectApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		// initialize
		users.initialize();

	}

}
