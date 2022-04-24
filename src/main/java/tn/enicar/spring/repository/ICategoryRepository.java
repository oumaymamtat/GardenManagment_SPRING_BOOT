package tn.enicar.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.Category;
@Repository
public interface ICategoryRepository extends CrudRepository<Category, Integer>  {

	
	@Modifying
	@Transactional
	@Query("update Category e set e.description = :description where e.id = :categoryId")
	public void updateCategoryJPQL(@Param("description") String description,@Param("categoryId") int categoryId);

	@Query(value="select * from Category where kinder_garten_id=:kinderId",nativeQuery=true)
	public List<Category> findAllCategoryByKinderGartenJPQL(@Param("kinderId")int kinderId);
	
}
