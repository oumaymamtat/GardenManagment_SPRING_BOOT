package tn.enicar.spring.services.interfaces;

import java.util.List;

public interface IStatisticsService {

	public List<?> listChildByKinderGarten();

	public List<?> NbrCommentsByUser ();

	public List<?> NbrLikeByUser();

	public List<?> nbrPublicationByUser();

	public List<?> numberParticipEventKinderGaten();

	public List<?> NbrChildSubscribed(int year);
	
	public void UpdateScoreEvaluation(int idkg);
	
	
}
