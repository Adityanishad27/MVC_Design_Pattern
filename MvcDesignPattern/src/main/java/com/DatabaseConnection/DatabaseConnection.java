package com.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
public static Connection getConnection() throws SQLException {
	
	Connection con =null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc_database","root","root");
		
		
	} catch (ClassNotFoundException e) {
		
		
		e.printStackTrace();
	}
	
	return con;
	
}

}
