package tn.enicar.spring.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estimate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PKEstimate pkEstimate;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User provider;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idKinder", referencedColumnName = "id", insertable = false, updatable = false)
	private KinderGarten kGarten;

	private String item;

	private int qte;

	private double total;

	public PKEstimate getPkEstimate() {
		return pkEstimate;
	}

	public void setPkEstimate(PKEstimate pkEstimate) {
		this.pkEstimate = pkEstimate;
	}

	public User getProvider() {
		return provider;
	}

	public void setProvider(User provider) {
		this.provider = provider;
	}

	public KinderGarten getkGarten() {
		return kGarten;
	}

	public void setkGarten(KinderGarten kGarten) {
		this.kGarten = kGarten;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
