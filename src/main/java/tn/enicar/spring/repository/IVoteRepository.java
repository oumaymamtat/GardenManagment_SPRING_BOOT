package tn.enicar.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.Vote;

@Repository
public interface IVoteRepository extends CrudRepository<Vote, Integer>{
	
	/*@Query(value = "select * from vote v,session_vote s "
			+ "where v.session_vote_id = s.id "
			+ "and v.date_vote between s.date_start and s.date_end", nativeQuery = true)
	public boolean testDate();*/
}
