package com.project.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserLogin implements Login{
	String emailid;
	String pwd;
	String Fname;
	String Lname;

	public void Login(){
		
		Scanner scr = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/alphabook";
		String user = "root";
		String pass = "root";
		
		String query = "select * from user";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		 Connection conn = DriverManager.getConnection(url, user, pass);
		 
		 Statement ps = conn.createStatement();
		 
		 ResultSet rs = ps.executeQuery(query);

		 System.out.println("\nEnter email id : ");
		 emailid = scr.next();
			
		 boolean match = false;
		 pwd =null;
		 
			while(rs.next())
			{
				if(emailid.equals(rs.getString(6))) {
					match = true;
					Fname = rs.getString(1);
					Lname = rs.getString(2);
					pwd = rs.getString(7);
					break;
				}
			}
			if(match) {
				System.out.println("\nEnter the password : ");
				for(int i=0; i<3; i++) {
					String password = scr.next();
					if(password.equals(pwd)){
						DateTime dd = new DateTime();
						System.out.println("\n\t***** Welcome " + Fname + " " + Lname + " *****");
						System.out.println("\tLogin Time : "+ dd.date() + "\n");
						//**********************add post
						int j=0;
						while(j<1) {
							
							System.out.println("1.Profile \t 2.Post \t 3.LogOut \t 4.Exit");
							System.out.println("\t----- Enter your choice -----");
							String select = scr.next();
							switch(select) {
							case "1" : {
											Password p1 = new Password();
											p1.showdetails(emailid);
											System.out.println("1.Post \t 2.View Status \t 3.Logout \t 4.Exit");
											System.out.println("\t----- Enter your choice -----");
											int k=0;
											while(k<1) {
												String run = scr.next();
												switch(run) {
												case "1": {
													Post post = new Post();
													post.status(emailid);
													k++;
												}
													break;
												case "2": {
													Post post = new Post();
													post.retrieve(emailid);
													k++;
												}
												break;
												case "3": {
//														DateTime out = new DateTime();
														System.out.println("\n\tLogout Time : "+ dd.date() + "\n");
														HomePage h = new HomePage();
														h.home();
														k++;
												}
													break;
												case "4": {
													UserExit e = new UserExit();
													e.Exit();
													j++;
												}
													break;
												default: System.out.println("\t----- Enter valid input !!! ------");
												}
											}
											j++;
										}
									break;
							case "2" :
										Post post = new Post();
										post.status(emailid); 
										j++;
								break;
							case "3" : {
								System.out.println("\nLogout Time : "+ dd.date() + "\n");
								HomePage h = new HomePage();
								h.home();
								j++;
							}
							break;
							case "4" : {
								UserExit e = new UserExit();
								e.Exit();
								j++;
							}
							break;
							default : System.out.println("\t\t Enter valid input");
							}
						}
					}
					else if(i==2) {
						System.out.println("\n\t***** Maximum attempt reached !!! *****\n");
						while(true) {
						System.out.println("1. Forgot password ? \t 2. Re-Login \t 3. Exit");
						System.out.println("\t----- Enter your choice -----");
						String s1 = scr.next();
						switch(s1) {
							case "1": {
							
								System.out.println("\nEnter Contact no   : " );
								while(true) 
								{
									try {
										Password p = new Password();
										String ph = scr.next();
										long phone = Long.parseLong(ph);
										boolean b = p.checkph(phone); 
										if(b == false) {
											System.out.println("\nEnter 10 digit contact number");					
										}
										else {
											boolean c = p.duplicatecontact(phone);
											if(c==true) {
												String s = p.generateOtp();
												System.out.println("\nOtp sent to "+ phone + "\nOtp : " + s );
												System.out.println("\nPlease verify the otp : ");
												String ss1 = scr.next();
												if(ss1.equals(s)) {
													System.out.println("\n\t ~~~~ User verified ~~~~\n");
													p.forgot(emailid);
													
													break;													
												}else {
													System.out.println(" -- Verification Unsuccesful... Reverting back to home page!!! -- ");
													Thread.sleep(1000);
													HomePage h = new HomePage();
													h.home();
												}
											}
											else {
												System.out.println("Given contact doesn't match with the user, please try again !!!");
											}
										}
										
									}
									catch(Exception e) {
										System.out.println("\t Please provide a valid input !!! \n");
									}
									
								}
								scr.close();
								HomePage h = new HomePage();
										h.home();
								break;
							}
							case "2": {
								Login();
							}
								break;
							case "3":{
								UserExit e = new UserExit();
								e.Exit();
								}
							break;
							default: System.out.println("Enter valid input");
						}
						}
					}
					else {
						System.out.println("Password doesn't match \t Please re-enter the password");
					}
				}
			}
			else {
				System.out.println("\n\t----- User not found -----");
				HomePage h = new HomePage();
				h.home();
			}	 
			 conn.close();
			 ps.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		scr.close();
	}
}
