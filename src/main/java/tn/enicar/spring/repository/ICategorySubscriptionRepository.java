package tn.enicar.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.CategorySubscription;


@Repository
public interface ICategorySubscriptionRepository extends CrudRepository<CategorySubscription, Integer>  {

	@Modifying
	@Transactional
	@Query("update CategorySubscription e set e.description = :description ,e.price = :price  where e.id = :categorySubscriptionId")
	public void updateupdateCategorySubscriptionJPQL(@Param("description") String description,@Param("price") double price,@Param("categorySubscriptionId") int categorySubscriptionId);

	@Query(value="select * from Category_Subscription where kinder_garten_id=:kinderId",nativeQuery=true)
	public List<CategorySubscription> findAllCategorySubscriptionByKinderGartenJPQL(@Param("kinderId")int kinderId);
}