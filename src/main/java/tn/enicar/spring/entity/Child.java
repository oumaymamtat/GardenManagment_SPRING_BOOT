package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Child implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	@Temporal(TemporalType.DATE)
	private Date dateOfbith;

	private String sex;

	private int age;

	private String picture;
	
	private double fidelityPoint;

	@ManyToOne
	//@JsonIgnore
	private User parent;

	@OneToMany(mappedBy = "child", cascade = { CascadeType.REFRESH })
	@JsonIgnore
	private List<SubscriptionChild> lisSubscriptionChilds = new ArrayList<SubscriptionChild>();

	

	@ManyToMany
	@JsonIgnore
	private List<Category> listInterest = new ArrayList<Category>();

	@ManyToMany
	//@JsonIgnore
	private List<Event> lisEvents = new ArrayList<Event>();

	public List<Category> getListInterest() {
		return listInterest;
	}

	public void setListInterest(List<Category> listInterest) {
		this.listInterest = listInterest;
	}

	

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public List<SubscriptionChild> getLisSubscriptionChilds() {
		return lisSubscriptionChilds;
	}

	public void setLisSubscriptionChilds(List<SubscriptionChild> lisSubscriptionChilds) {
		this.lisSubscriptionChilds = lisSubscriptionChilds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfbith() {
		return dateOfbith;
	}

	public void setDateOfbith(Date dateOfbith) {
		this.dateOfbith = dateOfbith;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Event> getLisEvents() {
		return lisEvents;
	}

	public void setLisEvents(List<Event> lisEvents) {
		this.lisEvents = lisEvents;
	}

	public double getFidelityPoint() {
		return fidelityPoint;
	}

	public void setFidelityPoint(double fidelityPoint) {
		this.fidelityPoint = fidelityPoint;
	}

	 
	
	
	
	
	

}
