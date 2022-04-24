package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Vote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private User voter;
	@ManyToOne
	private User votedFor;
	@Temporal(TemporalType.DATE)
	private Date dateVote;

	@ManyToOne
	private SessionVote sessionVote;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getVoter() {
		return voter;
	}

	public void setVoter(User voter) {
		this.voter = voter;
	}

	public User getVotedFor() {
		return votedFor;
	}

	public void setVotedFor(User votedFor) {
		this.votedFor = votedFor;
	}

	public Date getDateVote() {
		return dateVote;
	}

	public void setDateVote(Date dateVote) {
		this.dateVote = dateVote;
	}

	public Vote(int id, User voter, User votedFor ,Date dateVote) {
		super();
		this.id = id;
		this.voter = voter;
		this.votedFor = votedFor;
		this.dateVote = dateVote;
		}

	public SessionVote getSessionVote() {
		return sessionVote;
	}

	public void setSessionVote(SessionVote sessionVote) {
		this.sessionVote = sessionVote;
	}


}
