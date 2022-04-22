package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


import entity.ParkCar;
import entity.ParkPark;
import entity.ParkSource;
import view.panel.StopJPanel;

public class StopJPanelDB {
    public StopJPanel stopJPanel;
    public StopJPanelDB(StopJPanel stopJPanel) {
        this.stopJPanel = stopJPanel;
    }
    public static Vector<Vector<String>> getAllData() {
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql = "select parkSource.SourceNo,parkSource.sourcePosition,parkPark.carNo,carOwner,carTel,carType,parkPark.carStartDate"
                + " from parkSource left join parkPark on parkSource.sourceNo =parkPark.sourceNo left join parkCar on parkPark.carNo = parkCar.carNo";
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


    public static Vector<String> getSourceNoStringByType(String carType) {
        Vector<String>  sourceNoVector = new Vector<String>();
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select sourceNo from parkSource where sourceIsUsed='false'";
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
        java.util.Iterator<String> iterator = sourceNoVector.iterator();
        while(iterator.hasNext()) {
            String str=iterator.next();
            String type =""+str.charAt(0);
            if(!type.equals(carType)) {
                iterator.remove();
            }

        }
        if(sourceNoVector.size()==0)sourceNoVector.add("No spare source");
        return sourceNoVector;
    }
    public static void addParkCarInfo(ParkCar parkCar) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="insert into parkCar values(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1, parkCar.getCarNo());
            ps.setString(2, parkCar.getCarType());
            ps.setString(3, parkCar.getCarOwner());
            ps.setString(4, parkCar.getCarTel());
            int count = ps.executeUpdate();
            System.out.println("insert parkCar count:"+count);
            DBUtil.release(conn, ps,null);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void setParkSourceInfo(ParkSource parkSource) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="update parkSource set sourceIsUsed=? where sourceNo=?";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1, parkSource.getSourceIsUsed());
            ps.setString(2, parkSource.getSourceNo());
            int count = ps.executeUpdate();
            System.out.println("setParkSourceInfo count:"+count);
            DBUtil.release(conn, ps,null);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void addParkParkInfo(ParkPark parkPark) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="insert into parkPark values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1, parkPark.getCarNo());
            ps.setString(2, parkPark.getSourceNo());
            ps.setString(3, parkPark.getCarStartDate());
            int count = ps.executeUpdate();
            System.out.println("insert parkPark count:"+count);
            DBUtil.release(conn, ps,null);
        }catch (Exception e) {
            e.printStackTrace();
        }

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
        String sql ="select sourceGroup,count(*) from parkSource where sourceIsUsed='false' group by sourceGroup order by sourceGroup asc";
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
        if(sourceGroup.size()==0)sourceGroup.add("NO spare source");
        return sourceGroup;
    }


}
