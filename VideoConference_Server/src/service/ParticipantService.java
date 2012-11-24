package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.Participant;

public class ParticipantService {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	private static String TABLE_Participant_ID= "Participant_id";
	
	public ParticipantService() 
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
	
	public ArrayList<Participant> getParticipant()
	{
		ArrayList<Participant> ParticipantList = new ArrayList<Participant>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_participant AND " + ConferenceService.TABLE_STATUS + " = '1'");
			while(rs.next())
			{
				Participant tmp = new Participant();
				tmp.setParticipant_id(rs.getInt(TABLE_Participant_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_id(rs.getInt(ConferenceService.TABLE_CONFERENCE_ID));
				ParticipantList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ParticipantList;
	}
	
	public Boolean createParticipant(Participant tmp)
	{
		try {
			String sql = "INSERT INTO tbl_vc_participant ("+ UserService.TABLE_USER_ID + ConferenceService.TABLE_CONFERENCE_ID + ")" 
					+"values ('" + tmp.getUser_id() + "','" + tmp.getConference_id() +"')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public ArrayList<Participant> getParticipant(int conference_id)
	{
		ArrayList<Participant> ParticipantList = new ArrayList<Participant>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_participant WHERE " + ConferenceService.TABLE_CONFERENCE_ID + " = '"+ conference_id + "' AND "+ ConferenceService.TABLE_STATUS +"= '1'" );
			while(rs.next())
			{
				Participant tmp = new Participant();
				tmp.setParticipant_id(rs.getInt(TABLE_Participant_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_id(rs.getInt(ConferenceService.TABLE_CONFERENCE_ID));
				ParticipantList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ParticipantList;
	}
	
	public Boolean deleteParticipant(int conference_id, int user_id)
	{
		try {
			String sql = "UPDATE tbl_vc_participant SET "+ ConferenceService.TABLE_STATUS + "='0' WHERE " + ConferenceService.TABLE_CONFERENCE_ID +" = '"+ conference_id + "' AND "+  UserService.TABLE_USER_ID + " = '" + user_id + "'"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean addParticipant(Participant tmp)
	{
		try {
			String sql = "INSERT INTO tbl_vc_participant ("+ UserService.TABLE_USER_ID + ConferenceService.TABLE_CONFERENCE_ID +  ConferenceService.TABLE_STATUS + UserService.TABLE_MODIFY 
					+ ") values ('" + tmp.getUser_id() + "','" + tmp.getConference_id() +"')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
