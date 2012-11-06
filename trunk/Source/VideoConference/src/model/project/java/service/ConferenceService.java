package project.java.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.java.object.Conference;

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
	
	public ConferenceService() 
	{
		init();
	}
	
	public ArrayList<Conference> getConference()
	{
		ArrayList<Conference> ConferenceList = new ArrayList<Conference>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_conference");
			while(rs.next())
			{
				Conference tmp = new Conference();
				tmp.setConference_id(rs.getInt(TABLE_CONFERENCE_ID));
				tmp.setConference_name(rs.getString(TABLE_CONFERENCE_NAME));
				tmp.setDate(rs.getDate(TABLE_DATE));
				tmp.setTime_start(rs.getTime(TABLE_TIME_START));
				tmp.setTime_end(rs.getTime(TABLE_TIME_END));
				tmp.setDesription(rs.getString(TABLE_DESCRIPTION));
				tmp.setPassword(rs.getString(TABLE_PASSWORD));
				tmp.setParticipant(rs.getInt(TABLE_PARTICIPANT));
				tmp.setStatus(rs.getInt(TABLE_STATUS));
				tmp.setModify(rs.getString(UserService.TABLE_MODIFY));
				ConferenceList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConferenceList;
	}
	
	public Boolean insertConference(Conference tmp)
	{
		try {
			String sql = "INSERT INTO test ("+ TABLE_CONFERENCE_NAME + TABLE_DATE + TABLE_TIME_START 
					+ TABLE_TIME_END + TABLE_DESCRIPTION + TABLE_PASSWORD + TABLE_PARTICIPANT + TABLE_STATUS 
					+UserService.TABLE_MODIFY+ ")" +
					"values ('" + tmp.getConference_name() + "','" + tmp.getDate()  + "','" + tmp.getTime_start() + "','" +
					tmp.getTime_end() + "','" + tmp.getDesription() + "','" + tmp.getPassword() + "','" 
					+ tmp.getParticipant() + "','" + tmp.getStatus() + "','" + tmp.getModify()+ "')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
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
}
