package tn.enicar.spring.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.KinderGarten;
import tn.enicar.spring.entity.SessionVote;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.repository.IKinderGartenRepository;
import tn.enicar.spring.repository.ISessionVoteRepository;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.services.interfaces.ISessionVoteService;

@Service
public class SessionVoteServiceImpl implements ISessionVoteService{
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	ISessionVoteRepository iSessionVoteRepository;
	@Autowired
	IKinderGartenRepository iKinderGartenRepository;

	@Override
	public int addSessionVote(SessionVote sessionVote) {
		iSessionVoteRepository.save(sessionVote);
		return sessionVote.getId();
	}

	@Override
	public void updateSessionVote(String winner, Date dateStart, Date dateEnd, int sessionVoteId) {
		iSessionVoteRepository.updateSessionVoteJPQL(winner, dateStart, dateEnd, sessionVoteId);
		
	}

	@Override
	public List<SessionVote> getAllSessionVote() {
		return (List<SessionVote>) iSessionVoteRepository.findAll();
	}

	@Override
	public void deleteSessionVoteById(int sessionVoteId) {
		SessionVote sessionVote = iSessionVoteRepository.findById(sessionVoteId).get();
		iSessionVoteRepository.delete(sessionVote);
	}

	@Override
	public SessionVote getSessionVoteById(int sessionVoteId) {
		return iSessionVoteRepository.findById(sessionVoteId).get();
	}
	@Override
	public User delegatorsWinner(int kindergartenId,int sessionVoteId) {
		User u = iUserRepository.delegatorsElection(kindergartenId);
		KinderGarten k = iKinderGartenRepository.findById(kindergartenId).orElse(null);
		SessionVote s = iSessionVoteRepository.findById(sessionVoteId).get();
		s.setWinner(k.getDelegate().getFirstName());
		iKinderGartenRepository.save(k);
		return u;
		
	}
}
