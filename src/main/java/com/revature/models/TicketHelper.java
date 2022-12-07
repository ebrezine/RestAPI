package com.revature.models;

public class TicketHelper {
	public int id;
	public String status;
	
	public TicketHelper() {
	}
	
	public TicketHelper(int num, String stat) {
		id = num;
		status=stat;
	}
}
