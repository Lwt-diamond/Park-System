package Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;

public class PhotoDemo {

    /**
     * @param args
     */
    Connection conn=null;
    public PhotoDemo() {
        try {
            String url="jdbc:sqlserver://localhost:1433;DatabaseName=park";
            //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            conn= DriverManager.getConnection(url,"sa","123456");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }
    public void Insert() {
        try {
            String sql="insert into photo values(?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            File f =new File("C:\\Users\\Administrator\\Desktop\\playbg.jpg");

            FileInputStream input= new FileInputStream(f);

            ps.setBinaryStream(1, input,(int)f.length());
            ps.executeUpdate();
            ps.close();
            input.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    public void Read() {
        try {
            String sql="select photo from photo";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            byte [] b=new byte[10240*8];

            while(rs.next()) {
                InputStream in=rs.getBinaryStream("photo");
                in.read(b);
                File f=new File("D:/3.jpg");
                FileOutputStream out=new FileOutputStream(f);
                out.write(b, 0, b.length);
                out.close();
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PhotoDemo p=new PhotoDemo();
        p.Insert();
        p.Read();
    }

}