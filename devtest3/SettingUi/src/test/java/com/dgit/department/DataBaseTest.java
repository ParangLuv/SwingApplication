package com.dgit.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.util.DBConfig;

import junit.framework.Assert;

public class DataBaseTest {
	
	@Test
	public void testDBConnection(){
		Connection conn = DBConn.getConnection();
		Assert.assertNotNull(conn);
		System.out.println("Connection : "+conn);
	}
	
	//http://blog.naver.com/PostView.nhn?blogId=echoikh&logNo=220056173529&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView 참고
	@Test
	public void testDBExists(){		
		try {
			String sql = "select exists(select 1 from Information_schema.tables where table_schema  = '"+DBConfig.DB_NAME+"') as flag ";
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int result = rs.getInt("flag");
				Assert.assertEquals(1, result);
				System.out.println(result==1?"DB 존재":"DB 존재하지 않음");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testEmployeeTableExists(){
		try {
			String sql = "select exists(select 1 from Information_schema.tables where table_schema  = '"+DBConfig.DB_NAME+"' and table_name = 'employee') as flag ";
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int result = rs.getInt("flag");
				Assert.assertEquals(1, result);
				System.out.println(result==1?"employee 존재":"employee 존재하지 않음");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDepartmentTableExists(){
		try {
			String sql = "select exists(select 1 from Information_schema.tables where table_schema  = '"+DBConfig.DB_NAME+"' and table_name = 'department') as flag ";
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int result = rs.getInt("flag");
				Assert.assertEquals(1, result);
				System.out.println(result==1?"department 존재":"department 존재하지 않음");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTitleTableExists(){
		try {
			String sql = "select exists(select 1 from Information_schema.tables where table_schema  = '"+DBConfig.DB_NAME+"' and table_name = 'title') as flag ";
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int result = rs.getInt("flag");
				Assert.assertEquals(1, result);
				System.out.println(result==1?"title 존재":"title 존재하지 않음");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
