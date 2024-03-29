package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import object.Conference;
import object.Participant;

public class ConferenceService {

	public static String TABLE_CONFERENCE_ID= "conference_id";
	public static String TABLE_CONFERENCE_NAME = "conference_name";
	public static String TABLE_DATE = "date";
	public static String TABLE_TIME_START = "time_start";
	public static String TABLE_TIME_END = "time_end";
	public static String TABLE_DESCRIPTION = "description";
	public static String TABLE_PASSWORD = "password";
	public static String TABLE_PARTICIPANT = "participant";
	public static String TABLE_STATUS = "status";
	
	private SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	private Session session;
	
	public ConferenceService() 
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Conference> getConferences()
	{
		session = sessionFactory.openSession();
	    ArrayList<Conference> ConferencesList = (ArrayList<Conference>)session.createCriteria(Conference.class).list();
	    session.close();
		return ConferencesList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Conference> getConferencesByHostId(int host_id)
	{
		session = sessionFactory.openSession();
	    ArrayList<Conference> ConferencesList = (ArrayList<Conference>)session.createCriteria(Conference.class)
	    		.add(Restrictions.eq("host_id", host_id))
	    		.add(Restrictions.eq(TABLE_STATUS, 1))
	    		.list();
	    session.close();
		return ConferencesList;
	}
	
	public Boolean createConference(Conference tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.save(tmp);
		ts.commit();
		session.close();
		return ts.wasCommitted();
	}
	
	public Boolean createListConference(ArrayList<Conference> tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		for(Conference conference : tmp) {
			session.save(conference);
		}
		ts.commit();
		session.close();
		return ts.wasCommitted();
	}
	
	@SuppressWarnings("unchecked")
	public int getLastConferenceId(int host_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Conference> conferences = (ArrayList<Conference>)session.createCriteria(Conference.class)
				.add(Restrictions.eq("host_id", host_id))
				.addOrder(Order.desc(TABLE_CONFERENCE_ID))
				.list();
		System.out.println(conferences.get(0).getConference_id());
		return conferences.get(0).getConference_id();
	}
	
	@SuppressWarnings("unchecked")
	public Conference getConferencesById(int conference_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Conference> result = (ArrayList<Conference>)session.createCriteria(Conference.class)
				.add(Restrictions.eq(TABLE_CONFERENCE_ID, conference_id))
				.add(Restrictions.eq("status", 1))
				.list();
		session.close();
		return result.get(0);
	}
	
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Conference> getInvitedConferences(int user_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> listOfParticipant = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq("user_id", user_id))
				.add(Restrictions.eq("status", 1))
				.list();
		ArrayList<Conference> result = new ArrayList<Conference>();
		for(int i=0;i<listOfParticipant.size();i++)
		{
			ArrayList<Conference> listOfConference = (ArrayList<Conference>)session.createCriteria(Conference.class)
				.add(Restrictions.eq("conference_id", listOfParticipant.get(i).getConference_id()))
				.add(Restrictions.eq("status", 1))
				.list();
			result.addAll(listOfConference);
		}
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void deleteConference(int conference_id)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		List<Conference> result = session.createCriteria(Conference.class)
			.add(Restrictions.eq("conference_id", conference_id))
			.add(Restrictions.eq("status", 1))
			.list();
		result.get(0).setStatus(0);
		session.update(result.get(0));
		ts.commit();
		session.close();
	}
	
	public void deleteConferene(Conference conference)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		conference.setStatus(0);
		session.update(conference);
		ts.commit();
		session.close();
	}
	
	public void updateInfo(Conference conference)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.update(conference);
		ts.commit();
		session.close();
	}
	
	
}
