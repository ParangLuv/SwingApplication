package com.dgit.department.setting;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.jdbc.JdbcUtil;
import com.dgit.department.setting.dao.DataBaseDao;
import com.dgit.department.util.DBConfig;
import com.dgit.department.util.UseJOptionPane;



public class ImportSettingService extends SettingService{
	
	@Override
	public void doSetting() {
		try{
			DataBaseDao dao = DataBaseDao.getInstance();
			dao.selectUseDatabase();
					
			dao.setForeignKeyCheck(0);
			for(String tableName : DBConfig.TABLE_NAME){
				executeImportDate(getFilePath(tableName, true), tableName);
			}
			dao.setForeignKeyCheck(1);
			UseJOptionPane.showMessage("데이터 복원 완료");
		}catch(Exception e){
			UseJOptionPane.showWarningMessage("데이터 복원 실패\n"+e.getMessage());
		}
	}
	
	private void executeImportDate(String tablePath, String tableName) {
		String sql = String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s character set 'UTF8' fields TERMINATED by ','",
				tablePath, tableName);
		Statement stmt = null;
		try {
			Connection con = DBConn.getConnection();
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("중복데이터 존재");
			}
		} finally {
			JdbcUtil.close(stmt);
		}

	}
}

