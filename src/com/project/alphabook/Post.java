package com.project.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Post {
	public void status(String email) 
	{	
		Scanner scan = new Scanner(System.in);
		boolean output = checkmail(email);
		if(output == false)
		{
			System.out.println("\nPost a Status.... ");
			String mystatus = scan.nextLine();
			String url = "jdbc:mysql://localhost:3306/alphabook";
			String user = "root";
			String pass = "root";
			String query = "insert into status values( ?, ?, ?, ?, ?)";
			
			try {
				DateTime day = new DateTime();
				String time = day.date();
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection conn =  DriverManager.getConnection(url,user,pass);
				
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setString(1, email);
				ps.setString(2, mystatus);
				ps.setString(3, time);
				ps.setString(4, null);
				ps.setString(5, null);
				ps.execute();
				conn.close();
				ps.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			test(email);
		}
		else {
			System.out.println("\nPost a New Status.... ");
			String mystatus1 = scan.nextLine();
			String status = checkstatus(email);
			String time = checktime(email);
			String url = "jdbc:mysql://localhost:3306/alphabook";
			String user = "root";
			String pass = "root";
			String query = "update status set Status=?, Date_Time1=? where EmailId=?";
			String query1 = "update status set OldStatus=?, Date_Time2=? where EmailId=?";
			
			try {
				DateTime day = new DateTime();
				String time1 = day.date();
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection conn =  DriverManager.getConnection(url,user,pass);
				
				PreparedStatement ps = conn.prepareStatement(query);
				PreparedStatement ps1 = conn.prepareStatement(query1);
				
				ps.setString(1, mystatus1);
				ps.setString(2, time1);
				ps.setString(3, email);
				
				ps1.setString(1, status);
				ps1.setString(2, time);
				ps1.setString(3, email);
				
				ps.execute();
				ps1.execute();				
				
				conn.close();
				ps.close();
				ps1.close();
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			test(email);
//			scan.close();
		}
	}
	public void test(String email) {
		Scanner last = new Scanner(System.in);
		while(true) {
			System.out.println("\n1.See Status \t 2.New Post \t 3.Logout \t 4.Exit");
			System.out.println("\t----- Enter your choice -----");
			String str = last.next();
			switch(str) 
			{
			case "1": retrieve(email);
				break;
			case "2": status(email);
				break;
			case "3":
				DateTime out = new DateTime();
				System.out.println("\n\tLogout Time : "+ out.date() + "\n");
				HomePage h1 = new HomePage();
				h1.home();
				break;
			case "4": UserExit e = new UserExit();
				e.Exit();
 				break;
			default : System.out.println("\t Invalid input !!!");
			}
		}
		
	}
	
	public boolean checkmail(String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from status";
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection(url, user, pass);
			 Statement ps = conn.createStatement();
			 ResultSet rs = ps.executeQuery(query);
			 while(rs.next())
			 {
				if(email.equals(rs.getString(1))) {
					return true;
				}
			 }
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String checkstatus(String email) {
		String status;
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from status";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(query);
			while(rs.next())
			{
				if(email.equals(rs.getString(1))) {
					status = rs.getString(2);
					return status;
				}
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String checktime(String email) {
		String time;
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from status";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(query);
			while(rs.next())
			{
				if(email.equals(rs.getString(1))) {
					time = rs.getString(3);
					return time;
				}
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void retrieve(String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from status";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		 Connection conn = DriverManager.getConnection(url, user, pass);
		 
		 PreparedStatement ps = conn.prepareStatement(query);
		 
		 ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(email.equals(rs.getString(1))) {
					
					System.out.println("User          : " + rs.getString(1));
					System.out.println("Status        : " + rs.getString(2));
					System.out.println("Time          : " + rs.getString(3));
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
					if(rs.getString(4)!=null) {
						System.out.println("Old_Status    : " + rs.getString(4));					
						System.out.println("Time          : " + rs.getString(5));					
					}
				}
			}
		 
		 ps.execute();
		 
		 conn.close();
		 ps.close();
		 
		 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Friends(email);
//		HomePage h = new HomePage();
//		h.home();
		
	}
	
	public void Friends(String email) {
		Scanner frnd = new Scanner(System.in);
		while(true) {
			System.out.println("\n1.New Post \t 2.Friends Status \t 3.Logout \t 4.Exit");
			System.out.println("\t----- Enter your choice -----");
			String str = frnd.next();
			switch(str) 
			{
			case "1": status(email);
				break;
			case "2": checkfriends(email);
				break;
			case "3": 
				DateTime out = new DateTime();
				System.out.println("\n\tLogout Time : "+ out.date() + "\n");
				HomePage h1 = new HomePage();
				h1.home();
				break;
			case "4": UserExit e = new UserExit();
				e.Exit();
 				break;
			default : System.out.println("\t Invalid input !!!");
			}
		}
	}
	
	public void checkfriends(String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		String query = "select * from status";
		int c = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass); 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(!email.equals(rs.getString(1))) {
					c++;
					System.out.println(c + ". " + rs.getString(1));					
				}
			}
		 ps.execute();
		 conn.close();
		 ps.close();
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter your choice : ");
		 int select = sc.nextInt();
		 switch(select) {
			 case 1: viewStatus(1,email);
			 break;
			 case 2: viewStatus(2,email);
			 break;
			 case 3: viewStatus(3,email);
			 break;
			 default : System.out.println("please give a valid input !!! ");
		 	}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewStatus(int z, String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		String query = "select * from status";
		int c = 1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass); 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(c==z) {
					
					System.out.println("User          : " + rs.getString(1));
					System.out.println("Status        : " + rs.getString(2));
					System.out.println("Time          : " + rs.getString(3));
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
					if(rs.getString(4)!=null) {
						System.out.println("Old_Status    : " + rs.getString(4));					
						System.out.println("Time          : " + rs.getString(5));
					}
					break;
				}
				c++;
			}
		 ps.execute();
		 conn.close();
		 ps.close();
		 Friends(email);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
