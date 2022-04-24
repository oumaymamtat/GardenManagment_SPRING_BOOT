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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubscriptionChild implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dateC;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateStart;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateEnd;

	private double total;
	private double totalPay;
	private double restPay;
	private double discount;

	@ManyToOne
	private CategorySubscription categorySubscription;

	@ManyToMany
	private List<Extra> lisExtras = new ArrayList<Extra>();

	@ManyToOne
	private Child child;



	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public CategorySubscription getCategorySubscription() {
		return categorySubscription;
	}

	public void setCategorySubscription(CategorySubscription categorySubscription) {
		this.categorySubscription = categorySubscription;
	}

	public List<Extra> getLisExtras() {
		return lisExtras;
	}

	public void setLisExtras(List<Extra> lisExtras) {
		this.lisExtras = lisExtras;
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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}

	public double getRestPay() {
		return restPay;
	}

	
	
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setRestPay(double restPay) {
		this.restPay = restPay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionChild other = (SubscriptionChild) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
