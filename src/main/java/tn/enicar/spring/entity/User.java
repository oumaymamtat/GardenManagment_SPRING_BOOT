package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.enicar.spring.entity.enumeration.Role;
import tn.enicar.spring.entity.enumeration.StateUser;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private StateUser stateUser;
	private int tel;
	@Temporal(TemporalType.DATE)
	private Date dateC;
	private int scoreDelegate;
	private String email;
	private String password;

	

	

	@OneToMany(mappedBy = "parent")
	@JsonIgnore
	private List<Child> listChilds = new ArrayList<Child>();

	@OneToOne(mappedBy = "visitor")
	@JsonIgnore
	private SwitchAccount switchAccount;

	@ManyToOne
	@JsonIgnore
	private KinderGarten kinderGartenInscription;

	@OneToOne(mappedBy = "responsible")
	@JsonIgnore
	private KinderGarten kinderGartenResponsible;


	@OneToOne(mappedBy = "delegate")
	@JsonIgnore
	private KinderGarten kinderGartenDelegate;

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public KinderGarten getKinderGartenDelegate() {
		return kinderGartenDelegate;
	}

	public void setKinderGartenDelegate(KinderGarten kinderGartenDelegate) {
		this.kinderGartenDelegate = kinderGartenDelegate;
	}

	public KinderGarten getKinderGartenInscription() {
		return kinderGartenInscription;
	}

	public void setKinderGartenInscription(KinderGarten kinderGartenInscription) {
		this.kinderGartenInscription = kinderGartenInscription;
	}

	public KinderGarten getKinderGartenResponsible() {
		return kinderGartenResponsible;
	}

	public void setKinderGartenResponsible(KinderGarten kinderGartenResponsible) {
		this.kinderGartenResponsible = kinderGartenResponsible;
	}

	public SwitchAccount getSwitchAccount() {
		return switchAccount;
	}

	public void setSwitchAccount(SwitchAccount switchAccount) {
		this.switchAccount = switchAccount;
	}

	public List<Child> getListChilds() {
		return listChilds;
	}

	public void setListChilds(List<Child> listChilds) {
		this.listChilds = listChilds;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public int getScoreDelegate() {
		return scoreDelegate;
	}

	public void setScoreDelegate(int scoreDelegate) {
		this.scoreDelegate = scoreDelegate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StateUser getStateUser() {
		return stateUser;
	}

	public void setStateUser(StateUser stateUser) {
		this.stateUser = stateUser;
	}

	

	public void IncrementScoreDelegate() {
		this.scoreDelegate++;
	}

}
