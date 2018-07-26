package javabeans;

import java.sql.*;

public class DBTool {
	private static String DB_DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URL="jdbc:sqlserver://localhost:1433;DatabaseName=Cangku;";
	private static String DB_USER="sa";
	private static String DB_PASSWORD="123";
	//获得连接对象
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭结果集对象
	public static void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//关闭预编辑语句对象
	public static void close(PreparedStatement prst){ 
		if(prst!=null){
			try {
				prst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//关闭连接对象
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//针对更新操作关闭资源
	public static void close(PreparedStatement prst,Connection conn){
		close(prst);
		close(conn);
	}
	//针对查询操作关闭资源
	public static void close(ResultSet rs,PreparedStatement prst,Connection conn){
		close(rs);
		close(prst);
		close(conn);
	}
}
