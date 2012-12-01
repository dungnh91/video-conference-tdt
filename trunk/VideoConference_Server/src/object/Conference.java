package object;

import java.sql.Date;
import java.sql.Time;

public class Conference {
	private int conference_id;
	private int host_id;
	private String conference_name;
	private Date date;
	private Time time_start;
	private Time time_end;
	private String description;
	private String password;
	private int participant;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private int status;
	
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
	public void setConference_id(int id){this.conference_id = id;}
	public void setHost_id(int id){this.host_id = id;}
	public void setConference_name(String name){this.conference_name = name;}
	public void setDate(Date date){this.date = date;}
	public void setTime_start(Time time_start){this.time_start = time_start;}
	public void setTime_end(Time time_end){this.time_end = time_end;}
	public void setPassword(String password){this.password = password;}
	public void setParticipant(int participant){this.participant = participant;}
	
	public int getConference_id(){ return this.conference_id;}
	public int getHost_id(){return this.host_id;}
	public String getConference_name(){ return this.conference_name;}
	public Date getDate(){ return this.date;}
	public Time getTime_start(){ return this.time_start;}
	public Time getTime_end(){ return this.time_end;}
	public String getPassword(){ return this.password;}
	public int getParticipant(){ return this.participant;}
}
