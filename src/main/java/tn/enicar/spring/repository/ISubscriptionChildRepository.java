package tn.enicar.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.spring.entity.SubscriptionChild;
@Repository
public interface ISubscriptionChildRepository extends CrudRepository<SubscriptionChild, Integer>  {
	

}
