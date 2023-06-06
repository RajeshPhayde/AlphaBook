package com.project.alphabook;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Password {
	UserSignup u = new UserSignup();
	public boolean checkpwd(String pwd)
	{
		if(pwd.length()>7) {
			int c=0, n=0;
			for(int i=0; i<pwd.length(); i++) {
				if(pwd.charAt(i)>=48 && pwd.charAt(i)<=57) {
					n++;
				}
				else if((pwd.charAt(i)>=65 && pwd.charAt(i)<=90) ||
						(pwd.charAt(i)>=97 && pwd.charAt(i)<=122)){
					c++;
				}
				if(c>0 && n>0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean checkemail(String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from user";
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection(url, user, pass);
			 Statement ps = conn.createStatement();
			 ResultSet rs = ps.executeQuery(query);
			 while(rs.next())
			 {
				if(email.equals(rs.getString(6))) {
					return true;
				}
			 }
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkph(long contact) {
		int c = 0; 
		while(contact>0) {
			contact = contact/10;
			c++;
		}
		if(c==10) {
			return true;
		}
		return false;
	}
	
	public boolean duplicatecontact(long contact) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from user";
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection(url, user, pass);
			 Statement ps = conn.createStatement();
			 ResultSet rs = ps.executeQuery(query);
			 while(rs.next())
			 {
				if(contact == (rs.getLong(5))) {
					return true;
				}
			 }
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean verifyMail(String email) {
		if(email.length()>10) {
			for(int i=email.length()-10; i<email.length(); i++) {
				if(email.substring(i).equals("@gmail.com") ||
				   email.substring(i).equals("@yahoo.com") || email.substring(i).equals("@email.com")) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void forgot(String emailid) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		String query = "update user set Password=? where EmailId=?";
		
		System.out.println("Enter new password");
		Scanner scr1 = new Scanner(System.in);
		String pwd = scr1.next();
		boolean b = checkpwd(pwd);
		if(b==false)
		{
			System.out.println("password length shound be greater than 8 \nand should be alphanumeric..\n");
			forgot(emailid);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			CallableStatement cs = conn.prepareCall(query);
			
			cs.setString(1, pwd);
			cs.setString(2, emailid);
			
			cs.execute();
			
			System.out.println("\n\t    *** Password changed ***");
			
			cs.close();
			conn.close();
			System.out.println("\t~~~~ Reverting back to homepage ~~~~");
			HomePage h = new HomePage();
			h.home();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		scr1.close();
	}
	
	public void showdetails(String email) {
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from user";
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection(url, user, pass);
			 Statement ps = conn.createStatement();
			 ResultSet rs = ps.executeQuery(query);
			 
			 while(rs.next())
			 {
				if(email.equals(rs.getString(6))) {
					System.out.println("\nUserName     : "+rs.getString(1)+" "+rs.getString(2));
					System.out.println("Gender       : "+ rs.getString(3));
					System.out.println("Date of birth: "+ rs.getString(4));
					System.out.println("Contact      : "+ rs.getLong(5));
					System.out.println("Emailid      : "+ rs.getString(6) +"\n");
				break;
				}
				
			 }
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String generateOtp() {
		int otplength=5;
		SplittableRandom s = new SplittableRandom();
		String str = "";
		for(int i=0; i<otplength; i++) {
			str = str + s.nextInt(0,10);
		}
		return str;
	}
}
