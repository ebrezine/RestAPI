package com.revature.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		if(connection!=null && !connection.isClosed()) {
			return connection;
		}else {
			
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
			String url = "jdbc:postgresql://localhost:5433/postgres";
			String username = "postgres"; 
			String password = "password"; 
			
			connection = DriverManager.getConnection(url, username, password);
			
			return connection;
		}	
	}
}
