package tn.enicar.spring.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.Activity;
import tn.enicar.spring.entity.Category;
import tn.enicar.spring.entity.CategorySubscription;
import tn.enicar.spring.entity.Child;
import tn.enicar.spring.entity.Club;
import tn.enicar.spring.entity.Estimate;
import tn.enicar.spring.entity.Event;
import tn.enicar.spring.entity.Extra;
import tn.enicar.spring.entity.KinderGarten;

import tn.enicar.spring.entity.SessionVote;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.Vote;
import tn.enicar.spring.entity.VoteForm;
import tn.enicar.spring.repository.IVoteRepository;
import tn.enicar.spring.services.interfaces.IActivityService;
import tn.enicar.spring.services.interfaces.ICategoryService;
import tn.enicar.spring.services.interfaces.ICategorySubscriptionService;
import tn.enicar.spring.services.interfaces.IClubService;
import tn.enicar.spring.services.interfaces.IEstimateService;
import tn.enicar.spring.services.interfaces.IEventService;
import tn.enicar.spring.services.interfaces.IExtraService;
import tn.enicar.spring.services.interfaces.IKinderGartenService;

import tn.enicar.spring.services.interfaces.ISessionVoteService;
import tn.enicar.spring.services.interfaces.IUploadFileService;
import tn.enicar.spring.services.interfaces.IUserService;
import tn.enicar.spring.services.interfaces.IVoteService;

@RestController
@RequestMapping("/admingarten")
@PreAuthorize("hasRole('ROLE_adminGarten')")
public class AdminGartenController {

	@Autowired
	IKinderGartenService iKinderGartenService;
	@Autowired
	IExtraService iExtraService;
	@Autowired
	ICategorySubscriptionService iCategorySubscriptionService;

	@Autowired
	IActivityService iActivityService;
	@Autowired
	IEventService iEventService;
	@Autowired
	ICategoryService iCategoryService;
	@Autowired
	IClubService iClubService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IVoteService iVoteService;
	@Autowired
	ISessionVoteService iSessionVoteService;

	@Autowired
	IUploadFileService uploadFileService ;

