package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import entity.ParkPrice;

public class SourceInfoJPanelDB {

    public static void UpdateParkPrice(ParkPrice parkPrice) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="update parkPrice set parkPrice=? where carType=?";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setFloat(1, parkPrice.getParkPrice());
            ps.setString(2, parkPrice.getCarType());
            int count = ps.executeUpdate();
            System.out.println("update parkPRICE: "+count);
            DBUtil.release(conn, ps, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ;

    }

    public static Vector<Vector<String>> getAllBillsData() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql = "select * from parkBill";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            int i=1;
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
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));

                rows.add(v);//将每一行的值添加到所有行中

            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector<Vector<String>> getPriceInfo() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select * from parkPrice order by parkPrice";
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

    public static Vector<Vector<String>> getCountBy(String sql) {
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
                if(sql.charAt(sql.length()-1)=='c'){
                    v.add(rs.getString(3));
                }
                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }


}
