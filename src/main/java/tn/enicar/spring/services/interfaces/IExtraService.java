package tn.enicar.spring.services.interfaces;

import java.util.List;

import tn.enicar.spring.entity.Extra;

public interface IExtraService {
	public int addExtra(Extra extra,int idk);
	public void updateExtra(String description,double price,int ExtraId);
	public List<Extra> getAllextra();
	public void deleteExtraById(int extraId);
	public Extra getExtraById(int extraId);
	public void affecterExtraAkinderGarten(int extraId, int kinderId);
	public List<Extra> findAllExtraByKinderGarten(int kinderId) ;
}
