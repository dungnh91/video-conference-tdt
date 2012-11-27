package service;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import object.Conference;
import object.User;

public class UserService {

	public static String TABLE_USER_ID = "user_id";
	public static String TABLE_FULL_NAME = "fullname";
	public static String TABLE_USER_NAME = "user_name";
	public static String TABLE_PASSWORD = "password";
	public static String TABLE_EMAIL = "email";
	public static String TABLE_AVARTAR = "avartar";
	public static String TABLE_SUBCRIPTIONS = "subcriptions";
	public static String TABLE_MODIFY = "modify";
	
	public UserService() 
	{
	}
		
	@SuppressWarnings("unchecked")
	public ArrayList<User> getUser()
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		ArrayList<User> UserList = (ArrayList<User>)session.createCriteria(User.class).list();
		session.close();
		return UserList;
	}
	
	public void createUser(User tmp)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.save(tmp);
		ts.commit();
		session.close();		
	}
	
	@SuppressWarnings("unchecked")
	public User getUser(String UserName)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		ArrayList<User> user = (ArrayList<User>)session.createCriteria(User.class)
				.add(Restrictions.eq(UserService.TABLE_USER_NAME, UserName)).list();
		session.close();
		return user.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public String getPassword(String UserName)
	{		
		System.out.println(UserName);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		ArrayList<User> password = (ArrayList<User>)session.createCriteria(User.class)
				.add(Restrictions.eq(UserService.TABLE_USER_NAME, UserName)).list();
		System.out.println(password.get(0).getPassword());
		session.close();
		return password.get(0).getPassword();
	}
	
	public void updateInfo(User user)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.update(user);
		ts.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> getInvitedUsers(int conference_id)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		ArrayList<Conference> ConferenceList = (ArrayList<Conference>)session.createCriteria(Conference.class)
				.add(Restrictions.eq(ConferenceService.TABLE_CONFERENCE_ID, conference_id)).list();
		ArrayList<User> UserList = new ArrayList<User>();
		for(int i=0;i<ConferenceList.size();i++)
		{
			UserList.add((User)session.get(User.class, ConferenceList.get(i).getUser_id()));
		}
		session.close();
		return UserList;
	}
	
	public void deleteUser(User user)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.delete(user);
		ts.commit();
		session.close();
	}
}
