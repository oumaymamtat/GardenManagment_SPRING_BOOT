package tn.enicar.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PKEstimate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int idUser;
	private int idKinder;
	@Temporal(TemporalType.DATE)
	private Date dateC;
	
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdKinder() {
		return idKinder;
	}
	public void setIdKinder(int idKinder) {
		this.idKinder = idKinder;
	}
	public Date getDateC() {
		return dateC;
	}
	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateC == null) ? 0 : dateC.hashCode());
		result = prime * result + idKinder;
		result = prime * result + idUser;
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
		PKEstimate other = (PKEstimate) obj;
		if (dateC == null) {
			if (other.dateC != null)
				return false;
		} else if (!dateC.equals(other.dateC))
			return false;
		if (idKinder != other.idKinder)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
	
	 
	
	
	
	
	

}
