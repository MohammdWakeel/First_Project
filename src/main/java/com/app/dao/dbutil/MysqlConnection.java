package com.app.dao.dbutil;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
	
	private static Connection connection;
	private MysqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("Driver loaded Successfuly!");
		
		String url="jdbc:mysql://localhost:3306/first_project";
		String password="Khan@123";
		String user="root";
		connection=DriverManager.getConnection(url,user,password);
		//System.out.println("Connection established!");
		
		return connection;
		
	}
}
