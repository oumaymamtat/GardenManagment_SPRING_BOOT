package tn.enicar.spring.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.KinderGarten;

import tn.enicar.spring.entity.SubscriptionChild;
import tn.enicar.spring.repository.IChildRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.repository.ISubscriptionChildRepository;
import tn.enicar.spring.services.interfaces.IChildService;
@Service
public class ChildServiceImpl implements IChildService {

	@Autowired
	IChildRepository childRepository;
	@Autowired
	ISubscriptionChildRepository subscriptionChildRepository;
	
	@Override
	public void addChild(Child child) {
		
		 childRepository.save(child);
	}
	@Override
	public List<Child> getAllChild() {
		return (List<Child>) childRepository.findAll();
	}
	@Override
	public void updateChild(Child child) {
		childRepository.save(child);
		
	}
	@Override
	public void assignPictureToChild(int id, MultipartFile file) {
		Child c = childRepository.findById(id).orElse(null);
		
		if(c!=null){
			c.setPicture(file.getOriginalFilename());
			this.updateChild(c);
		}
		
	}
	
		
	

	
}
