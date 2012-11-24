package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.User;

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
				tmp.setSubcriptions(rs.getInt(TABLE_SUBCRIPTIONS));
				UserList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserList;
	}
	
	public Boolean createUser(User tmp)
	{
		try {
			String sql = "INSERT INTO tbl_vc_user ("+ TABLE_FULL_NAME + TABLE_USER_NAME + TABLE_PASSWORD 
					+ TABLE_EMAIL + TABLE_AVARTAR + TABLE_SUBCRIPTIONS + ")" +
					"values (N'" + tmp.getFullName() + "','" + tmp.getUser_name()  + "','" + tmp.getPassword() + "','" +
					tmp.getEmail() + "','" + tmp.getAvartar() + "','" + tmp.getSubcriptions() + "')"; 
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public User getUser(String UserName)
	{
		try {
			User tmp = new User();
			String sql = "SELECT * FROM tbl_vc_user WHERE "+ TABLE_USER_NAME+" = '"+ UserName +"'";
			rs = stm.executeQuery(sql);
			rs.next();
			tmp.setUser_id(rs.getInt(TABLE_USER_ID));
			tmp.setUser_name(UserName);
			tmp.setFullName(rs.getString(TABLE_FULL_NAME));
			tmp.setPassword(rs.getString(TABLE_PASSWORD));
			tmp.setSubcriptions(rs.getInt(TABLE_SUBCRIPTIONS));
			tmp.setEmail(rs.getString(TABLE_EMAIL));
			tmp.setAvartar(rs.getString(TABLE_AVARTAR));
			return tmp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String getPassword(String UserName)
	{		
		try {
			String sql ="SELECT password FROM tbl_vc_user WHERE "+ TABLE_USER_NAME + " = '" + UserName +"'";
			rs = stm.executeQuery(sql);
			rs.next();
			return rs.getString(TABLE_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Boolean updateInfo(User user)
	{
		try {
			String sql =String.format("UPDATE tbl_vc_user SET " + TABLE_PASSWORD + " = '{0}', " + TABLE_EMAIL + " = '{1}'," + TABLE_AVARTAR + " = '{2}',"
					+ TABLE_SUBCRIPTIONS + " = '{3}'," +TABLE_FULL_NAME + " = N'{4}' WHERE " + TABLE_USER_ID + " = '{5}'"
					,user.getPassword(),user.getEmail(),user.getAvartar(),user.getSubcriptions(),user.getFullName(), user.getUser_id());
			if(stm.execute(sql))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<User> getInvitedUsers(int conference_id)
	{
		ArrayList<User> Users = new ArrayList<User>();
		try {
			
			String sql ="SELECT * FROM tbl_vc_user WHERE "+ TABLE_USER_ID + " IN " + "(SELECT "+TABLE_USER_ID + " FROM tbl_vc_participant WHERE " + ConferenceService.TABLE_CONFERENCE_ID + " = '" + conference_id+ "' AND " + ConferenceService.TABLE_STATUS + " = '1')";
			rs = stm.executeQuery(sql);
			while(rs.next())
			{
				User tmp = new User();
				tmp.setUser_id(rs.getInt(TABLE_USER_ID));
				tmp.setUser_name(rs.getString(TABLE_USER_NAME));
				tmp.setAvartar(rs.getString(TABLE_AVARTAR));
				tmp.setEmail(rs.getString(TABLE_EMAIL));
				tmp.setFullName(rs.getString(TABLE_FULL_NAME));
				tmp.setSubcriptions(rs.getInt(TABLE_SUBCRIPTIONS));
				Users.add(tmp);
			}
			return Users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
