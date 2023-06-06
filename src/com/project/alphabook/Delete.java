package com.project.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "delete from user where Gender = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, pass);
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, "Male");
			
			ps.execute();
			
			conn.close();
			ps.close();
			
			System.out.println("data deleted");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
