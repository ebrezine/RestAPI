package com.revature.daos;

import java.util.List;

import com.revature.models.worker;
public interface WorkerDAO {
	public abstract List<worker> find_all_workers();
	public abstract worker find_worker_by_id(String username);
	public abstract boolean add_worker(worker work);
	public abstract boolean remove_worker(String username);
}
