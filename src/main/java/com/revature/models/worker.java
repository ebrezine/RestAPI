package com.revature.models;


import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class worker {
	public String username;
	public String password;
	public boolean is_manager;
	
	public String hash_pw(String pw) {
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
		return encoder.encode(pw);
	}
	

	public worker(String uname, String pw) {
		username = uname;
		password = pw;
		is_manager = false;
	}
	
	public worker(String uname, String pw, boolean manager) {
		username = uname;
		password = pw;
		is_manager = manager;
	}

	public Ticket add_ticket(int amt, String desc, String stat) {
		Ticket tick = new Ticket(amt, desc, stat, this.username);
		return tick;
	}
	

}
