package tn.enicar.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.CategorySubscription;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.repository.ICategorySubscriptionRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.services.interfaces.ICategorySubscriptionService;
@Service
public class CategorySubscriptionServiceImpl implements ICategorySubscriptionService {

	@Autowired
	IKinderGartenRepository kinderRepo;
	@Autowired
	ICategorySubscriptionRepository iCategorySubscriptionRepository;
	
	
	@Override
	public int addCategorySubscription(CategorySubscription categorySubscription,int idk) {
		categorySubscription.setKinderGarten(kinderRepo.findById(idk).orElse(null));
		iCategorySubscriptionRepository.save(categorySubscription);
		return categorySubscription.getId();
	}

	@Override
	public void updateCategorySubscription(String description, double price, int categorySubscriptionId) {
		iCategorySubscriptionRepository.updateupdateCategorySubscriptionJPQL(description, price, categorySubscriptionId);
		
	}

	@Override
	public List<CategorySubscription> getAllCategorySubscription() {
		return (List<CategorySubscription>) iCategorySubscriptionRepository.findAll();
	}

	@Override
	public void deleteCategorySubscriptionById(int categorySubscriptionId) {
		CategorySubscription categorySubscription = iCategorySubscriptionRepository.findById(categorySubscriptionId).get();
		iCategorySubscriptionRepository.delete(categorySubscription);
		
	}

	@Override
	public CategorySubscription getCategorySubscriptionById(int categorySubscriptionId) {
		return iCategorySubscriptionRepository.findById(categorySubscriptionId).get();
	}

	@Override
	public void affecterCategorySubscriptionAkinderGarten(int categorySubscriptionId, int kinderId) {
		KinderGarten kinderManagedEntity = kinderRepo.findById(kinderId).get();
		CategorySubscription categorySubscriptionManagedEntity = iCategorySubscriptionRepository.findById(categorySubscriptionId).get();
		
		categorySubscriptionManagedEntity.setKinderGarten(kinderManagedEntity);
		iCategorySubscriptionRepository.save(categorySubscriptionManagedEntity);

		
	}

	@Override
	public List<CategorySubscription> findAllCategorySubscriptionByKinderGarten(int kinderId) {
		return iCategorySubscriptionRepository.findAllCategorySubscriptionByKinderGartenJPQL(kinderId);
	}

}
