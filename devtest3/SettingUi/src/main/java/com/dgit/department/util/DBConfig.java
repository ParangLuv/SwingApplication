package com.dgit.department.util;

public class DBConfig {
	/* DB 생성, 계정 생성에 사용할 계정 */
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/* Project 내에서 사용할 DB 및 계정 */
	public static final String DB_NAME = "ncs_erp_lyj";
	public static final String PJT_USER = "user_ncs";
	public static final String PJT_PASSWD = PJT_USER;

	public static final String[] TABLE_NAME = {"title", "department","employee"};
	
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	public static final String MYSQL_EXPORT_PATH = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Uploads\\";
	
	public static final String[] CREATE_SQL ={			
			"CREATE TABLE title (	"
			+ "tcode INT(11)  NOT NULL,	"
			+ "tname VARCHAR(10) null,	"
			+ "primary key (tcode))",
			
			"CREATE TABLE department (	"
					+ "dcode INT(11) NOT NULL, "
					+ "dname CHAR(10) NOT NULL,	"
					+ "floor INT(11) NULL, "
					+ "primary key (dcode))",
			
			"CREATE TABLE employee (	"
			+ "eno INT(11) NOT NULL,"
			+ "ename VARCHAR(20) NOT NULL, "
			+ "salary INT(11) NULL,"
			+ "dno INT(11) null, "
			+ "gender TINYINT(1) null, "
			+ "joindate DATE null, "
			+ "title INT(11) null, "
			+ "primary key (eno), "
			+ "FOREIGN KEY (dno) REFERENCES department (dcode) on update cascade on delete set null, "
			+ "FOREIGN KEY (title) REFERENCES title (tcode) on update cascade on delete set null )"			
	};
	
	public static final String CREATE_VIEW_SQL = "CREATE OR REPLACE VIEW "+DB_NAME+".view_employee "
												+"AS select e.eno, e.ename, e.salary, e.gender, e.joindate, e.dno, e.title, t.tcode, t.tname, d.dcode, d.dname, d.floor "
												+"from employee as e left outer join title t on e.title = t.tcode "
												+"left outer join department d on e.dno = d.dcode";
	
}
