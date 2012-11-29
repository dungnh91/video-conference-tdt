package model.object

public class Emailqueue
{
	private String from;
	private String to;
	private String message;
	private String subject;
	private String headers;
	private String cc;
	private String bcc;
	private String descriptions;
	
	public void setFrom(String from) {this.from = from;}
	public void setTo(String to) {this.to = to;}
	public void setMessage(String message) {this.message = message;}
	public void setSubject(String subject) {this.subject = subject;}
	public void setHeader(String headers) {this.headers = headers;}
	public void setCc(String cc) {this.cc = cc;}
	public void setBcc(String bcc) {this.bcc = bcc;}
	public void setDescription(String descriptions) {this.descriptions = descriptions;}
	
	public void getFrom() {return this.from;}
	public void getTo() {return this.to;}
	public void getMessage() {return this.message;}
	public void getSubject() {return this.subject;}
	public void getHeader() {return this.headers;}
	public void getCc() {return this.cc;}
	public void getBcc() {return this.bcc;}
	public void getDescription() {return this.descriptions;}
	
}