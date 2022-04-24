package tn.enicar.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String description;
	
	private String photo;
	
	
	
	
	@ManyToOne
	@JsonIgnore
	private KinderGarten kinderGarten;
	
	
	
	
	



	
	 




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




	public String getPhoto() {
		return photo;
	}




	public void setPhoto(String photo) {
		this.photo = photo;
	}




	public KinderGarten getKinderGarten() {
		return kinderGarten;
	}




	public void setKinderGarten(KinderGarten kinderGarten) {
		this.kinderGarten = kinderGarten;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
