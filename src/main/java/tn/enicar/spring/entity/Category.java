package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@ManyToOne
	@JsonIgnore
	private KinderGarten kinderGarten;

	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Event> lisEvents = new ArrayList<Event>();

	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Club> lisClub = new ArrayList<Club>();

	@ManyToMany(mappedBy = "listInterest")
	@JsonIgnore
	private List<Child> listChild = new ArrayList<Child>();

	public List<Child> getListChild() {
		return listChild;
	}

	public void setListChild(List<Child> listChild) {
		this.listChild = listChild;
	}

	public List<Club> getLisClub() {
		return lisClub;
	}

	public void setLisClub(List<Club> lisClub) {
		this.lisClub = lisClub;
	}

	public List<Event> getLisEvents() {
		return lisEvents;
	}

	public void setLisEvents(List<Event> lisEvents) {
		this.lisEvents = lisEvents;
	}

	public KinderGarten getKinderGarten() {
		return kinderGarten;
	}

	public void setKinderGarten(KinderGarten kinderGarten) {
		this.kinderGarten = kinderGarten;
	}

	@OneToMany(mappedBy = "category")
	private List<Club> listClubs = new ArrayList<Club>();

	public List<Club> getListClubs() {
		return listClubs;
	}

	public void setListClubs(List<Club> listClubs) {
		this.listClubs = listClubs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
