package project.java.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.java.object.Participant;

public class ParticipantService {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	private static String TABLE_Participant_ID= "Participant_id";
	
	public ParticipantService() 
	{
		init();
	}
	
	public ArrayList<Participant> getParticipant()
	{
		ArrayList<Participant> ParticipantList = new ArrayList<Participant>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_participant");
			while(rs.next())
			{
				Participant tmp = new Participant();
				tmp.setParticipant_id(rs.getInt(TABLE_Participant_ID));
				tmp.setUser_id(rs.getInt(UserService.TABLE_USER_ID));
				tmp.setConference_id(rs.getInt(ConferenceService.TABLE_CONFERENCE_ID));
				tmp.setStatus(rs.getInt(ConferenceService.TABLE_STATUS));
				tmp.setModify(rs.getString(UserService.TABLE_MODIFY));
				ParticipantList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ParticipantList;
	}
	
	public Boolean insertParticipant(Participant tmp)
	{
		try {
			String sql = "INSERT INTO test ("+ UserService.TABLE_USER_ID + ConferenceService.TABLE_CONFERENCE_ID + ConferenceService.TABLE_STATUS 
					+ UserService.TABLE_MODIFY + ")" +
					"values ('" + tmp.getUser_id() + "','" + tmp.getConference_id()  + "','" + tmp.getStatus() + "','" +
					tmp.getModify()+ "')"; 
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
