package tn.enicar.spring.services.interfaces;

import java.util.Date;
import java.util.List;

import tn.enicar.spring.entity.Estimate;


public interface IEstimateService {

	public void addEstimate(int providerId,int kinderId,String item,int qte,double total ) ;
	public void updateEstimate(Date estimateDate,int iduser,int idkinder,String item,int qte,double total);
	public List<Estimate> getAllEstimate();
	public void deleteEstimate(Date estimateDate,int iduser,int idkinder);
	public List<Estimate> getEstimateByKinderAndProvider(int kinderId,int ProviderId);
	
}
