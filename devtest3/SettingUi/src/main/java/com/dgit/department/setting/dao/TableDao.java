package com.dgit.department.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.jdbc.JdbcUtil;


public class TableDao {
	private PreparedStatement pstmt;
	
	private static TableDao instance = new TableDao();

	private TableDao() {}

	public static TableDao getInstance() {
		return instance;
	}

	public void createTable(String sql) {
		Connection con = DBConn.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.printf("CREATE TABLE(%s) Success! %n",sql.substring(13, sql.indexOf("(")));
		} catch (SQLException e) {
			System.out.printf("CREATE TABLE(%s) Fail! %n",	sql.substring(13, sql.indexOf("(")));
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
