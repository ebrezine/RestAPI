package com.revature.services;

import java.util.List;

import com.revature.daos.WorkerDAO;
import com.revature.daos.WorkerDAOImpl;
import com.revature.exceptions.ActiveDeactivateException;
import com.revature.models.worker;

public class WorkerService {
	private WorkerDAO worker_dao;
	
	public WorkerService() {
		worker_dao = new WorkerDAOImpl();
	}
	
	public WorkerService(WorkerDAO workerdao) {
		worker_dao = workerdao;
	}
	
	public List<worker> all_workers() {
		return worker_dao.find_all_workers();
	}
	public worker find_worker_by_id(String username) {
		return worker_dao.find_worker_by_id(username);
	}
	public boolean add_worker(worker work) {
		return worker_dao.add_worker(work);
	}
	public boolean remove_worker(worker work) throws ActiveDeactivateException{
		if(work == null) {
			throw new ActiveDeactivateException("User tried to remove nonexistent worker");
		}
		return worker_dao.remove_worker(work.username);
	}
}
