package tn.enicar.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.Notification;
@Repository
public interface INotification extends CrudRepository<Notification, Integer> {
	
@Query(value="select * from notification",nativeQuery=true)
public List<Notification> getAllNotification();
}
