package tn.enicar.spring.services.interfaces;

import java.util.Date;
import java.util.List;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.Event;


public interface IEventService {
	public void addEvent(Event event, int id);
	public void updateEvent(String object,String description, Date date,double price, int eventId);
	public List<Event> getAllevent();
	public void deleteEventaById(int eventId);
	public Event getEventById(int eventId);
	public void affecterEventACategory(int eventId, int categoryId) ;
	public List<Event> getAllEventForToday();
	public List<Event> getEventForChild(int idChild);
	public void SendSmstoProvider(int id_event, int userId, int kindergartenId);
	public void SendRequestItem(int id_event, int userId, int kindergartenId);
	public List<String> getEstimateByEvent(int idEvent);
	public List<Event> getAllEventbyprice(int price);
	public int addParticipate(int id);
}
