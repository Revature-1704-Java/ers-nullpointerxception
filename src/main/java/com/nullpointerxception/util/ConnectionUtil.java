package com.nullpointerxception.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private static ConnectionUtil connectionUtil;
	
	private ConnectionUtil() {
		
	}
	
	public static ConnectionUtil getInstance() {
		if(connectionUtil == null) {
			connectionUtil = new ConnectionUtil();
			return connectionUtil;
		}else {
			return connectionUtil;
		}
	}
	
	public Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		return DriverManager.getConnection(url, user, password);
	}

}
