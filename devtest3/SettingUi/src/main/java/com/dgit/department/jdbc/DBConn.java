package com.dgit.department.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dgit.department.util.DBConfig;

public class DBConn {
	private static DBConn instance = new DBConn();
	private static Connection conn;
	
	private DBConn() {
		try {
			Class.forName(DBConfig.DRIVER);
			conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if(instance == null){
			new DBConn();
		}
		
		return DBConn.conn;
	}
	
	public static void closeConnection(){
		if(conn != null){
			try{
				conn.close();
				conn = null;
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
