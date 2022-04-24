package tn.enicar.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicar.spring.entity.Estimate;
import tn.enicar.spring.services.interfaces.IEstimateService;

@RestController
@RequestMapping("/provider")
@PreAuthorize("hasRole('ROLE_provider')")
public class ProviderController {
	@Autowired
	IEstimateService iEstimateService;
	
		@PostMapping("/addEstimate/{providerId}/{kinderId}/{item}/{qte}/{total}")
		@ResponseBody
		public void addEstimate(@PathVariable("providerId") int providerId,
				@PathVariable("kinderId") int kinderId,@PathVariable("item") String item,
				@PathVariable("qte") int qte,@PathVariable("total") double total) {
			iEstimateService.addEstimate(providerId, kinderId, item, qte, total);
		}
		@GetMapping(value = "/getAllEstimate")
		@ResponseBody
		public List<Estimate> getAllEstimate() {

			return iEstimateService.getAllEstimate();
		}
		@GetMapping(value = "/getEstimateByKinderAndProvider/{kinderId}/{ProviderId}")
		@ResponseBody
		public List<Estimate> getEstimateByKinderAndProvider(@PathVariable("kinderId") int kinderId,@PathVariable("ProviderId")  int ProviderId) {
			return iEstimateService.getEstimateByKinderAndProvider(kinderId, ProviderId);
		}
		
		@PutMapping(value = "/updateEstimate/{estimateDate}/{iduser}/{idkinder}")
		@ResponseBody
		public void updateEstimate(@PathVariable("estimateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date estimateDate
				,@PathVariable("iduser") int iduser
				, @PathVariable("idkinder")int idkinder, @RequestBody Estimate estimate) {
			iEstimateService.updateEstimate(estimateDate, iduser, idkinder, estimate.getItem(), estimate.getQte(), estimate.getTotal());

		}
		
		@DeleteMapping("/deleteEstimate/{estimateDate}/{iduser}/{idkinder}")
		@ResponseBody
		public void deleteEstimate(@PathVariable("estimateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date estimateDate
				,@PathVariable("iduser") int iduser
				, @PathVariable("idkinder")int idkinder) {
			iEstimateService.deleteEstimate(estimateDate, iduser, idkinder);
			
		}
}
