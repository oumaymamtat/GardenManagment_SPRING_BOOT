package tn.enicar.spring.service.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.SessionVote;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.Vote;
import tn.enicar.spring.entity.VoteForm;
import tn.enicar.spring.repository.ISessionVoteRepository;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.repository.IVoteRepository;
import tn.enicar.spring.services.interfaces.IVoteService;

@Service
public class VoteService implements IVoteService {
	private static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	IVoteRepository iVoteRepository;
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	ISessionVoteRepository iSessionVoteRepository;

	public int addVote(int kindergartenId, VoteForm voteform, int idsession) {
		Date date = new Date();
		SessionVote s = iSessionVoteRepository.findById(idsession).orElse(null);
		User voter = iUserRepository.findById(voteform.getVoter()).orElse(null);
		User votedFor = iUserRepository.findById(voteform.getVotedFor()).orElse(null);
		if (voter.getKinderGartenInscription().getId() == votedFor.getKinderGartenInscription().getId()) {
			if (voter.getKinderGartenInscription().getId() == kindergartenId) {
				User u = iUserRepository.findById(voteform.getVotedFor()).orElse(null);

				Vote vote = new Vote(0, voter, votedFor, date);
				if (vote.getDateVote().before(s.getDateEnd()) && vote.getDateVote().after(s.getDateStart())) {

					int id = iVoteRepository.save(vote).getId();
					vote.setSessionVote(s);
					u.IncrementScoreDelegate();
					iUserRepository.save(u);

				return id;
				}else{
					log.info("session has been terminated");
				}

			}
		}
		return 0;

	}
}
