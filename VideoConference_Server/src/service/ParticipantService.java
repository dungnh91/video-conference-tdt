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
	private static String TABLE_Participant_ID= "participant_id";
	
	public ParticipantService() 
	{

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Participant> getParticipant()
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_STATUS, 1))
				.list();
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
	
	
	public Boolean createParticipant(ArrayList<Participant> tmp)
	{
		ConferenceService confSer = new ConferenceService();
		int lastId = confSer.getLastConferenceId(tmp.get(0).getHost_id());
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		for (Participant participant: tmp) {
			participant.setConference_id(lastId);
			System.out.println(participant);
			session.save(participant);
		}
		ts.commit();
		session.close();
		return ts.wasCommitted();
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Participant> getParticipantByConferenceId(int conference_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, conference_id))
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
	
	@SuppressWarnings("unchecked")
	public void UpdateStatusForParticipant(Participant tmp)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		ArrayList<Participant> t = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, tmp.getConference_id()))
				.add(Restrictions.eq(UserService.TABLE_USER_ID, tmp.getUser_id())).list();
		if(t.get(0).getStatus()==0)
			t.get(0).setStatus(1);
		else
			t.get(0).setStatus(0);
		session.update(t.get(0));
		ts.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Participant> getDeletedParticipantsByConferenceId(int conference_id)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_STATUS, 0))
				.list();
		session.close();
		return participants;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean ParticipantIsExist(Participant part)
	{
		Session session = sessionFactory.openSession();
		ArrayList<Participant> participants = (ArrayList<Participant>)session.createCriteria(Participant.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, part.getConference_id()))
				.add(Restrictions.eq(UserService.TABLE_USER_ID, part.getUser_id()))
				.list();
		session.close();
		System.out.println(participants.size());
		return participants.size()>0?true:false;
	}
	
	public Boolean updateParticipants(ArrayList<Participant> participant)
	{
		Boolean flag= false;
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		ArrayList<Participant> oldPar = getParticipantByConferenceId(participant.get(0).getConference_id());
		for (Participant new_part : participant)
		{
			flag=false;
			for (Participant old_part : oldPar)
			{
				if(new_part.getUser_id() == old_part.getUser_id())
					{
						flag=true;
						break;
					}
			}
			if(flag==false)
				{
					if(ParticipantIsExist(new_part))
					{
						UpdateStatusForParticipant(new_part);
					}
					else
						createParticipant(new_part);
				}
		}
		
		
		for (Participant old_part : oldPar)
		{
			flag=false;
			for (Participant new_part : participant)
			{
				if(new_part.getUser_id() == old_part.getUser_id())
					{
						flag=true;
						break;
					}
			}
			if(flag==false)
				UpdateStatusForParticipant(old_part);
		}
		
		ts.commit();
		session.close();
		return ts.wasCommitted();
	}
}
