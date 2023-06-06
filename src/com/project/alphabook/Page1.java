package com.project.alphabook;

public class Page1 {
	public void userInterface(int n)
	{
		Obj o = new Obj();
		switch(n)
		{
			case 1 : {
				Signup s = o.sign();
				s.Signup();
//				UserSignup u = new UserSignup();
//				u.Signup();
			}
				break;
			case 2 : {
				Login l = o.log();
				l.Login();
//				UserLogin l = new UserLogin();
//				l.Login();
			}
				break;
			case 3 : {
					UserExit e = new UserExit();
					e.Exit();
			}
				break;
			default : {
				System.out.println("\t----- Invalid input!!! Re-enter your choice -----\n");
				HomePage h = new HomePage();
				h.home();
			}
		}
	}
}
