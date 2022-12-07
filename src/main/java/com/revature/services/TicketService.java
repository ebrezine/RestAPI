package com.revature.services;
import com.revature.models.TicketHelper;

import java.util.List;

import com.revature.daos.TicketDAO;
import com.revature.daos.TicketDAOImpl;
import com.revature.models.Ticket;


public class TicketService {
	private TicketDAO ticket_dao = new TicketDAOImpl();

	
	public List<Ticket> find_all_tickets(){
		return ticket_dao.get_all_tickets();
	}
	public List<Ticket> get_ticket_by_user(String username) {
		return ticket_dao.get_ticket_by_user(username);
	}
	public boolean add_ticket(Ticket tick) {
		return ticket_dao.add_ticket(tick);
	}
	public List<Ticket> get_ticket_by_status(String user, String status){
		return ticket_dao.get_ticket_by_status(user, status);
	}
	public boolean update_ticket(TicketHelper tick) {
		return ticket_dao.update_ticket(tick);
	}
}
