package com.revature.daos;

import java.sql.Connection;
import com.revature.models.TicketHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.models.worker;
import com.revature.utils.ConnectionUtil;

import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;


public class TicketDAOImpl implements TicketDAO{

	
	@Override
	public List<Ticket> get_all_tickets(){
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM tickets;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {

				Ticket ticket = new Ticket();
				ticket.description = resultSet.getString("ticket_description");
				ticket.amount = resultSet.getInt("amount");
				ticket.created_by = resultSet.getString("created_by");
				ticket.status = (resultSet.getString("ticket_status"));
				ticket.pending = (resultSet.getBoolean("pending"));
				ticket.id = (resultSet.getInt("id"));
				tickets.add(ticket);
			}
			
			return tickets;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Ticket> get_ticket_by_user(String username) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM tickets WHERE created_by = ? AND pending=false;";
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1,  username);
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.description = resultSet.getString("ticket_description");
				ticket.amount = resultSet.getInt("amount");
				ticket.created_by = resultSet.getString("created_by");
				ticket.status = (resultSet.getString("ticket_status"));
				ticket.pending = (resultSet.getBoolean("pending"));
				ticket.id = (resultSet.getInt("id"));
				tickets.add(ticket);
			}
			
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Ticket> get_ticket_by_status(String user, String status){
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM tickets WHERE created_by = ? AND ticket_status = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1,  user);
			statement.setString(2,  status);
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.description = resultSet.getString("ticket_description");
				ticket.amount = resultSet.getInt("amount");
				ticket.created_by = resultSet.getString("created_by");
				ticket.status = (resultSet.getString("ticket_status"));
				tickets.add(ticket);
			}
			
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean add_ticket(Ticket ticket) {
		try(Connection connection = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO tickets (amount, ticket_description, created_by, ticket_status, pending) VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			int index = 0;
			statement.setInt(++index, ticket.amount);
			statement.setString(++index, ticket.description);
			statement.setString(++index, ticket.created_by);
			statement.setString(++index, ticket.status);
			statement.setBoolean(++index, ticket.pending);

			
			
			statement.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	@Override
	public boolean update_ticket(TicketHelper ticket) {
			try(Connection connection = ConnectionUtil.getConnection()){
				
				String query = "SELECT * FROM tickets WHERE id="+ticket.id+";";
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet tick = statement.executeQuery();
				Boolean pend = true;
				while(tick.next()) {
					pend = tick.getBoolean("pending");
				}
					if(pend == true) {
						String sql = "UPDATE tickets SET ticket_status = ?, pending=false WHERE id = ?;";
						statement = connection.prepareStatement(sql);
						statement.setString(1, ticket.status);
						statement.setInt(2, ticket.id);
						
						statement.execute();
						

						
						return true;
					}
					else {
						return false;
					}

				
				
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
		}
	}
	
	@Override
	public boolean delete_ticket(String description) {
		return false;
	}
	
}
