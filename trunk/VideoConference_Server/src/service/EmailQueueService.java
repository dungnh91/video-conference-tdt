package service;

import object.EmailQueue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmailQueueService {

	private SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	private Session session;
	
	public EmailQueueService()
	{
		
	}
	public Boolean createEmailQueue(EmailQueue email)
	{
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.saveOrUpdate(email);
		ts.commit();
		session.close();
		return ts.wasCommitted();
	}
	
}
