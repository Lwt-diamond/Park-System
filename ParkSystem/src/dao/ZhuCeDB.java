package dao;

import entity.ParkUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZhuCeDB {
    /**
     * 注册用户（用户名，密码，权限）
     * @param parkUser
     */
    public static void createUser(ParkUser parkUser) {
        Connection conn = DBUtil.getDBConnection();
        String sql = "insert into  parkUser values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,parkUser.getUserName());
            ps.setString(2,parkUser.getUserPassword());
            ps.setString(3,parkUser.getUserRight());
            int count = ps.executeUpdate();
            System.out.println("注册USER："+count+"  ");
            System.out.println(parkUser);
            DBUtil.release(conn,ps,null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
