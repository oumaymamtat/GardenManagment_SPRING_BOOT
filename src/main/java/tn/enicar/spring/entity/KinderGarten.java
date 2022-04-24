package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class KinderGarten implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String adress;

	private String email;

	private int tel;
	
	@Column(nullable = true)
	private float scoreEval;

	private String logo;
	
	private double latitude;
	private double longitude;
	@OneToMany(mappedBy = "kinderGartenInscription")
	@JsonIgnore
	private List<User> listParent = new ArrayList<User>();

	@OneToMany(mappedBy = "kinderGarten")
	@JsonIgnore
	private List<Activity> listActivity = new ArrayList<Activity>();

	@OneToMany(mappedBy = "kinderGarten")
	@JsonIgnore
	private List<Extra> listExtra = new ArrayList<Extra>();

	@OneToMany(mappedBy = "kinderGarten")
	@JsonIgnore
	private List<CategorySubscription> listCategoryS = new ArrayList<CategorySubscription>();



	@OneToMany(mappedBy = "kinderGarten")
	@JsonIgnore
	private List<Category> listCategory = new ArrayList<Category>();

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public List<Activity> getListActivity() {
		return listActivity;
	}

	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}

	public List<Extra> getListExtra() {
		return listExtra;
	}

	public void setListExtra(List<Extra> listExtra) {
		this.listExtra = listExtra;
	}

	public List<CategorySubscription> getListCategoryS() {
		return listCategoryS;
	}

	public void setListCategoryS(List<CategorySubscription> listCategoryS) {
		this.listCategoryS = listCategoryS;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getListParent() {
		return listParent;
	}

	public void setListParent(List<User> listParent) {
		this.listParent = listParent;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public User getDelegate() {
		return delegate;
	}

	public void setDelegate(User delegate) {
		this.delegate = delegate;
	}

	@OneToOne
	private User responsible;

	@OneToOne
	private User delegate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public float getScoreEval() {
		return scoreEval;
	}

	public void setScoreEval(float scoreEval) {
		this.scoreEval = scoreEval;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
