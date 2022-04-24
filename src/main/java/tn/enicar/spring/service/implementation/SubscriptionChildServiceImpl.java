package tn.enicar.spring.service.implementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.spring.entity.CategorySubscription;
import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.Event;
import tn.enicar.spring.entity.Extra;
import tn.enicar.spring.entity.SubscriptionChild;
import tn.enicar.spring.repository.IChildRepository;
import tn.enicar.spring.repository.ISubscriptionChildRepository;
import tn.enicar.spring.services.interfaces.ISubscriptionChildService;

@Service
public class SubscriptionChildServiceImpl implements ISubscriptionChildService {

	@Autowired
	private ISubscriptionChildRepository rep;

	@Autowired
	private IChildRepository repChild;

	@Override
	public SubscriptionChild addSubscriptionChild(SubscriptionChild s) {
		
		/**
		 * 
		 * total extra
		 */

		double totalExtrat = 0;

		if (s.getLisExtras().size() != 0) {

			for (Extra e : s.getLisExtras()) {
				totalExtrat = totalExtrat + e.getPrice();
			}
		}

		s.setTotal(s.getCategorySubscription().getPrice() + totalExtrat);
		s.setRestPay(s.getCategorySubscription().getPrice() + totalExtrat);
		s.setTotalPay(0);
		s.setDateC(new Date());
		s.setDateC(new Date());
		rep.save(s);
		
		return s;

	}

	@Override
	public List<SubscriptionChild> getAllSubscriptionByChild(int id) {

		List<SubscriptionChild> list = new ArrayList<SubscriptionChild>();

		Child c = repChild.findById(id).orElse(null);

		if (c != null) {
			list = c.getLisSubscriptionChilds();
		}

		return list;
	}

	@Override
	public void update(SubscriptionChild s) {
		rep.save(s);

	}

	@Override
	public void delete(int id) {

		SubscriptionChild s = rep.findById(id).orElse(null);

		if (s != null) {

			rep.delete(s);
		}

	}

	// get subscription by id

	@Override
	public SubscriptionChild getById(int id) {
		return rep.findById(id).orElse(null);
	}

	public SubscriptionChild getByDate(Child c) {
		
		

		SubscriptionChild subscriptionChild = null;
		DateFormat dateFormatter = new SimpleDateFormat("yyyy");

		String dateSytem = dateFormatter.format(new Date());

		for (SubscriptionChild s : c.getLisSubscriptionChilds()) {

			if ((dateFormatter.format(s.getDateStart())).equals(dateSytem)
					|| (dateFormatter.format(s.getDateEnd())).equals(dateSytem)) {

				return s;

			}

		}

		return subscriptionChild;

	}

	@Override
	public void updateTotalWithParticipateEvent(Child c) {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy");

		double total = 0;

		Child child = repChild.findById(c.getId()).orElse(null);
		
		

		SubscriptionChild s = this.getByDate(child);

		if (s != null) {

			for (Event e : child.getLisEvents()) {
				 

				if ((dateFormatter.format(s.getDateStart())).equals(dateFormatter.format(e.getDate()))
						|| (dateFormatter.format(s.getDateEnd())).equals(dateFormatter.format(e.getDate()))) {

					total = total + e.getPrice();

				}

			}
			
			s.setTotal(s.getTotal()+total);
			rep.save(s);

		}
	}

	@Override
	public List<SubscriptionChild> getAll() {
		 
		return (List<SubscriptionChild>) rep.findAll();
	}

}
