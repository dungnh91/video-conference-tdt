package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.object.User;

public class EmailqueueService {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	//public static String TABLE_EMAIL_ID = "email_queue_id";
	public static String TABLE_FROM = "from";
	public static String TABLE_TO = "to";
	public static String TABLE_MESSAGE = "message";
	public static String TABLE_SUBJECT = "subject";
	public static String TABLE_HEADERS = "headers";
	public static String TABLE_CC = "cc";
	public static String TABLE_BCC = "bcc";
	public static String TABLE_DESCRIPTIONS = "descriptions";
	
	public EmailqueueService() 
	{
		init();
	}
	
	private void init()
	{
		try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/video_conference","root","");
            stm = conn.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public ArrayList<Email> getEmailqueue()
	{
		ArrayList<Email> EmailList = new ArrayList<Email>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_emailqueue");
			while(rs.next())
			{
				User tmp = new Emailqueue();
				//tmp.setUser_id(rs.getInt(TABLE_USER_ID));
				tmp.setFrom(rs.getString(TABLE_FROM));
				tmp.setTo(rs.getString(TABLE_TO));
				tmp.setMessage(rs.getString(TABLE_MESSAGE));
				tmp.setSubject(rs.getString(TABLE_SUBJECT));
				tmp.setHeader(rs.getString(TABLE_HEADERS));
				tmp.setCc(rs.getInt(TABLE_CC));
				tmp.setBcc(rs.getInt(TABLE_BCC));
				tmp.setDescription(rs.getInt(TABLE_DESCRIPTIONS));
				EmailList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserList;
	}
	
	public Boolean createEmailqueue(Emailqueue tmp)
	{
		try {
			String sql = "INSERT INTO tbl_vc_emailqueue ("+ TABLE_FROM + TABLE_TO + TABLE_MESSAGE 
					+ TABLE_SUBJECT + TABLE_HEADERS + TABLE_CC + TABLE_BCC + TABLE_DESCRIPTIONS ")" +
					"values ('" + tmp.getFrom() + "','" + tmp.getTo()  + "',N'" + tmp.getMessage() + "',N'" +
					tmp.getSubject() + "',N'" + tmp.getHeader() + "','" + tmp.getCc() + "','" + tmp.getBcc()+ "',N'" + tmp.getDescription() + "')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
}
