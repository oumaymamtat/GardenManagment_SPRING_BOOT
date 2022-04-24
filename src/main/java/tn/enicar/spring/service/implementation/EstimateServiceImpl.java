package tn.enicar.spring.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.Club;
import tn.enicar.spring.entity.Estimate;
import tn.enicar.spring.entity.PKEstimate;
import tn.enicar.spring.repository.IEstimateRepository;
import tn.enicar.spring.services.interfaces.IEstimateService;

@Service
public class EstimateServiceImpl implements IEstimateService{
	@Autowired
	IEstimateRepository iEstimateRepository;

	@Override
	public void addEstimate(int providerId,int kinderId,String item,int qte,double total ) {
		PKEstimate pk= new  PKEstimate();
		Estimate e= new Estimate();
		pk.setDateC(new Date());
		pk.setIdKinder(kinderId);
		pk.setIdUser(providerId);
		e.setItem(item);
		e.setQte(qte);
		e.setTotal(total);
		e.setPkEstimate(pk);
		iEstimateRepository.save(e);
		
	}


	@Override
	public List<Estimate> getAllEstimate() {
		return (List<Estimate>) iEstimateRepository.findAll();

	}

	@Override
	public List<Estimate> getEstimateByKinderAndProvider(int kinderId, int ProviderId) {
		return iEstimateRepository.getEstimateByKinderAndProviderJPQL(kinderId, ProviderId);
	}

	@Override
	public void deleteEstimate(Date estimateDate,int iduser,int idkinder) {
		iEstimateRepository.deleteRepasJPQL(estimateDate, iduser, idkinder);
		
	}


	@Override
	public void updateEstimate(Date estimateDate, int iduser, int idkinder, String item, int qte, double total) {
		iEstimateRepository.updateEstimateJPQL(qte, total, item, estimateDate, iduser, idkinder);
		
	}



}
