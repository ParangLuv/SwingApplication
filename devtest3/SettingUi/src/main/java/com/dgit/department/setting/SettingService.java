package com.dgit.department.setting;

import com.dgit.department.util.DBConfig;

public abstract class SettingService {
	protected static String getFilePath(String tableName, boolean isImport) {
        StringBuilder sb = new StringBuilder();
        sb.append(isImport ? DBConfig.IMPORT_DIR : DBConfig.MYSQL_EXPORT_PATH).append(tableName).append(".txt");
        return sb.toString().replace("\\", "/");
    }
	
	public abstract void doSetting();
}
