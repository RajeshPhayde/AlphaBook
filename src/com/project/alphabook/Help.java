package com.project.alphabook;

import java.util.Scanner;

public class Help {
	public void back() {
		System.out.println("1.Login \t 2.Logout \t 3.Exit");
		System.out.println("\t----- Enter your choice -----");
		Scanner scn = new Scanner(System.in);
		int d=0;
		while(d<1) {
			
			String s2 = scn.next();
			switch(s2)
			{
				case "1": { UserLogin l = new UserLogin();
					l.Login();
				}
					break;
				case "2":{
					HomePage h = new HomePage();
					h.home();
					break;
				}
				case "3":{
					UserExit e = new UserExit();
					e.Exit();
					break;
				}
				default: System.out.println("Enter valid input");
			}
		}
		scn.close();
	}
}
