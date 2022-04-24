package tn.enicar.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.Category;
import tn.enicar.spring.entity.Event;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.repository.ICategoryRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.services.interfaces.ICategoryService;
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	ICategoryRepository iCategoryRepository;
	@Autowired
	IKinderGartenRepository kinderRepo;
	
	@Override
	public int addCategory(Category category,int idk) {
		category.setKinderGarten(kinderRepo.findById(idk).orElse(null));
		iCategoryRepository.save(category);
		return category.getId();
	}

	@Override
	public void updateCategory(String description, int categoryId) {
		iCategoryRepository.updateCategoryJPQL(description, categoryId);
		
	}

	@Override
	public List<Category> getAllcategory() {
		return (List<Category>) iCategoryRepository.findAll();

	}

	@Override
	public void deleteCategoryById(int categoryId) {
		Category category = iCategoryRepository.findById(categoryId).get();
		iCategoryRepository.delete(category);
		
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return iCategoryRepository.findById(categoryId).get();
	}
	@Override
	public void affecterCategoryAkinderGarten(int categoryId, int kinderId) {
		KinderGarten kinderManagedEntity = kinderRepo.findById(kinderId).get();
		Category categoryManagedEntity = iCategoryRepository.findById(categoryId).get();
		
		categoryManagedEntity.setKinderGarten(kinderManagedEntity);
		iCategoryRepository.save(categoryManagedEntity);		
	}

	@Override
	public List<Category> findAllCategoryByKinderGarten(int kinderId) {
		return iCategoryRepository.findAllCategoryByKinderGartenJPQL(kinderId);
	}

}
