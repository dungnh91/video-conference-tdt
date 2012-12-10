package object;

public class EmailQueue {

	private int email_queue_id;
	private String fromemail;
	private String toemail;
	private String message;
	private String subject;
	private int status;
	public int getEmail_queue_id() {
		return email_queue_id;
	}
	public void setEmail_queue_id(int email_queue_id) {
		this.email_queue_id = email_queue_id;
	}
	public String getFromemail() {
		return fromemail;
	}
	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}
	public String getToemail() {
		return toemail;
	}
	public void setToemail(String toemail) {
		this.toemail = toemail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
