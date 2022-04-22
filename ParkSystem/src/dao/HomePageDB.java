package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class HomePageDB {

    public static Vector<Vector<String>> getAllData() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select parkSource.SourceNo,parkSource.sourcePosition,parkSource.sourceIsUsed,parkPark.carStartDate,carOwner,carTel,carType from parkSource,parkPark,parkCar where parkSource.sourceNo =parkPark.sourceNo and parkPark.carNo = parkCar.carNo" ;
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

                rows.add(v);//将每一行的值添加到所有行中
            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector<Vector<String>> getSpareSourceInfo() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select sourceNo,sourcePosition from parkSource where sourceIsUsed='false'" ;
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vector<String> v = new Vector<String>();
                v.add(rs.getString(1));//表示是获取第一个字段的值
                v.add(rs.getString(2));
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

}
