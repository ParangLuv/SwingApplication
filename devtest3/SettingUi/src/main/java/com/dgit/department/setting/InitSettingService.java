package com.dgit.department.setting;

import com.dgit.department.setting.dao.DataBaseDao;
import com.dgit.department.setting.dao.TableDao;
import com.dgit.department.setting.dao.UserDao;
import com.dgit.department.util.DBConfig;
import com.dgit.department.util.UseJOptionPane;

public class InitSettingService extends SettingService {
	
	@Override
	public void doSetting() {
		
		createDataBase();
		createTable();
		createView();
		createUser();
		UseJOptionPane.showMessage("초기화 완료");
	
	}
	
	private void createView() {
		DataBaseDao DBDao = DataBaseDao.getInstance();
		DBDao.createView(DBConfig.CREATE_VIEW_SQL);		
	}

	private void createDataBase(){
		DataBaseDao DBDao = DataBaseDao.getInstance();
		DBDao.createDatabase();
		DBDao.selectUseDatabase();
	}

	private void createTable(){
		TableDao tDao = TableDao.getInstance();
		for(String sql:DBConfig.CREATE_SQL){
			tDao.createTable(sql);
		}
	}
	
	private void createUser(){
		UserDao uDao = UserDao.getInstance();
		uDao.initUser();
	}
	
}
