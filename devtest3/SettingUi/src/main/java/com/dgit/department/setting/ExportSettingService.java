package com.dgit.department.setting;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dgit.department.jdbc.DBConn;
import com.dgit.department.jdbc.JdbcUtil;
import com.dgit.department.setting.dao.DataBaseDao;
import com.dgit.department.util.DBConfig;
import com.dgit.department.util.UseJOptionPane;

public class ExportSettingService extends SettingService{
	
	@Override
	public void doSetting() {
		// TODO Auto-generated method stub
		try{
			DataBaseDao dao = DataBaseDao.getInstance();
			dao.selectUseDatabase();
			
			checkBackupDir();
			
			for(String tableName : DBConfig.TABLE_NAME){
				executeExportData(getFilePath(tableName, false), tableName);
			}
			
			copyFile();
			UseJOptionPane.showMessage("데이터 백업 완료");
		}catch(Exception e){
			UseJOptionPane.showWarningMessage("데이터 백업 실패\n"+e.getMessage());
		}
		
	}
	
	private void checkBackupDir() {
		File backupDir = new File(DBConfig.EXPORT_DIR);
		if (backupDir.exists()){
			for(File file : backupDir.listFiles()){
				file.delete();
				System.out.printf("%s Delete Success! %n",file.getName());
			}
		}else{
			backupDir.mkdir();
			System.out.printf("%s make dir Success! %n",DBConfig.EXPORT_DIR);
		}
				
	}

	public void executeExportData(String tablePath, String tableName) {
		String sql = String.format("select * into outfile '%s' "
				+ "character set 'UTF8' " + "fields TERMINATED by ',' "
				+ "LINES TERMINATED by '\n' from %s", 
				tablePath,	tableName);
		
		Statement stmt = null;
		try {
			Connection con = DBConn.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery(sql);
			System.out.printf("Export Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
		} catch (SQLException e) {
			System.out.printf("error %d : %s %n", e.getErrorCode(), e.getMessage());
		} finally {
			JdbcUtil.close(stmt);
		}
	}

	private void copyFile() {
		File srcDir = new File(DBConfig.MYSQL_EXPORT_PATH);
		File destDir = null;
		
		for(File file : srcDir.listFiles()){
			destDir = new File(DBConfig.EXPORT_DIR+file.getName());
			file.renameTo(destDir);
			file.delete();
		}
	}
}