	@PostMapping("/addKinderGarten")
	@ResponseBody
	public KinderGarten addKinderGarten(@RequestBody KinderGarten kendergarten) {
		iKinderGartenService.addKindergarten(kendergarten);
		return kendergarten;
	}
	@PostMapping("/assignLogo/{id}")
	public void assignLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
		if (uploadFileService.addFile(file)) {
			iKinderGartenService.assignLogo(id, file);
		}

	}

	@PutMapping(value = "/BannedUser/{id}/{kinderId}")
	@ResponseBody
	public void BannedUser(@PathVariable("id") int id,@PathVariable("kinderId") int kinderId){
		iKinderGartenService.BannedUser(id, kinderId);
	}
	
	@PutMapping(value = "/recupComptes/{idUser}/{kinderId}")
	@ResponseBody
	public void recupComptes(@PathVariable("idUser") int idUser,@PathVariable("kinderId") int kinderId) {
		iKinderGartenService.recupComptes(idUser, kinderId);
	}
	
	@GetMapping(value = "/getKindergartenById/{kinderId}")
	@ResponseBody
	public KinderGarten getKindergartenById(@PathVariable("kinderId") int kinderId) {
		return iKinderGartenService.getKindergartenById(kinderId);
	}

	
	@GetMapping(value = "/findUserByIdK/{responsible}")
	@ResponseBody
	public KinderGarten findUserByIdK(@PathVariable("responsible") int responsible) {
		return iKinderGartenService.findUserByIdK(responsible);
	}
	
	
	@GetMapping(value = "/getAllkinder")
	@ResponseBody
	public List<KinderGarten> getAllkinder() {

		return iKinderGartenService.getAllkinder();
	}

	@GetMapping(value = "/getKindergartenByResponsible/{responsibleId}")
	@ResponseBody
	public List<KinderGarten> getKindergartenByResponsible(@PathVariable("responsibleId") int responsibleId) {

		return iKinderGartenService.getKindergartenByResponsible(responsibleId);
	}
	
	@PutMapping(value = "/updateKinderGarten/{id}")
	@ResponseBody
	public void updateKinderGarten(@PathVariable("id") int kenderId, @RequestBody KinderGarten kendergarten) {
		iKinderGartenService.updateKindergarten(kendergarten.getName(), kendergarten.getAdress(),
				kendergarten.getEmail(), kendergarten.getTel(), kendergarten.getLogo(),kendergarten.getLongitude(),kendergarten.getLatitude(), kenderId);

	}

	@DeleteMapping("/deleteKindergartenById/{kenderId}")
	@ResponseBody
	public void deleteKindergartenById(@PathVariable("kenderId") int kenderId) {
		iKinderGartenService.deleteKindergartenById(kenderId);
	}

	// Extra ...

	@PostMapping("/addExtra/{idk}")
	@ResponseBody
	public Extra addExtra(@RequestBody Extra extra,@PathVariable("idk") int idk) {
		iExtraService.addExtra(extra,idk);
		return extra;
	}

	@GetMapping(value = "/getExtraById/{extraId}")
	@ResponseBody
	public Extra getExtraById(@PathVariable("extraId") int extraId) {
		return iExtraService.getExtraById(extraId);
	}

	@GetMapping(value = "/getAllextra")
	@ResponseBody
	public List<Extra> getAllextra() {

		return iExtraService.getAllextra();
	}

	@PutMapping(value = "/updateExtra/{id}")
	@ResponseBody
	public void updateExtra(@PathVariable("id") int ExtraId, @RequestBody Extra extra) {
		iExtraService.updateExtra(extra.getDescription(), extra.getPrice(), ExtraId);

	}

	@DeleteMapping("/deleteExtraById/{extraId}")
	@ResponseBody
	public void deleteExtraById(@PathVariable("extraId") int extraId) {
		iExtraService.deleteExtraById(extraId);
	}

	@PutMapping(value = "/affecterExtraAkinderGarten/{extraId}/{kinderId}")
	public void affecterExtraAkinderGarten(@PathVariable("extraId") int extraId,
			@PathVariable("kinderId") int kinderId) {
		iExtraService.affecterExtraAkinderGarten(extraId, kinderId);

	}

	// categorySubscription...

	@PostMapping("/addCategorySubscription/{idk}")
	@ResponseBody
	public CategorySubscription addCategorySubscription(@RequestBody CategorySubscription categorySubscription,@PathVariable("idk") int idk) {
		iCategorySubscriptionService.addCategorySubscription(categorySubscription,idk);
		return categorySubscription;
	}

	@GetMapping(value = "/getCategorySubscriptionById/{categorySubscriptionId}")
	@ResponseBody
	public CategorySubscription getCategorySubscriptionById(
			@PathVariable("categorySubscriptionId") int categorySubscriptionId) {
		return iCategorySubscriptionService.getCategorySubscriptionById(categorySubscriptionId);
	}

	@GetMapping(value = "/getAllCategorySubscription")
	@ResponseBody
	public List<CategorySubscription> getAllCategorySubscription() {

		return iCategorySubscriptionService.getAllCategorySubscription();
	}

	@PutMapping(value = "/updateCategorySubscription/{id}")
	@ResponseBody
	public void updateCategorySubscription(@PathVariable("id") int categorySubscriptionId,
			@RequestBody CategorySubscription categorySubscription) {
		iCategorySubscriptionService.updateCategorySubscription(categorySubscription.getDescription(),
				categorySubscription.getPrice(), categorySubscriptionId);

	}

	@DeleteMapping("/deleteCategorySubscriptionById/{categorySubscriptionId}")
	@ResponseBody
	public void deleteCategorySubscriptionById(@PathVariable("categorySubscriptionId") int categorySubscriptionId) {
		iCategorySubscriptionService.deleteCategorySubscriptionById(categorySubscriptionId);
	}

	@PutMapping(value = "/affecterCategorySubscriptionAkinderGarten/{categorySubscriptionId}/{kinderId}")
	public void affecterCategorySubscriptionAkinderGarten(
			@PathVariable("categorySubscriptionId") int categorySubscriptionId,
			@PathVariable("kinderId") int kinderId) {
		iCategorySubscriptionService.affecterCategorySubscriptionAkinderGarten(categorySubscriptionId, kinderId);

	}


	// activity ...

	@PostMapping("/addActivity/{idk}")
	@ResponseBody
	public int addActivity(@RequestBody Activity activity,@PathVariable("idk") int idk) {
		return iActivityService.addActivity(activity,idk);
	
	}

	@PostMapping("/assignPhoto/{id}")
	public void assignPhoto(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
		if (uploadFileService.addFile(file)) {
			iActivityService.assignPhoto(id, file);
		}
	}
	
	@GetMapping(value = "/getActivityById/{activityaId}")
	@ResponseBody
	public Activity getActivityById(@PathVariable("activityaId") int activityaId) {
		return iActivityService.getActivityById(activityaId);
	}

	@GetMapping(value = "/getAllactivity")
	@ResponseBody
	public List<Activity> getAllactivity() {

		return iActivityService.getAllactivity();
	}

	@PutMapping(value = "/updateActivity/{id}")
	@ResponseBody
	public void updateActivity(@PathVariable("id") int activityId, @RequestBody Activity activity) {
		iActivityService.updateActivity(activity.getDescription(), activity.getPhoto(), activityId);

	}

	@DeleteMapping("/deleteActivityById/{activityId}")
	@ResponseBody
	public void deleteActivityById(@PathVariable("activityId") int activityId) {
		iActivityService.deleteActivityById(activityId);
	}

	@PutMapping(value = "/affecterActivityAkinderGarten/{activityId}/{kinderId}")
	public void affecterActivityAkinderGarten(@PathVariable("activityId") int activityId,
			@PathVariable("kinderId") int kinderId) {
		iActivityService.affecterActivityAkinderGarten(activityId, kinderId);

	}

	@PutMapping(value = "/deleteAllActivity/{kinderId}")
	public void deleteAllActivity(@PathVariable("kinderId") int kinderId) {
		iActivityService.deleteAllActivity(kinderId);
	}

	@GetMapping(value = "/findAllActivityByKinderGarten/{kinderId}")
	@ResponseBody
	public List<Activity> findAllActivityByKinderGarten(@PathVariable("kinderId") int kinderId) {
		return iActivityService.findAllActivityByKinderGarten(kinderId);
	}

	// Event...
	
	@GetMapping(value = "/getAllEventbyprice/{price}")
	@ResponseBody
	public List<Event> getAllEventbyprice(@PathVariable("price") int price){
		return iEventService.getAllEventbyprice(price);
	}

	 /*	@Autowired
	    private SimpMessagingTemplate webSocket;

	    private final String  TOPIC_DESTINATION = "/lesson/sms";

	    @RequestMapping(value = "/sms/{id_event}/{userId}/{kindergartenId}", method = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public void smsSubmit(@PathVariable("id_event") int id_event,
				@PathVariable("userId") int userId,@PathVariable("kindergartenId") int kindergartenId) {
	        try{
	            iEventService.SendSmstoProvider(id_event, userId, kindergartenId);
	        }
	        catch(Exception e){

	            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
	            throw e;
	        }
	        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+userId);

	    }*/
	    private String getTimeStamp() {
	        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	     }

	
	
	
	@GetMapping(value = "/getEventForChild/{idChild}")
	@ResponseBody
	public List<Event> getEventForChild(@PathVariable("idChild") int idChild){
		return iEventService.getEventForChild(idChild);
	}

	@GetMapping(value = "/getEstimateByEvent/{idEvent}")
	@ResponseBody
	public List<String> getEstimateByEvent(@PathVariable("idEvent") int idEvent){
		return iEventService.getEstimateByEvent(idEvent);
	}

	@PutMapping(value = "/SendRequestItem/{id_event}/{userId}/{kindergartenId}")
	@ResponseBody
	public void SendRequestItem(@PathVariable("id_event") int id_event,
			@PathVariable("userId") int userId,@PathVariable("kindergartenId") int kindergartenId) {
		iEventService.SendRequestItem(id_event, userId, kindergartenId);

	}
	
	
	@GetMapping(value = "/getAllEventForToday")
	@ResponseBody
	public List<Event> getAllEventForToday(){
		return iEventService.getAllEventForToday();
	}
	
	@PostMapping("/addEvent/{id}")
	@ResponseBody
	public void addEvent(@RequestBody Event event,@PathVariable("id") int id) {
		iEventService.addEvent(event,id);
		
	}

	@GetMapping(value = "/getEventById/{eventId}")
	@ResponseBody
	public Event getEventById(@PathVariable("eventId") int eventId) {
		return iEventService.getEventById(eventId);
	}

	@GetMapping(value = "/getAllevent")
	@ResponseBody
	public List<Event> getAllevent() {

		return iEventService.getAllevent();
	}

	@PutMapping(value = "/updateEvent/{id}")
	@ResponseBody
	public void updateEvent(@PathVariable("id") int eventId, @RequestBody Event event) {
		iEventService.updateEvent(event.getObject(),event.getDescription(), event.getDate(),event.getPrice(), eventId);

	}

	@DeleteMapping("/deleteEventById/{eventId}")
	@ResponseBody
	public void deleteEventById(@PathVariable("eventId") int eventId) {
		iEventService.deleteEventaById(eventId);
	}

	@PutMapping(value = "/affecterEventACategory/{eventId}/{categoryId}")
	public void affecterEventACategory(@PathVariable("eventId") int eventId,
			@PathVariable("categoryId") int categoryId) {
		iEventService.affecterEventACategory(eventId,categoryId);

	}

	// Category ...

	@PostMapping("/addCategory/{idk}")
	@ResponseBody
	public Category addCategory(@RequestBody Category category,@PathVariable("idk") int idk) {
		iCategoryService.addCategory(category,idk);
		return category;
	}

	@GetMapping(value = "/getCategoryById/{categoryId}")
	@ResponseBody
	public Category getCategoryById(@PathVariable("categoryId") int categoryId) {
		return iCategoryService.getCategoryById(categoryId);
	}

	@GetMapping(value = "/getAllcategory")
	@ResponseBody
	public List<Category> getAllcategory() {

		return iCategoryService.getAllcategory();
	}

	@PutMapping(value = "/updateCategory/{id}")
	@ResponseBody
	public void updateCategory(@PathVariable("id") int categoryId, @RequestBody Category category) {
		iCategoryService.updateCategory(category.getDescription(), categoryId);

	}

	@DeleteMapping("/deleteCategoryById/{categoryId}")
	@ResponseBody
	public void deleteCategoryById(@PathVariable("categoryId") int categoryId) {
		iCategoryService.deleteCategoryById(categoryId);
	}

	@PutMapping(value = "/affecterCategoryAkinderGarten/{categoryId}/{kinderId}")
	public void affecterCategoryAkinderGarten(@PathVariable("categoryId")int categoryId,@PathVariable("kinderId") int kinderId) {
		iCategoryService.affecterCategoryAkinderGarten(categoryId, kinderId);

	}
	
	
	// Club ...

	@PostMapping("/addClub/{id}")
	@ResponseBody
	public void addClub(@RequestBody Club club, @PathVariable("id") int id) {
		iClubService.addClub(club,id);
	}

	@GetMapping(value = "/getClubById/{clubId}")
	@ResponseBody
	public String getClubById(@PathVariable("clubId") int clubId) {
		return iClubService.getClubById(clubId).getId()+"";
	}

	@GetMapping(value = "/getAllclub")
	@ResponseBody
	public List<Club> getAllclub() {

		return iClubService.getAllclub();
	}

	@PutMapping(value = "/updateClub/{id}")
	@ResponseBody
	public void updateClub(@PathVariable("id") int clubId, @RequestBody Club club) {
		iClubService.updateClub(club.getDescription(), clubId);

	}

	@DeleteMapping("/deleteClubById/{clubId}")
	@ResponseBody
	public void deleteClubById(@PathVariable("clubId") int clubId) {
		iClubService.deleteClubById(clubId);
	}

	@PutMapping(value = "/affecterClubACategory/{clubId}/{categoryId}")
	public void affecterClubACategory(@PathVariable("clubId") int clubId, @PathVariable("categoryId") int categoryId) {
		iClubService.affecterClubACategory(clubId, categoryId);

	}

	@GetMapping("/kinder_garden/{id}/delegators")
	@ResponseBody
	public List<User> listDelegators(@PathVariable int id) {
		return iKinderGartenService.listDelegators(id);
	}

	@PostMapping("/kinder_garden/{id}/delegators/vote/{idsession}")
	@ResponseBody
	public int addVote(@PathVariable int id,@PathVariable("idsession") int idsession,@RequestBody VoteForm vote) {
		return iVoteService.addVote(id,vote,idsession);
	}

	@GetMapping("/kinder_garden/{id}/delegator/validate")
	@ResponseBody
	public void delegatorsElection(@PathVariable int id) {
		iKinderGartenService.delegatorsElection(id);
	}
	// Session Vote
	@PostMapping("/addSessionVote")
	@ResponseBody
	public SessionVote addSessionVote(@RequestBody SessionVote sessionVote) {
		iSessionVoteService.addSessionVote(sessionVote);
		return sessionVote;
	}
	@GetMapping(value = "/getSessionVoteById/{sessionVoteId}")
	@ResponseBody
	public SessionVote getSessionVoteById(@PathVariable("sessionVoteId") int sessionVoteId) {
		return iSessionVoteService.getSessionVoteById(sessionVoteId);
	}
	@GetMapping(value = "/getAllSessionVote")
	@ResponseBody
	public List<SessionVote> getAllSessionVote() {
		return iSessionVoteService.getAllSessionVote();
	}
	@PutMapping(value = "/updateSessionVote/{id}")
	@ResponseBody
	public void updateSessionVote(@PathVariable("id") int sessionVoteId, @RequestBody SessionVote sessionVote) {
		iSessionVoteService.updateSessionVote(sessionVote.getWinner(), sessionVote.getDateStart(), sessionVote.getDateEnd(), sessionVoteId);
	}
	@DeleteMapping("/deleteSessionVoteById/{sessionVoteId}")
	@ResponseBody
	public void deleteSessionVoteById(@PathVariable("sessionVoteId") int sessionVoteId) {
		iSessionVoteService.deleteSessionVoteById(sessionVoteId);
	}
	@GetMapping("/kinder_garden/{id}/delegators/{sessionVoteId}/Winner")
	@ResponseBody
	public void delegatorsWinner(@PathVariable int id,@PathVariable int sessionVoteId) {
		iSessionVoteService.delegatorsWinner(id, sessionVoteId);
	}

	
	
	@GetMapping(value = "/findAllCategoryByKinderGarten/{kinderId}")
	@ResponseBody
	public List<Category> findAllCategoryByKinderGarten(@PathVariable("kinderId") int kinderId) {
		return iCategoryService.findAllCategoryByKinderGarten(kinderId);
	}
	
	@GetMapping(value = "/findAllCategorySubscriptionByKinderGarten/{kinderId}")
	@ResponseBody
	public List<CategorySubscription> findAllCategorySubscriptionByKinderGarten(@PathVariable("kinderId") int kinderId) {
		return iCategorySubscriptionService.findAllCategorySubscriptionByKinderGarten(kinderId);
	}
	
	@GetMapping(value = "/findAllExtraByKinderGarten/{kinderId}")
	@ResponseBody
	public List<Extra> findAllExtraByKinderGarten(@PathVariable("kinderId") int kinderId) {
		return iExtraService.findAllExtraByKinderGarten(kinderId);
	}
	
}
