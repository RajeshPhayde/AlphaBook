package com.project.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UserSignup implements Signup{
	String Fname;
	String Lname;
	String Gender;
	String dob;
	long contact;
	String email;
	String password;
	public void Signup()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter First name : " );
		Fname = sc.next();
		System.out.println("\nEnter Last name  : " );
		Lname = sc.next();
		int y=0;
		while(y<1) {
			System.out.println("\nGender     : Select from below \n1. Male  \t 2. Female  \t 3. Other" );
			String s = sc.next();
			switch(s) {
				case "1": Gender = "Male";
				y++;
					break;
				case "2": Gender = "Female";
				y++;
					break;
				case "3": Gender = "Other";
				y++;
					break;
				default: {
					System.out.println("\nEnter valid input !!!\n");
				}
			}
		}
		System.out.println("Enter Date Of Birth : (dd/mm/yy) format");
		while(true) {	
			dob = sc.next();
			Day d = new Day();
			boolean checkDob = d.date(dob);
			if(checkDob) {
				break;
			}
		}
//			String day = sc.next();
//			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
//			java.util.Date udob = sdf1.parse(day);
			
		Password p = new Password();
		int x=0;
		System.out.println("Enter Contact no   : " );
		while(x<1) 
		{
			try {
				String phn = sc.next();
				contact = Long.parseLong(phn);
				boolean b = p.checkph(contact); 
				if(b == false) {
					System.out.println("\nPlease enter 10 digit contact number");					
				}
				else {
					boolean c = p.duplicatecontact(contact);
					if(c==false) {
						x++;						
					}
					else {
						System.out.println("\n# Given number alreday exist, please try with alternate number");
					}
				}
			}
			catch(Exception e) {
				System.out.println("\n ~~~~ Please provide a valid input !!! ~~~~");
			}
		}
		
		System.out.println("\nEnter Email Id   : " );
		int aa = 0;	
		while(aa<1) {
			email = sc.next();
			boolean z = p.verifyMail(email);
			if(z==true) {
				aa++;
			}
			else {
				System.out.println("\n=-=-=-  Enter a valid Email Id -=-=-=");
			}
		}
		boolean b1 = p.checkemail(email);
		if(b1==true) {
			System.out.println("\n----- User already exist !!! -----\n");
			HomePage h = new HomePage();
			h.home();
		}	
		int c=0;
		while(c<1) {
			System.out.println("\nEnter Strong Password   : " );
			password = sc.next();
			boolean b2 = p.checkpwd(password);
			if(b2 == true) {
				c++;
			}
			else {
				System.out.println("\nPassword should contain alphanumeric characters \nand length should be greater than 8.");
			}
		}
		
		System.out.println("\n1. Submit \t 2. Cancel ");
		System.out.println("\t----- Enter your choice -----");
		
		String choice = sc.next();
		switch(choice) {
		case "1" : {
				String url = "jdbc:mysql://localhost:3306/alphabook";
				String user = "root";
				String pass = "root";
				String query = "insert into user values( ?, ?, ?, ?, ?, ?, ?)";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	
					Connection conn =  DriverManager.getConnection(url,user,pass);
					
					PreparedStatement ps = conn.prepareStatement(query);
					
					ps.setString(1, Fname);
					ps.setString(2, Lname);
					ps.setString(3, Gender);
					ps.setString(4, dob);
					ps.setLong(5, contact);
					ps.setString(6, email);
					ps.setString(7, password);
					
					ps.execute();
					
					conn.close();
					ps.close();
					
					System.out.println("\t *** Account Created Succesfully ***\n");
					
					System.out.println("UserName : "+Fname+" "+Lname);
					System.out.println("Gender   : "+ Gender);
					System.out.println("Dob      : "+ dob);
					System.out.println("Contact  : "+ contact);
					System.out.println("Emailid  : "+ email +"\n");
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				HomePage h = new HomePage();
				h.home();
			}
		break;
		case "2" : {
			HomePage h = new HomePage();
			h.home();
			}
		break;
		default :{
			System.out.println("\t----- enter valid input -----");
			HomePage h = new HomePage();
			h.home();
		}
		
		}
		sc.close();
	}
}
