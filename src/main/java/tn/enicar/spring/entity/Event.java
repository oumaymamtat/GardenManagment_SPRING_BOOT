package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String description;
	@Temporal(TemporalType.DATE)
	private Date date;

	private int nParticipate;

	private double price;

	private String object;
	@ManyToOne
	@JsonIgnore
	private Category category;
	
	@ManyToMany(mappedBy = "lisEvents")
	public List<Child> lisChilds = new ArrayList<Child>();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getnParticipate() {
		return nParticipate;
	}

	public void setnParticipate(int nParticipate) {
		this.nParticipate = nParticipate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public List<Child> getLisChilds() {
		return lisChilds;
	}

	public void setLisChilds(List<Child> lisChilds) {
		this.lisChilds = lisChilds;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	public void incrimentParticipate(){
		this.nParticipate++;
	}

}
