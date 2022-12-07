package com.revature.models;

public class LoginDTO {

	public String username;
	public String password;
	public boolean manager;
	
	public LoginDTO(String uname, String pw) {
		super();
		username = uname;
		password = pw;
		manager = false;
	}
	public LoginDTO(String uname, String pw, String is_manager) {
		super();
		username = uname;
		password = pw;
		if(is_manager.toLowerCase() == "true") {
			manager = true;
		}
		else {
			manager = false;
		}
	}
	public LoginDTO() {
		super();
	}
}
