package project.java.object;

import java.sql.Date;
import java.sql.Time;

public class Conference {
	private int conference_id;
	private String conference_name;
	private Date date;
	private Time time_start;
	private Time time_end;
	private String description;
	private String password;
	private int participant;
	private int status;
	private String modify;
	
	public void setConference_id(int id){this.conference_id = id;}
	public void setConference_name(String name){this.conference_name = name;}
	public void setDate(Date date){this.date = date;}
	public void setTime_start(Time time_start){this.time_start = time_start;}
	public void setTime_end(Time time_end){this.time_end = time_end;}
	public void setDesription(String description){this.description = description;}
	public void setPassword(String password){this.password = password;}
	public void setParticipant(int participant){this.participant = participant;}
	public void setStatus(int status){this.status = status;}
	public void setModify(String modify){this.modify = modify;}

	
	public int getConference_id(){ return this.conference_id;}
	public String getConference_name(){ return this.conference_name;}
	public Date getDate(){ return this.date;}
	public Time getTime_start(){ return this.time_start;}
	public Time getTime_end(){ return this.time_end;}
	public String getDesription(){ return this.description;}
	public String getPassword(){ return this.password;}
	public int getParticipant(){ return this.participant;}
	public int getStatus(){ return this.status;}
	public String getModify(){ return this.modify;}
}
