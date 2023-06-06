package com.project.alphabook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	public String date() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
}
