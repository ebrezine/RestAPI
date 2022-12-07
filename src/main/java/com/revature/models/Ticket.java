package com.revature.models;

public class Ticket {
	public String status;
	public int amount;
	public String description;
	public boolean pending;
	public String created_by;
	public int id;
	
	public Ticket() {
		pending = true;
		
	}
	
	public Ticket(int amt, String desc) {
		amount = amt;
		description = desc;
		pending=true;
	}
	public Ticket(int amt, String desc, String stat) {
		if(stat != "Pending") {
			if(stat.toLowerCase() != "approved" && stat.toLowerCase() != "denied") {
				status = ("pending");
				pending = true;
			} else {
				status = (stat);
				pending = false;
			}
		} else {
			status = ("pending");
			pending = true;
		}

		
	}
	public Ticket(int amt, String desc, String stat, String creator) {
		if(stat != "Pending") {
			if(stat.toLowerCase() != "approved" && stat.toLowerCase() != "denied") {
				status = "pending";
				pending = true;
			} else {
				status = stat;
				pending = false;
			}
		} else {
			status = "pending";
			pending = true;
		}
		amount = amt;
		description = desc;
		created_by = creator;
	}
}
