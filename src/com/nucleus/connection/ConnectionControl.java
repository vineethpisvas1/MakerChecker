package com.nucleus.connection;

import java.sql.*;

public class ConnectionControl {
	public Connection connectToDatabase() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.50.198:1521:orcl","sh","sh");
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found!!");
		}catch (SQLException e) {
			System.out.println("SQL error!!");		
		}
		return connection;
	}
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}