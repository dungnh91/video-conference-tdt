package object;

public class EmailQueue {

	private int email_queue_id;
	private String from;
	private String to;
	private String message;
	private String subject;
	private String headers;
	private String cc;
	private String bcc;
	private String descriptions;
	private String ip_add;
	private int status;
	public int getEmail_queue_id() {
		return email_queue_id;
	}
	public void setEmail_queue_id(int email_queue_id) {
		this.email_queue_id = email_queue_id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getIp_add() {
		return ip_add;
	}
	public void setIp_add(String ip_add) {
		this.ip_add = ip_add;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
