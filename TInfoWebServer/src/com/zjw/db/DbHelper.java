package com.zjw.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	/**
	 * 数据库修改方法
	 */
	 public static int executeSql(String sql){
		Connection con=null;
		Statement st=null;
		try{
			con = getconnection();
			st = con.createStatement();
			return st.executeUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			closeConnectionAndStatement(con,st);
		}
		return 0;
	}
	 
	 /**
	  * 建立链接
	  * */
	public static Connection getconnection() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/2018_db_data", "root", "123123");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//关闭连接
	public static void closeConnectionAndStatement(Connection con,Statement st){
		try {
			if(st!=null)st.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
