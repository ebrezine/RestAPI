package com.revature.daos;
import com.revature.models.TicketHelper;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {
	public abstract List<Ticket> get_all_tickets();
	public abstract List<Ticket> get_ticket_by_user(String user);
	public abstract List<Ticket> get_ticket_by_status(String user, String status);
	public abstract boolean add_ticket(Ticket ticket);
	public abstract boolean update_ticket(TicketHelper ticket);
	public abstract boolean delete_ticket(String description);
}
