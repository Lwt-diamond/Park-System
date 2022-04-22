package dao;
//数据库相关的操作（数据库连接，释放资源）

import java.sql.*;

public class DBUtil {
	private static Connection conn;
	private static String url ="jdbc:sqlserver://localhost:1433;DatabaseName=park";
	private static String user = "sa";
	private static String password = "123456";
	//获得默认数据库连接
	public static Connection getDBConnection()
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//释放资源
	public static void release(Connection conn,PreparedStatement ps,ResultSet rs)
	{
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null)
		{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

