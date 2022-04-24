package tn.enicar.spring.services.interfaces;

import java.util.List;

import tn.enicar.spring.entity.Club;


public interface IClubService {
	
	public void addClub(Club club, int id);
	public void updateClub(String description,int clubId);
	public List<Club> getAllclub();
	public void deleteClubById(int clubId);
	public Club getClubById(int clubId);
	public void affecterClubACategory(int clubId, int categoryId);
}
