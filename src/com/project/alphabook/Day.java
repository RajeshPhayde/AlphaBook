package com.project.alphabook;

import java.util.Scanner;

public class Day {
	int day,mon,year;
	public boolean date(String dob) {
	int c=0;
		while(c<1) {
			System.out.println();
			boolean check = validDate(dob);
			if(!check) {
				System.out.println("Not a valid date, follow date format !!!");
				return false;
			}
			else {
				return true;
			}		
		}
		return false;
	}

	public boolean validDate(String date) {
		if(date.length()==8) {
			String dd = date.substring(0,2);
			String mm = date.substring(3,5);
			String yy = date.substring(6,8);
			try {
				day = Integer.parseInt(dd);
				mon = Integer.parseInt(mm);
				year = Integer.parseInt(yy);
			}
			catch(Exception e) {
				return false;
			}
			if(day>=0 && day<=31 && mon>=0 && mon<=12 ) {
				if(mon==2 && day<=28) {
					return true;
				}
				else if((mon==4 || mon==6 || mon==9 || mon==11) && day<=30) {
					return true;
				}
				else if(mon==1 || mon==3 || mon==5 || mon==7 || mon==8 || mon==10 || mon==12){
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
}
