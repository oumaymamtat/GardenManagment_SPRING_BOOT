package tn.enicar.spring.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.enicar.spring.entity.Category;
import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.Event;
import tn.enicar.spring.entity.Notification;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.repository.ICategoryRepository;
import tn.enicar.spring.repository.IChildRepository;
import tn.enicar.spring.repository.IEstimateRepository;
import tn.enicar.spring.repository.IEventRepository;
import tn.enicar.spring.repository.INotification;
import tn.enicar.spring.repository.IUserRepository;
import tn.enicar.spring.services.interfaces.IEventService;

@Service
public class EventServiceImpl implements IEventService {
	
	private static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	
	@Autowired
	IEventRepository iEventRepository;
	@Autowired
	ICategoryRepository iCategoryRepository;
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	MailServiceImpl servicemail;
	@Autowired

	IEstimateRepository iEstimateRepository;
	
	@Override
	public void addEvent(Event event,int id) {
		event.setCategory(iCategoryRepository.findById(id).orElse(null));
		iEventRepository.save(event);
	}
	@Override
	public void updateEvent(String object,String description, Date date, double price, int eventId) {
		iEventRepository.updateEventJPQL(object, description, date, price, eventId);;

	}

	@Override
	public List<Event> getAllevent() {
		return (List<Event>) iEventRepository.findAll();
	}

	@Override
	public void deleteEventaById(int eventId) {
		Event event = iEventRepository.findById(eventId).get();
		iEventRepository.delete(event);
	}

	@Override
	public Event getEventById(int eventId) {
		return iEventRepository.findById(eventId).get();

	}

	@Override
	public void affecterEventACategory(int eventId, int categoryId) {
		Category categoryManagedEntity = iCategoryRepository.findById(categoryId).get();
		Event eventManagedEntity = iEventRepository.findById(eventId).get();

		eventManagedEntity.setCategory(categoryManagedEntity);
		iEventRepository.save(eventManagedEntity);
	}


	@Override
	public List<Event> getAllEventForToday() {
		return iEventRepository.getAllEventPourToday();
	}

	@Autowired
	IChildRepository iChildRepository;

	@Override
	public List<Event> getEventForChild(int idChild) {
		Child c = iChildRepository.findById(idChild).orElse(null);
		List<Event> liste = (List<Event>) iEventRepository.findAll();
		// List<Category> listCategory =
		// (List<Category>)iCategoryRepository.findAll();
		List<Category> listInterest = c.getListInterest();
		List<Event> lis = new ArrayList<>();
		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < listInterest.size(); j++) {
				if (liste.get(i).getCategory().equals(listInterest.get(j))
						&& listInterest.get(j).getListChild().contains(c)) {
					lis.add(liste.get(i));

				}
			}
		}
		return lis;
	}

	@Override
	public void SendRequestItem(int id_event, int userId, int kindergartenId) {
		Date date = new Date();
		User user = iUserRepository.findById(userId).orElse(null);
		Event event = iEventRepository.findById(id_event).get();
		if (user.getKinderGartenInscription().getId() == kindergartenId) {
			if (date.after(event.getDate())) {
				servicemail.sendSimpleMail(user.getEmail(), event.getCategory().getKinderGarten().getResponsible().getFirstName()+" Hey Provider " + user.getFirstName(),
						" I need, " + event.getObject()+ " with number of places "+event.getnParticipate() + " ! with price " + event.getPrice());
			} else {
				log.info("the event didint terminated");
			}
		}
	}

	private final String ACCOUNT_SID ="AC131848a87fecf99be837175861639fc2";

    private final String AUTH_TOKEN = "88fe3ade569a7fa18adfc2b281470df2";

    private final String FROM_NUMBER = "+12563686010";
	
    @Override
	public void SendSmstoProvider(int id_event, int userId, int kindergartenId) {
		Date date = new Date();
		User user = iUserRepository.findById(userId).orElse(null);
		Event event = iEventRepository.findById(id_event).get();
		if (user.getKinderGartenInscription().getId() == kindergartenId) {
			
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		        Message message = Message.creator(new PhoneNumber("+216"+user.getTel()), new PhoneNumber(FROM_NUMBER), " I need this product please ! "+event.getObject())
		        		.create();
		        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

			} 
		}
	
	
    @Override
	public List<Event> getAllEventbyprice(int price) {
				
		return iEventRepository.getAllEventbyprice(price);
    }
	
	@Override
	public List<String> getEstimateByEvent(int idEvent) {
		return iEstimateRepository.getEstimateByEventJPQL(idEvent);
	}


	@Override
	public int addParticipate(int id) {
		Event e = iEventRepository.findById(id).orElse(null);
		e.incrimentParticipate();
		int ide = iEventRepository.save(e).getId();
		return ide;
	}

}
