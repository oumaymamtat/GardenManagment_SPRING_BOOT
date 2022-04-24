package tn.enicar.spring.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.spring.entity.SessionVote;

@Repository
public interface ISessionVoteRepository extends CrudRepository<SessionVote, Integer>{
	@Modifying
	@Transactional
	@Query("update SessionVote e set e.winner = :winner,e.dateStart = :dateStart ,e.dateEnd = :dateEnd  where e.id = :sessionVoteId")
	public void updateSessionVoteJPQL(@Param("winner") String winner,@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd,@Param("sessionVoteId") int sessionVoteId);

}
