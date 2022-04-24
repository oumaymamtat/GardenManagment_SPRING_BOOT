package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.enicar.spring.entity.enumeration.RoleSwitch;

@Entity
public class SwitchAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dateC;

	private String state;
	
	
	private RoleSwitch roleswitch;
	
	@OneToOne
	private User visitor;
	
	
	
	public RoleSwitch getRoleswitch() {
		return roleswitch;
	}

	public void setRoleswitch(RoleSwitch roleswitch) {
		this.roleswitch = roleswitch;
	}


	

	public User getVisitor() {
		return visitor;
	}

	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}

	
	
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
