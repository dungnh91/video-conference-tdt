package project.java.object;

public class User {
	private int user_id;
	private String fullname;
	private String user_name;
	private String password;
	private String email;
	private String avartar;
	private String subcriptions;
	private String modify;
	
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public void setFullName(String fullName)
	{
		this.fullname = fullName;
	}
	public void setUser_name(String username)
	{
		this.user_name = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setAvartar(String ava)
	{
		this.avartar = ava;
	}
	public void setSubcriptions(String sub)
	{
		this.subcriptions = sub;
	}
	public void setModify(String modify)
	{
		this.modify = modify;
	}
	
	public int getUser_id()
	{
		return user_id;
	}
	public String getFullName()
	{
		return this.fullname;
	}
	public String getUser_name()
	{
		return this.user_name;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getAvartar()
	{
		return this.avartar;
	}
	public String getSubcriptions()
	{
		return this.subcriptions;
	}
	public String getModify()
	{
		return this.modify;
	}
}
