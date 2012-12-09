package service;

import object.EmailQueue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmailQueueService {

	public static String TABLE_EMAIL_QUEUE_ID = "email_queue_id";
	public static String TABLE_FROM = "from";
	public static String TABLE_TO="to";
	public static String TABLE_MESSAGE = "message";
	public static String TABLE_SUBJECT  = "subject";
	public static String TABLE_HEADERS = "headers";
	public static String TABLE_CC = "cc";
	public static String TABLE_BCC= "bcc";
	public static String TABLE_DESCRIPTIONS = "descriptions";
	public static String TABLE_IP_ADD = "ip_add";
	public static String TABLE_STATUS = "status";

	private SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	private Session session;
	
	public Boolean createEmailQueue(EmailQueue email)
	{
		session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.save(email);
		ts.commit();
		session.close();
		return ts.wasCommitted();
	}
	
}
