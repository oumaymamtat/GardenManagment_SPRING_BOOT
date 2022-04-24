package tn.enicar.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.spring.entity.Child;
@Repository
public interface IChildRepository extends CrudRepository<Child, Integer>  {

	
}
