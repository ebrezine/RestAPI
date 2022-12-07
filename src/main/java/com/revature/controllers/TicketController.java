package com.revature.controllers;

import com.revature.models.Ticket;
import com.revature.models.worker;
import com.revature.models.LoginDTO;
import com.revature.services.TicketService;
import com.revature.models.TicketHelper;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class TicketController implements Controller{
	private TicketService ticket_service = new TicketService();
	
	Handler get_tickets = (ctx) -> {
		HttpSession session = ctx.req().getSession();
		System.out.println("got session");
		worker work = (worker)session.getAttribute("user");
		System.out.println("got user");
		if(work.is_manager == false) {
			System.out.println("not manager");
			List<Ticket> tickets = ticket_service.get_ticket_by_user(work.username);

			if(tickets != null) {
				ctx.json(tickets);
				ctx.status(200);
				for(int i = 0; i < tickets.size(); i++) {
					Ticket tick = tickets.get(i);
					System.out.println(tick.id);
				}
			}else {
				ctx.status(204);
			}
		}
		else {
			System.out.println("boss");
			List<Ticket> pending = ticket_service.find_all_tickets();
			System.out.println("got it");
			for(int i = 0; i < pending.size(); i++) {
				Ticket tick = pending.get(i);
				if (tick.status.toString().toLowerCase() != "pending"){
						pending.remove(i);
				}
			}
			
			ctx.json(pending);
			ctx.status(200);
		}
	};
	
	

		
	
	Handler new_ticket = (ctx) -> {
		Ticket ticket = ctx.bodyAsClass(Ticket.class);
		HttpSession session = ctx.req().getSession();
		worker work = (worker) session.getAttribute("user");
		ticket.created_by = work.username;
		if(ticket.status == null) {
			ticket.status = "pending";
			ticket.pending = true;
		}
		if(ticket_service.add_ticket(ticket)) {
			ctx.status(201);
		}else {
			ctx.status(400);
		}
	};
	
	Handler change_ticket = (ctx) ->{
		HttpSession session = ctx.req().getSession();
		worker work = (worker) session.getAttribute("user");
		if(work.is_manager == true) {
			TicketHelper ticket = ctx.bodyAsClass(TicketHelper.class);
			System.out.println("made ticket");
			if(ticket_service.update_ticket(ticket)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}
	};
	
	Handler pending_ticket = (ctx) ->{
		HttpSession session = ctx.req().getSession();
		if(session.getAttribute("user") != null) {
			boolean is_manager = (boolean) session.getAttribute("is_manager");
			if(is_manager == true) {
				List<Ticket> tickets = ticket_service.find_all_tickets();
				if(tickets != null) {
					for(int i = 0; i < tickets.size(); i++) {
						Ticket tick = tickets.get(i);
						if (!(tick.status.toLowerCase().equals("pending"))) {
							tickets.remove(i);
						}
					}
					
					ctx.json(tickets);
					ctx.status(201);
				}
				else {
					ctx.status(400);
				}
			}
			
		}else {
			ctx.status(401);
		}
	};
	
	Handler accepted_ticket = (ctx) ->{
		HttpSession session = ctx.req().getSession();
		if(session.getAttribute("user") != null) {
			List<Ticket> tickets = ticket_service.find_all_tickets();
			if(tickets != null) {
				for(int i = 0; i < tickets.size(); i++) {
					Ticket tick = tickets.get(i);
					if (!(tick.status.toLowerCase().equals("accepted"))) {
						tickets.remove(i);
					}
				}
				ctx.json(tickets);
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}else {
			ctx.status(401);
		}
	};
	
	Handler denied_ticket = (ctx)->{
		HttpSession session = ctx.req().getSession();
		if(session.getAttribute("user") != null) {
			List<Ticket> tickets = ticket_service.find_all_tickets();
			if(tickets != null) {
				for(int i = 0; i < tickets.size(); i++) {
					Ticket tick = tickets.get(i);
					if (!(tick.status.toLowerCase().equals("denied"))) {
						tickets.remove(i);
					}
				}
				ctx.json(tickets);
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}else {
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.post("/change", change_ticket);
		app.get("/ticket", get_tickets);
		app.post("/ticket", new_ticket);
		app.get("/ticket/pending", pending_ticket);
		app.get("ticket/accepted", accepted_ticket);
		app.get("ticket/denied", denied_ticket);
	}
}
