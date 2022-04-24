package tn.enicar.spring.entity;

public class VoteForm {

	private int id;
	private int voter;
	private int votedFor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVoter() {
		return voter;
	}
	public void setVoter(int voter) {
		this.voter = voter;
	}
	public int getVotedFor() {
		return votedFor;
	}
	public void setVotedFor(int votedFor) {
		this.votedFor = votedFor;
	}
	
	
	
}
