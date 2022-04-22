package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.ParkAdmin;
import entity.ParkUser;



public class AdminJPanelUserInfoDB {

    public static Vector<Vector<String>> getUserInfo() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql="select * from parkUser";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vector<String> v = new Vector<String>();
                v.add(rs.getString(1));//表示是获取第一个字段的值
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector<Vector<String>> findUserInfo(String sql) {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vector<String> v = new Vector<String>();
                v.add(rs.getString(1));//表示是获取第一个字段的值
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void deleteUserInfo(String sql) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            int count = ps.executeUpdate();
            System.out.println(count);
            DBUtil.release(conn, ps, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

    public static void InsertUserInfo(ParkUser parkUser) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql="insert into parkUser values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1, parkUser.getUserName());
            ps.setString(2, parkUser.getUserPassword());
            ps.setString(3, parkUser.getUserRight());
            int count = ps.executeUpdate();
            DBUtil.release(conn, ps, null);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //

    public static Vector<Vector<String>> getAdminInfo() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql="select * from parkAdmin";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vector<String> v = new Vector<String>();
                v.add(rs.getString(1));//表示是获取第一个字段的值
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector<Vector<String>> findAdminInfo(String sql) {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vector<String> v = new Vector<String>();
                v.add(rs.getString(1));//表示是获取第一个字段的值
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
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
