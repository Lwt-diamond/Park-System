package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FindJPanelDB {
    public static Vector<Vector<String>> getAllData() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql = "select parkSource.SourceNo,parkSource.sourcePosition,parkPark.carNo,carOwner,carTel,carType,parkPark.carStartDate"
                + " from parkSource left join parkPark on parkSource.sourceNo =parkPark.sourceNo left join parkCar on parkPark.carNo = parkCar.carNo "
                + " where parkSource.sourceIsUsed= 'true'";
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


                rows.add(v);//将每一行的值添加到所有行中

            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
    public static Vector<String> getSourceNoString() {
        Vector<String>  sourceNoVector = new Vector<String>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select sourceNo from parkSource ";//where sourceIsUsed='true'
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs = ps.executeQuery();
            int i=1;
            while(rs.next()) {
                sourceNoVector.add(rs.getString(1));//表示是获取第一个字段的值

            }
            DBUtil.release(conn, ps, rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
//		for(String str:sourceNoVector) {
//			String type =""+str.charAt(0);
//			if(!type.equals(carType)) {
//				sourceNoVector.remove(str);
//			}
//		}
//		java.util.Iterator<String> iterator = sourceNoVector.iterator();
//		while(iterator.hasNext()) {
//			String str=iterator.next();
//			String type =""+str.charAt(0);
//			if(!type.equals(carType)) {
//				iterator.remove();
//			}
//
//		}
        if(sourceNoVector.size()==0)sourceNoVector.add("No source is using!");
        return sourceNoVector;
    }

    public static String getSourcePositionByNo(String sourceNo) {
        String sourcePosition= new String();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select sourcePosition from parkSource where sourceNo=?";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1, sourceNo);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                sourcePosition = rs.getString(1);
                break;
            }
            System.out.println("getSourcePositionByNo: "+sourceNo+"  "+sourceNo);
            DBUtil.release(conn, ps,rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return sourcePosition;
    }
    public static Vector<String> getSourceGroup() {
        Vector<String> sourceGroup=new Vector<String>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select sourceGroup,count(*) from parkSource where sourceIsUsed='true' group by sourceGroup order by sourceGroup asc";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                sourceGroup.add(rs.getString(1));
            }

            DBUtil.release(conn, ps,rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(sourceGroup.size()==0)sourceGroup.add("No source is using!");
        return sourceGroup;
    }


    //以下是更新DB
    //改
    public static int updateDB(String sql){
        int count=0;
        try {
            Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            count = ps.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

}
