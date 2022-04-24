package tn.enicar.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.Event;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer>  {

	@Modifying
	@Transactional
	@Query("update Event e set e.description = :description ,e.object = :object,e.date = :date , e.price= :price  where e.id = :eventId")
	public void updateEventJPQL(@Param("object") String object,@Param("description") String description,@Param("date") Date date,@Param("price") double price,@Param("eventId") int eventId);


	@Query("SELECT e from Event e where e.date = CURRENT_DATE()")
	 public List<Event> getAllEventPourToday();
	
	
	@Query(value="SELECT * from Event where price <= :price",nativeQuery=true)
	 public List<Event> getAllEventbyprice(@Param("price") int price);
	
	// methode of statistique get all category by event for each kindergarden
	@Query(value="select c.description,count(e.id) count from event e , category c  where e.category_id=c.id  and c.kinder_garten_id=:kinderId  group by (c.description)",nativeQuery=true)
	public List<String> getStatistiqueEventBykindergarten(@Param("kinderId")int kinderId);
	
	@Query(value="select COUNT(*) from event e ,category c where c.kinder_garten_id=:kinderId and e.`category_id`=c.id",nativeQuery=true)
	public int  getCountOfEventByKinderGarten(@Param("kinderId")int kinderId);
	
	
	
}
