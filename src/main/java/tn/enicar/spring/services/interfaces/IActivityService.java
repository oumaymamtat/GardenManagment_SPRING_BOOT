package tn.enicar.spring.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.Activity;

public interface IActivityService {
	public int addActivity(Activity activity,int kinderId);
	public void updateActivity(String description,String photo,int activityId);
	public List<Activity> getAllactivity();
	public void deleteActivityById(int activityId);
	public Activity getActivityById(int activityaId);
	public void affecterActivityAkinderGarten(int activityId, int kinderId);
	public List<Activity> findAllActivityByKinderGarten(int kinderId) ;
	public void deleteAllActivity(int kinderId);
	public void assignPhoto(int id , MultipartFile file);
}
