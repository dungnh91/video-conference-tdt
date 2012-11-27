package service;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import object.Participant;

public class ParticipantService {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private static String TABLE_Participant_ID= "Participant_id";
	
	public ParticipantService() 
	{

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Participant> getParticipant()
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class).list();
		session.close();
		return participants;
	}
	
	public void createParticipant(Participant tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		tmp.setStatus(1);
		session.save(tmp);
		ts.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Participant> getParticipant(int participant_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(TABLE_Participant_ID, participant_id))
				.add(Restrictions.eq(ConferenceService.TABLE_STATUS,1)).list();
		session.close();
		return participants;
	}
	
	public void deleteParticipant(Participant participant)
	{
		Session session = sessionFactory.openSession();
		participant.setStatus(0);
		Transaction ts = session.beginTransaction();
		session.update(participant);
		ts.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public void deleteParticipant(int conference_id, int user_id)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		ArrayList<Participant> participant = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, conference_id))
				.add(Restrictions.eq(UserService.TABLE_USER_ID, user_id))
				.list();
		participant.get(0).setStatus(0);
		session.update(participant.get(0));
		ts.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public void deleteParticipant(int conference_id)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, conference_id))
				.list();
		for(int i=0;i<participants.size();i++)
		{
			participants.get(i).setStatus(0);
			session.update(participants.get(i));
		}
		ts.commit();
		session.close();
	}
	
	public void updateParticipant(Participant tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.update(tmp);
		ts.commit();
		session.close();
	}
}
