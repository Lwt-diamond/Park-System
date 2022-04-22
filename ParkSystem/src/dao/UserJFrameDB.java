package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserJFrameDB {

	public static void updateUserInfo(String userName, String userPassword) {
		Connection conn = DBUtil.getDBConnection();
        String sql = "update  parkUser set userPassword=? where userName=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userPassword);
            ps.setString(2,userName);
            int count = ps.executeUpdate();
            System.out.println("UpdateUSER:"+count+"  ");
         
            DBUtil.release(conn,ps,null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
		
	}

}
