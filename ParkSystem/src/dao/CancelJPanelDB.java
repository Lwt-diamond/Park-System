package dao;

import entity.ParkBill;
import entity.ParkCar;
import entity.ParkPark;
import entity.ParkSource;
import view.panel.CancelJPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CancelJPanelDB {
    public CancelJPanel cancelJPanel;
    public CancelJPanelDB(CancelJPanel cancelJPanel) {
        this.cancelJPanel = cancelJPanel;
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


    //找到单价
    public static float getParkPriceByCarType(String cType) {
        float perHour=0;
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="select  parkPrice  from parkPrice where carType=?";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1,cType);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                perHour = rs.getFloat(1);
            }
            DBUtil.release(conn, ps,rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return perHour;
    }

    public static void addParkBillInfo(ParkBill parkBill) {
        Connection conn = DBUtil.getDBConnection();
        PreparedStatement ps;
        String sql ="insert into parkBill values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);//"select * from parkSource"
            ps.setString(1,parkBill.getBillNo());
            ps.setString(2,parkBill.getParkCar().getCarNo());
            ps.setString(3,parkBill.getParkCar().getCarType());
            ps.setString(4,parkBill.getParkCar().getCarOwner());
            ps.setString(5,parkBill.getParkCar().getCarTel());
            ps.setString(6,parkBill.getSourceNo());
            ps.setString(7,parkBill.getCarStartDate());
            ps.setString(8,parkBill.getCarEndDate());
            ps.setFloat(9,parkBill.getCarStopHours());
            ps.setFloat(10,parkBill.getParkPrice());
            ps.setFloat(11,parkBill.getCarFee());
            ps.setString(12, parkBill.getBillAdminUserName());
            ps.setString(13, parkBill.getBillAdminUserTel());

            int count = ps.executeUpdate();
            System.out.println("这是账单加入的结果："+count);
            //安全调用
            CancelJPanelDB.setParkSourceInfo(new ParkSource(parkBill.getSourceNo(),null,"false"));
            CancelJPanelDB.deleteParkPark(new ParkPark(parkBill.getParkCar().getCarNo(),parkBill.getSourceNo(), parkBill.getCarStartDate()));
            CancelJPanelDB.deleteParkCar(parkBill.getParkCar());
            DBUtil.release(conn, ps,null);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void deleteParkCar(ParkCar parkCar) {
        String sql="delete from parkCar where carNo=?";
        int count=0;
        try {
            Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, parkCar.getCarNo());
            count = ps.executeUpdate();
            System.out.println("Delete: "+count+parkCar.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ;
    }

    private static void deleteParkPark(ParkPark parkPark) {
        String sql="delete from parkPark where carNo=?";
        int count=0;
        try {
            Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, parkPark.getCarNo());
            count = ps.executeUpdate();
            System.out.println("Delete: "+count+parkPark.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ;
    }
}
