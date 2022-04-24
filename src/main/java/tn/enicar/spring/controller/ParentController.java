package tn.enicar.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.enicar.spring.entity.CategorySubscription;
import tn.enicar.spring.entity.Child;

import tn.enicar.spring.entity.Event;
import tn.enicar.spring.entity.Extra;

import tn.enicar.spring.entity.SubscriptionChild;
import tn.enicar.spring.entity.User;
import tn.enicar.spring.entity.VoteForm;
import tn.enicar.spring.services.interfaces.ICategorySubscriptionService;
import tn.enicar.spring.services.interfaces.IChildService;

import tn.enicar.spring.services.interfaces.IEventService;
import tn.enicar.spring.services.interfaces.IExtraService;

import tn.enicar.spring.services.interfaces.IKinderGartenService;

import tn.enicar.spring.services.interfaces.ISubscriptionChildService;
import tn.enicar.spring.services.interfaces.IUploadFileService;
import tn.enicar.spring.services.interfaces.IUserService;
import tn.enicar.spring.services.interfaces.IVoteService;

@RestController
@RequestMapping("/parent")
@PreAuthorize("hasRole('ROLE_parent')")
public class ParentController {

	
	@Autowired
	ISubscriptionChildService subscriptionChildService;
	@Autowired
	IUploadFileService uploadFileService;
	
	@Autowired
	IUserService userS;
	@Autowired
	IExtraService iExtraService;
	@Autowired
	ICategorySubscriptionService iCategorySubscriptionService;

	@Autowired
	IEventService iEventService;


	@Autowired
	IVoteService iVoteService;
	@Autowired
	IKinderGartenService iKinderGartenService;
	//



	

	
	

	/***
	 * 
	 * 
	 * Crud subscription child
	 */

	@PostMapping("/addSubscriptionChild/{id}")
	@ResponseBody
	public void add(@RequestBody SubscriptionChild s,@PathVariable("id")int id) {
		
		
		s.setCategorySubscription(iCategorySubscriptionService.getCategorySubscriptionById(id));

		/**
		 * 
		 * total extra
		 */

		/*double totalExtrat = 0;

		if (s.getLisExtras().size() != 0) {

			for (Extra e : s.getLisExtras()) {
				totalExtrat = totalExtrat + e.getPrice();
			}
		}

		s.setTotal(s.getCategorySubscription().getPrice() + totalExtrat);
		s.setRestPay(s.getCategorySubscription().getPrice() + totalExtrat);
		s.setTotalPay(0);
		s.setDateC(new Date());*/

		subscriptionChildService.addSubscriptionChild(s);

	}
	
	@GetMapping(value ="/getSubscriptionById/{id}")
	@ResponseBody
	public SubscriptionChild getSubscriptionByid(@PathVariable int id) {

		return subscriptionChildService.getById(id);
	}

	@DeleteMapping("/deleteSubscriptionChild/{id}")
	@ResponseBody
	public void delete(@PathVariable int id) {
		subscriptionChildService.delete(id);
	}

	@GetMapping("/getListSubscriptionByChild/{id}")
	@ResponseBody
	public List<SubscriptionChild> getListSubscriptionByChild(@PathVariable int id) {

		return subscriptionChildService.getAllSubscriptionByChild(id);
	}

	@PutMapping("/updateSubscription")
	public void updateSubscriptionChild(@RequestBody SubscriptionChild s) {

		subscriptionChildService.update(s);

	}


	@PutMapping("/RegisterKinderGarten/{id_kg}/{id_user}")
	@ResponseBody

	public String RegisterKinderGarten(@PathVariable("id_user") int iduser, @PathVariable("id_kg") int id_kg)

	{
		return userS.RegisterKinderGarten(iduser, id_kg);
	}
	
	@GetMapping(value = "/getAllextra")
	@ResponseBody
	public List<Extra> getAllextra() {

		return iExtraService.getAllextra();
	}
	
	@GetMapping(value = "/getExtraById/{extraId}")
	@ResponseBody
	public Extra getExtraById(@PathVariable("extraId") int extraId) {
		return iExtraService.getExtraById(extraId);
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


	@GetMapping(value = "/getAllevent")
	@ResponseBody
	public List<Event> getAllevent() {

		return iEventService.getAllevent();
	}
	
	@PutMapping("/addParticipate/{id}")
	@ResponseBody
	public int addParticipate(@PathVariable("id") int id)
	{
		return iEventService.addParticipate(id);
		
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

}
