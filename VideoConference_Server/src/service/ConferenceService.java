package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import object.Conference;
import object.Participant;

public class ConferenceService {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	public static String TABLE_CONFERENCE_ID= "conference_id";
	public static String TABLE_CONFERENCE_NAME = "conference_name";
	public static String TABLE_DATE = "date";
	public static String TABLE_TIME_START = "time_start";
	public static String TABLE_TIME_END = "time_end";
	public static String TABLE_DESCRIPTION = "description";
	public static String TABLE_PASSWORD = "password";
	public static String TABLE_PARTICIPANT = "participant";
	public static String TABLE_STATUS = "status";
	
	SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	Session session;
	
	public ConferenceService() 
	{
		
	}
	
	public ArrayList<Conference> getConferences()
	{
		session = sessionFactory.openSession();
		Query query = session.createQuery("from Conference");
	    List l = query.list();
	    Iterator it = l.iterator();

	    ArrayList<Conference> ConferencesList = new ArrayList<Conference>();
	    Conference conference = null;
	    while (it.hasNext()) {
	        conference = (Conference)it.next();
	        ConferencesList.add(conference);
	    }           
	    session.close();
		return ConferencesList;
	}
	
	public void createConference(Conference tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.saveOrUpdate(tmp);
		ts.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Conference> getConferences(int user_id)
	{
		Session session = sessionFactory.openSession();
		Conference conference = (Conference)session.get(Conference.class, user_id);
		List<Conference> result = session.createCriteria(Conference.class)
				.add(Restrictions.eq("user_id", conference.getUser_id()))
				.add(Restrictions.eq("status", "1"))
				.list();
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public List<Conference> getInvitedConferences(int user_id)
	{
		Session session = sessionFactory.openSession();
		Participant participant = (Participant)session.get(Participant.class, user_id);
		List<Participant> listOfParticipant = session.createCriteria(Participant.class)
				.add(Restrictions.eq("user_id", participant.getUser_id()))
				.add(Restrictions.eq("status", "1"))
				.list();
		List<Conference> result = null;
		for(int i=0;i<listOfParticipant.size();i++)
		{
			Conference conference = (Conference)session.get(Conference.class, listOfParticipant.get(i).getConference_id());
			List<Conference> listOfConference = session.createCriteria(Conference.class)
				.add(Restrictions.eq("user_id", conference.getUser_id()))
				.add(Restrictions.eq("status", "1"))
				.list();
			result.addAll(listOfConference);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void deleteConference(int conference_id)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Conference conference = (Conference)session.get(Conference.class, conference_id);
		List<Conference> result = session.createCriteria(Conference.class)
			.add(Restrictions.eq("conference_id", conference.getConference_id()))
			.add(Restrictions.eq("status", "1"))
			.list();
		result.get(0).setStatus(0);
		session.delete(result);
		ts.commit();
		session.close();
	}
	
	public void updateInfo(Conference conference)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.saveOrUpdate(conference);
		ts.commit();
		session.close();
	}
	
	
}
