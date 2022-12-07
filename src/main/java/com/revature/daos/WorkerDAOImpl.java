package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.worker;
import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

public class WorkerDAOImpl implements WorkerDAO{
	private TicketDAO ticket_dao = new TicketDAOImpl();
	
	@Override
	public List<worker> find_all_workers(){
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM workers;";

			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<worker> list = new ArrayList<>();

			while (result.next()) {
				worker a = new worker( 
						result.getString("username"),
						result.getString("password"), 
						result.getBoolean("is_manager"));
				list.add(a);
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public worker find_worker_by_id(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM workers WHERE username = "+"'"+username+"';";
			
			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);
			if(result.next()) {
				worker a = new worker( 
						result.getString("username"),
						result.getString("password"), 
						result.getBoolean("is_manager"));
				return a;
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean add_worker(worker work) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO workers (username, password, is_manager) VALUES (?, ?, ?);";
PreparedStatement statement = connection.prepareStatement(sql);
			
			int index = 1;
			statement.setString(index++, work.username);
			statement.setString(index++, work.password);
			statement.setBoolean(index++, work.is_manager);
			
			statement.execute();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean remove_worker(String username) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM workers SET WHERE username = "+username+";";
			
			Statement statement = connection.createStatement();
			
			statement.execute(sql);
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
