package tn.enicar.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.Extra;



@Repository
public interface IExtraRepository extends CrudRepository<Extra, Integer>  {

	
	@Modifying
	@Transactional
	@Query("update Extra e set e.description = :description ,e.price = :price  where e.id = :ExtraId")
	public void updateExtraJPQL(@Param("description") String description,@Param("price") double price,@Param("ExtraId") int ExtraId);


	@Query(value="select * from Extra where kinder_garten_id=:kinderId",nativeQuery=true)
	public List<Extra> findAllExtraByKinderGartenJPQL(@Param("kinderId")int kinderId);
}

