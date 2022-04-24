package tn.enicar.spring.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.SubscriptionChild;
import tn.enicar.spring.repository.IChildRepository;
import tn.enicar.spring.repository.ISubscriptionChildRepository;
import tn.enicar.spring.services.interfaces.IFidelityPointService;

@Service
public class FidelityPointServiceImplementation implements IFidelityPointService {

	@Autowired
	ISubscriptionChildRepository subscriptionRep;
	@Autowired
	IChildRepository childRep;

	@Override
	public void transfertPointFidelity(int idSubscriptionChild, double point) {

		SubscriptionChild s = subscriptionRep.findById(idSubscriptionChild).orElse(null);

		if (s != null) {

			// update fidelity point child

			if (point <= s.getChild().getFidelityPoint()) {

				s.getChild().setFidelityPoint(s.getChild().getFidelityPoint() - point);

				childRep.save(s.getChild());

				/**
				 * 
				 * 200 point==>10 DT
				 * 
				 */

				s.setDiscount(s.getDiscount() + ((point * 10) / 200));
				
				s.setRestPay(s.getRestPay()-s.getDiscount());

				subscriptionRep.save(s);

			}

		}

	}

}
