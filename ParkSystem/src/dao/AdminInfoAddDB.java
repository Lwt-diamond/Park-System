package dao;

import entity.ParkAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminInfoAddDB {
    public static void main(String[] args) {
        //AdminInfoAddDB.addAdminInfo(new ParkAdmin("1","1","1","2021-07-08","1","1","1","1"));
    }
    /**
     * 添加管理员信息
     * @param parkAdmin
     */
    public static void addAdminInfo(ParkAdmin parkAdmin) {
        Connection conn = DBUtil.getDBConnection();
        String sql = "insert into  parkAdmin values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,parkAdmin.getUserName());
            ps.setString(2,parkAdmin.getAdminName());
            ps.setString(3,parkAdmin.getAdminID());
            ps.setString(4,parkAdmin.getAdminBirthday());
            ps.setString(5,parkAdmin.getAdminSex());
            ps.setString(6,parkAdmin.getAdminAddress());
            ps.setString(7,parkAdmin.getAdminTel());
            ps.setString(8,parkAdmin.getAdminPhoto());


            int count = ps.executeUpdate();
            System.out.println("注册Admin："+count+"  ");
            //System.out.println(parkUser);
            DBUtil.release(conn,ps,null);
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
    }
}
