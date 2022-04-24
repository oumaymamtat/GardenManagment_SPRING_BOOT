package tn.enicar.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.Activity;

@Repository
public interface IActivityRepository extends CrudRepository<Activity, Integer>  {


	@Modifying
	@Transactional
	@Query("update Activity e set e.description = :description ,e.photo = :photo  where e.id = :activityId")
	public void updateActivityJPQL(@Param("description") String description,@Param("photo") String photo,@Param("activityId") int activityId);
	
	@Query(value="select * from activity where kinder_garten_id=:kinderId",nativeQuery=true)
	public List<Activity> findAllActivityByKinderGartenJPQL(@Param("kinderId")int kinderId);

	 @Modifying
	 @Transactional
	 @Query(value="DELETE from activity where kinder_garten_id=:kinderId",nativeQuery=true)
	 public void deleteAllActivityJPQL(@Param("kinderId")int kinderId);

}
