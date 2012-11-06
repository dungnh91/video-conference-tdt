package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.java.object.User;

public class UserService {

	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	
	public static String TABLE_USER_ID = "user_id";
	public static String TABLE_FULL_NAME = "fullname";
	public static String TABLE_USER_NAME = "user_name";
	public static String TABLE_PASSWORD = "password";
	public static String TABLE_EMAIL = "email";
	public static String TABLE_AVARTAR = "avartar";
	public static String TABLE_SUBCRIPTIONS = "subcriptions";
	public static String TABLE_MODIFY = "modify";
	
	public UserService() 
	{
		init();
	}
	
	public ArrayList<User> getUser()
	{
		ArrayList<User> UserList = new ArrayList<User>();
		try {
			rs = stm.executeQuery("SELECT * FROM tbl_vc_user");
			while(rs.next())
			{
				User tmp = new User();
				tmp.setUser_id(rs.getInt(TABLE_USER_ID));
				tmp.setFullName(rs.getString(TABLE_FULL_NAME));
				tmp.setUser_name(rs.getString(TABLE_USER_NAME));
				tmp.setPassword(rs.getString(TABLE_PASSWORD));
				tmp.setEmail(rs.getString(TABLE_EMAIL));
				tmp.setAvartar(rs.getString(TABLE_AVARTAR));
				tmp.setSubcriptions(rs.getString(TABLE_SUBCRIPTIONS));
				tmp.setModify(rs.getString(TABLE_MODIFY));
				UserList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserList;
	}
	
	public Boolean insertUser(User tmp)
	{
		try {
			String sql = "INSERT INTO test ("+ TABLE_FULL_NAME + TABLE_USER_NAME + TABLE_PASSWORD 
					+ TABLE_EMAIL + TABLE_AVARTAR + TABLE_SUBCRIPTIONS + TABLE_MODIFY+ ")" +
					"values ('" + tmp.getFullName() + "','" + tmp.getUser_name()  + "','" + tmp.getPassword() + "','" +
					tmp.getEmail() + "','" + tmp.getAvartar() + "','" + tmp.getSubcriptions() + "','" + tmp.getModify()+ "')"; 
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
