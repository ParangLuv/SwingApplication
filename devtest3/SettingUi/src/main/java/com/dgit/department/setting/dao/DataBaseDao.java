package com.dgit.department.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.jdbc.JdbcUtil;
import com.dgit.department.util.DBConfig;


public class DataBaseDao {
	private PreparedStatement pstmt;
	
	private static DataBaseDao instance = new DataBaseDao();

	private DataBaseDao() {}

	public static DataBaseDao getInstance() {
		return instance;
	}
	
	public void createDatabase() {//DB생성
		try {
			Connection con = DBConn.getConnection();
			String sql = "CREATE DATABASE " + DBConfig.DB_NAME;
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.println(sql);
		} catch (SQLException e) {
			if (e.getErrorCode()==1007){//생성하려는 DB가 이미 존재하는 경우
				System.out.printf("[ERROR] DATABASE(%s) Exist! %n", DBConfig.DB_NAME);
				dropDatabase();
				createDatabase();
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private void dropDatabase() {//DB삭제
		try {
			Connection con = DBConn.getConnection();
			String sql = "DROP DATABASE IF EXISTS " + DBConfig.DB_NAME;
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.println(sql);
		} catch (SQLException e) {
			System.out.printf("[ERROR] DROP DATABASE(%s) Fail! %n", DBConfig.DB_NAME);
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public void selectUseDatabase() {// use ncs_erp_lyj
		try {
			Connection con = DBConn.getConnection();
			String sql = "USE " + DBConfig.DB_NAME;
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.println(sql);
		} catch (SQLException e) {
			System.out.printf("[ERROR] USE DATABASE(%s) Selected Fail! %n", DBConfig.DB_NAME);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}		
	}

	public void setForeignKeyCheck(int isCheck){//외래키 제한 설정
		try{
			Connection con = DBConn.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
			System.out.printf("%s SET FOREIGN_KEY_CHECKS(%s) Success!%n", DBConfig.DB_NAME, isCheck==0?"False":"True");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void createView(String sql) {
		Connection con = DBConn.getConnection();
		System.out.println("CREATE VIEW SQL : "+sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.printf("CREATE VIEW(%s) Success! %n",sql.substring(35, sql.indexOf("AS")));
		} catch (SQLException e) {
			System.out.printf("CREATE VIEW(%s) Fail! %n",	sql.substring(35, sql.indexOf("AS")));
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
