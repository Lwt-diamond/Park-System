package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import entity.ParkBill;

public class LookBillsJPanelDB {

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

    public static void deleteParkBillInfo(ParkBill parkBill) {
        // TODO Auto-generated method stub
        System.out.println("delete"+parkBill.toString());

    }


}
