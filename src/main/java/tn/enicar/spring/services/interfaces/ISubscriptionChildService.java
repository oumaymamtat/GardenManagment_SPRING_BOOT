package tn.enicar.spring.services.interfaces;

import java.util.List;
import java.util.Set;

import tn.enicar.spring.entity.CategorySubscription;
import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.Extra;
import tn.enicar.spring.entity.SubscriptionChild;

public interface ISubscriptionChildService {
	
	
	public SubscriptionChild addSubscriptionChild(SubscriptionChild s);
	public List<SubscriptionChild> getAllSubscriptionByChild(int id);
	public void update(SubscriptionChild s);
	public void delete(int id);
	public SubscriptionChild getById(int id);
	public void updateTotalWithParticipateEvent(Child c);
	public List<SubscriptionChild> getAll();
	
	

}
