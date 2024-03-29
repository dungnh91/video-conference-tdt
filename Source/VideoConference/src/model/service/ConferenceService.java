package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.object.Conference;

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
	
	public ArrayList<Conference> getConferences()
	{
		ArrayList<Conference> ConferenceList = new ArrayList<Conference>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_conference");
			while(rs.next())
			{
				Conference tmp = new Conference();
				tmp.setConference_id(rs.getInt(TABLE_CONFERENCE_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_name(rs.getString(TABLE_CONFERENCE_NAME));
				tmp.setDate(rs.getDate(TABLE_DATE));
				tmp.setTime_start(rs.getTime(TABLE_TIME_START));
				tmp.setTime_end(rs.getTime(TABLE_TIME_END));
				tmp.setDesription(rs.getString(TABLE_DESCRIPTION));
				tmp.setPassword(rs.getString(TABLE_PASSWORD));
				tmp.setParticipant(rs.getInt(TABLE_PARTICIPANT));
				ConferenceList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConferenceList;
	}
	
	public Boolean createConference(Conference tmp)
	{
		try {
			String sql = "INSERT INTO tbl_vc_conference ("+ UserService.TABLE_USER_ID + TABLE_CONFERENCE_NAME +  TABLE_DATE + TABLE_TIME_START 
					+ TABLE_TIME_END + TABLE_DESCRIPTION + TABLE_PASSWORD + TABLE_PARTICIPANT + ")" +
					"values ('" + tmp.getUser_id() + "',N'" + tmp.getConference_name() + "','"   + tmp.getDate()  + "','" + tmp.getTime_start() + "','" +
					tmp.getTime_end() + "',N'" + tmp.getDesription() + "','" + tmp.getPassword() + "','" 
					+ tmp.getParticipant() +"')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public ArrayList<Conference> getConferences(int user_id)
	{
		ArrayList<Conference> ConferenceList = new ArrayList<Conference>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_conference WHERE " + UserService.TABLE_USER_ID + " = '" + user_id + "' AND " + TABLE_STATUS + " = '1'");
			while(rs.next())
			{
				Conference tmp = new Conference();
				tmp.setConference_id(rs.getInt(TABLE_CONFERENCE_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_name(rs.getString(TABLE_CONFERENCE_NAME));
				tmp.setDate(rs.getDate(TABLE_DATE));
				tmp.setTime_start(rs.getTime(TABLE_TIME_START));
				tmp.setTime_end(rs.getTime(TABLE_TIME_END));
				tmp.setDesription(rs.getString(TABLE_DESCRIPTION));
				tmp.setPassword(rs.getString(TABLE_PASSWORD));
				tmp.setParticipant(rs.getInt(TABLE_PARTICIPANT));
				ConferenceList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConferenceList;
	}
	
	public ArrayList<Conference> getInvitedConferences(int user_id)
	{
		ArrayList<Conference> ConferenceList = new ArrayList<Conference>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_conference WHERE "+TABLE_CONFERENCE_ID+" IN (SELECT " +TABLE_CONFERENCE_ID+ " FROM tbl_vc_participant WHERE " + UserService.TABLE_USER_ID +
					" = '" + user_id + "' AND CONFERENCE_ID NOT IN (SELECT "+ TABLE_CONFERENCE_ID 
					+" FROM tbl_vc_conference WHERE "+ UserService.TABLE_USER_ID +" = '" + user_id+"')) AND " + TABLE_STATUS + " = '1'");
			while(rs.next())
			{
				Conference tmp = new Conference();
				tmp.setConference_id(rs.getInt(TABLE_CONFERENCE_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_name(rs.getString(TABLE_CONFERENCE_NAME));
				tmp.setDate(rs.getDate(TABLE_DATE));
				tmp.setTime_start(rs.getTime(TABLE_TIME_START));
				tmp.setTime_end(rs.getTime(TABLE_TIME_END));
				tmp.setDesription(rs.getString(TABLE_DESCRIPTION));
				tmp.setPassword(rs.getString(TABLE_PASSWORD));
				tmp.setParticipant(rs.getInt(TABLE_PARTICIPANT));
				ConferenceList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConferenceList;
	}
	
	public Boolean deleteConference(int conference_id)
	{
		try {
			String sql = "UPDATE tbl_vc_conference SET "+ TABLE_STATUS + "='0' WHERE " + TABLE_CONFERENCE_ID +" = '"+ conference_id + "'"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Boolean updateInfo(Conference conference)
	{
		try {
			String sql =String.format("UPDATE tbl_vc_conference SET " + TABLE_CONFERENCE_NAME + " = N'{0}', " + TABLE_DATE + " = '{1}'," + TABLE_DESCRIPTION + " = N'{2}',"
					+ TABLE_PASSWORD + " = '{3}',"+TABLE_TIME_START + " = '{4}', " +  TABLE_TIME_END + " = '{5}'  WHERE " + TABLE_CONFERENCE_ID + " = '{8}'"
					,conference.getConference_name(),conference.getDate(),conference.getDesription(),
					conference.getPassword(),conference.getTime_start(),conference.getTime_end(),conference.getConference_id());
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
