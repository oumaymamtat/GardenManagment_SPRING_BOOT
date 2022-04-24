package tn.enicar.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.repository.IActivityRepository;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.services.interfaces.IActivityService;

@Service
public class ActivityServiceImpl implements IActivityService {
	@Autowired
	IActivityRepository iActivityRepository;
	@Autowired
	IKinderGartenRepository kinderRepo;
	
	@Override
	public int addActivity(Activity activity,int idKinder) {
		activity.setKinderGarten(kinderRepo.findById(idKinder).orElse(null));
		int id = iActivityRepository.save(activity).getId();
		return id;
	}

	public int updateActivity(Activity activity) {
		iActivityRepository.save(activity);
		return activity.getId();
	}
	@Override
	public void updateActivity(String description, String photo, int activityId) {
		iActivityRepository.updateActivityJPQL(description, photo, activityId);

		
	}

	@Override
	public List<Activity> getAllactivity() {
		return (List<Activity>) iActivityRepository.findAll();
	}

	@Override
	public void deleteActivityById(int activityId) {
		Activity activity = iActivityRepository.findById(activityId).get();
		iActivityRepository.delete(activity);
		
	}

	@Override
	public Activity getActivityById(int activityaId) {
		return iActivityRepository.findById(activityaId).get();
	}

	@Override
	public void affecterActivityAkinderGarten(int activityId, int kinderId) {
		KinderGarten kinderManagedEntity = kinderRepo.findById(kinderId).get();
		Activity activityManagedEntity = iActivityRepository.findById(activityId).get();
		
		activityManagedEntity.setKinderGarten(kinderManagedEntity);
		iActivityRepository.save(activityManagedEntity);
		
	}

	@Override
	public List<Activity> findAllActivityByKinderGarten(int kinderId) {
		return iActivityRepository.findAllActivityByKinderGartenJPQL(kinderId);
	}

	@Override
	public void deleteAllActivity(int kinderId) {
		iActivityRepository.deleteAllActivityJPQL(kinderId);	
	}

	@Override
	public void assignPhoto(int id, MultipartFile file) {
		Activity a = iActivityRepository.findById(id).orElse(null);
		if (a!=null){
			a.setPhoto(file.getOriginalFilename());
			this.updateActivity(a);
		}
	}
	
}
