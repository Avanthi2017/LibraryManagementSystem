package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false&useOldAliasMetadataBehavior=true";
	private String userName = "root";
	private String password = "password";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, userName, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}
