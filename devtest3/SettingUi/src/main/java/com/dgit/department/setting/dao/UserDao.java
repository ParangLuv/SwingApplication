package com.dgit.department.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.jdbc.JdbcUtil;
import com.dgit.department.util.DBConfig;



public class UserDao {
	private static UserDao instance = new UserDao();
	
	private UserDao() {}

	public static UserDao getInstance() {
		return instance;
	}

	public void initUser(){
		createUserAndGrant();
	}
	
	/* 계정 삭제 */
	private void dropUser() {
		String sql = "drop user ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, DBConfig.PJT_USER);
			pstmt.executeUpdate();
			System.out.printf("Drop User(%s) Success! %n", DBConfig.PJT_USER);
		} catch (SQLException e) {
			System.err.printf("Drop User(%s) Fail! %n", DBConfig.PJT_USER);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 계정 생성 및 권한 부여 */
	private void createUserAndGrant() {
		String sql = "grant select, insert, update, delete on " + DBConfig.DB_NAME+ ".* to ? identified by ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, DBConfig.PJT_USER);
			pstmt.setString(2, DBConfig.PJT_PASSWD);
			
			pstmt.execute();
			System.out.printf("Create User(%s) Success! %n", DBConfig.PJT_USER);
		} catch (SQLException e) {
			if (e.getErrorCode()==1396){
				System.err.printf("[ERROR] User(%s) exists!%n", DBConfig.PJT_USER);
				dropUser();
				createUserAndGrant();
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}