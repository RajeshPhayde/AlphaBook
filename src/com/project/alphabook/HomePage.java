package com.project.alphabook;

import java.util.Scanner;

public class HomePage {
	public void home()
	{
		System.out.println("1. SignUp \t 2. Login \t 3. Exit");
		System.out.println("\t----- Enter your choice -----");

		Scanner sn = new Scanner(System.in);
		String num = sn.next();
		int n=0;
		while(true) {
			try {
				n = Integer.parseInt(num);	
				break;
			}
			catch(Exception e) {
				System.out.println("\t----- Enter valid input !!! -----");
				home();
			}
		}
		Page1 p = new Page1();
		p.userInterface(n);
		sn.close();
	}
	
}
