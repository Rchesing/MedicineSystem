package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/hospital";
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String USER="root";
	private static final String PASSWORD="chenyujun";
	private static Connection conn;
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接失败");
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
}