package object;

public class Participant {
	private int participant_id;
	private int host_id;
	private int user_id;
	private int conference_id;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setParticipant_id(int participant_id){this.participant_id = participant_id;}
	public void setUser_id(int user_id){this.user_id = user_id;}
	public void setConference_id(int conference_id){this.conference_id = conference_id;}
	
	public int getParticipant_id(){ return this.participant_id;}
	public int getUser_id(){ return this.user_id;}
	public int getConference_id(){ return this.conference_id;}
	public int getHost_id() {
		return host_id;
	}
	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}
	
}
