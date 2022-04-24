package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategorySubscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	private double price;
	
	
	
	
	@OneToMany(mappedBy = "categorySubscription")
	@JsonIgnore
	private List<SubscriptionChild>listSubscriptionChilds = new ArrayList<SubscriptionChild>();
	
	
	
	@ManyToOne
	@JsonIgnore
	private KinderGarten kinderGarten;
	
	
	
	
	
	
	
	
	

	public KinderGarten getKinderGarten() {
		return kinderGarten;
	}

	public void setKinderGarten(KinderGarten kinderGarten) {
		this.kinderGarten = kinderGarten;
	}

	public List<SubscriptionChild> getListSubscriptionChilds() {
		return listSubscriptionChilds;
	}

	public void setListSubscriptionChilds(List<SubscriptionChild> listSubscriptionChilds) {
		this.listSubscriptionChilds = listSubscriptionChilds;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	
	

}
