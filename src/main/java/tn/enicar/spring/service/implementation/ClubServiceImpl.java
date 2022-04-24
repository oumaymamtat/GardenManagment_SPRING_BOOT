package tn.enicar.spring.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.Category;
import tn.enicar.spring.entity.Club;
import tn.enicar.spring.entity.Extra;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.repository.ICategoryRepository;
import tn.enicar.spring.repository.IClubRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.services.interfaces.IClubService;
@Service
public class ClubServiceImpl implements IClubService {
	@Autowired
	IKinderGartenRepository kinderRepo;
	@Autowired
	IClubRepository iClubRepository;
	@Autowired
	ICategoryRepository iCategoryRepository;
	
	@Override
	public void addClub(Club club,int id) {
		club.setCategory(iCategoryRepository.findById(id).orElse(null));
		iClubRepository.save(club);
	}

	@Override
	public void updateClub(String description, int clubId) {
		iClubRepository.updateClubJPQL(description, clubId);
		
	}

	@Override
	public List<Club> getAllclub() {
		return (List<Club>) iClubRepository.findAll();
	}

	@Override
	public void deleteClubById(int clubId) {
		Club club = iClubRepository.findById(clubId).get();
		iClubRepository.delete(club);
	}

	@Override
	public Club getClubById(int clubId) {
		return iClubRepository.findById(clubId).get();

	}

	@Override
	public void affecterClubACategory(int clubId, int categoryId) {
		Category categoryManagedEntity = iCategoryRepository.findById(categoryId).get();
		Club clubManagedEntity = iClubRepository.findById(clubId).get();
		
		clubManagedEntity.setCategory(categoryManagedEntity);
		iClubRepository.save(clubManagedEntity);
		
	}



}
