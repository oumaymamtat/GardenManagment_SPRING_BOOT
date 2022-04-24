package tn.enicar.spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.User;

@Repository
public interface IKinderGartenRepository extends CrudRepository<KinderGarten, Integer> {
	

	
	@Modifying
	@Transactional
	@Query("update KinderGarten e set e.name = :name,e.latitude=:latitude,e.longitude=:longitude ,e.adress = :adress,e.email = :email,e.tel = :tel,e.logo = :logo  where e.id = :kinderId")
	public void updateKindergartenJPQL(@Param("name") String name, @Param("adress") String adress,
			@Param("email") String email, @Param("tel") int tel, @Param("logo") String logo,
			@Param("latitude") double latitude,@Param("longitude") double longitude,
			@Param("kinderId") int kinderId);
	

	@Query(value="select * from kinder_garten order by score_eval desc",nativeQuery=true)

	public List<KinderGarten> TriKinderGartenByScoreEval();
	
	
	
/*	@Query("Select k from KinderGarten k " + "where k.adress=:adress  ")
	public List<KinderGarten> getKinderGartenByAdress(@Param("adress") String adress);
*/
	public List<KinderGarten> findByAdress(@Param("adress") String adress);
	public List<KinderGarten> findAll();

	@Query(value="Select * from Kinder_Garten where responsible_id=:responsible",nativeQuery=true)
	public List<KinderGarten> getKinderGartenByResponsible(@Param("responsible") int responsible);
	
	@Query(value="select * from Kinder_Garten where responsible_id=:responsible",nativeQuery=true)
	public KinderGarten findUserByIdK(@Param("responsible") int responsible);
	
}
