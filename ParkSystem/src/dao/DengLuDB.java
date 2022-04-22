package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DengLuDB {
	/**
	 * 判断数据库中是否存在该用户名和密码
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public static boolean Login(String userName,String userPassword)
	{
		boolean isok = false;
		
		Connection conn = DBUtil.getDBConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select count(*) from parkUser where userName=? and userPassword=?");
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int i = rs.getInt(1);
				if(i>0)
				{
					isok=true;
				}
			}
			DBUtil.release(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 * 从数据库中查找该用户名的权限
	 * @param userName
	 * @return
	 */
	public static String getUserRight(String userName)
	{
		String right="";
		Connection conn = DBUtil.getDBConnection();
		try {
			PreparedStatement ps = 	conn.prepareStatement("select userRight from parkUser where userName=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				right = rs.getString(1);
				return right;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return right;
	}
	
	
	
}
