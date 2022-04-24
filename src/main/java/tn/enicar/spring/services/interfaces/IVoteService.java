package tn.enicar.spring.services.interfaces;

import tn.enicar.spring.entity.VoteForm;

public interface IVoteService {

	public int addVote(int kindergartenId, VoteForm voteform ,int idsession);
}
