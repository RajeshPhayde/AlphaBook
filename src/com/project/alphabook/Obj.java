package com.project.alphabook;

public class Obj {
	public Login log() {
		Login l = new UserLogin();
		return l;
	}
	public Signup sign() {
		Signup s = new UserSignup();
		return s;
	}
}
