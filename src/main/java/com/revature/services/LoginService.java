package com.revature.services;

import com.revature.services.WorkerService;
import com.revature.models.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginService {
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
		

	public boolean login(String username, String pw) {
		log.info("User attempted to log in with username: " + username);
		boolean successful = false;

		try {
			WorkerService worker_service = new WorkerService();
			if(worker_service.find_worker_by_id(username) != null) {
				log.info("made it to found worker");
				worker acc = worker_service.find_worker_by_id(username);
				if(acc.password.equals(pw)) {
					log.info(username+" logged in");
					successful = true;
					
				}else {
					log.warn(username+" failed to log in");
				}
			}
		}catch(NullPointerException e) {
			log.warn(username+" attempted to log in with null value");
		}
		return successful;
	}
	
	public boolean register(String username, String pw) {
		try {
			log.info("user attempted to register");
			WorkerService worker_service = new WorkerService();
			if(worker_service.find_worker_by_id(username) == null) {
				worker new_guy = new worker(username, pw);
				worker_service.add_worker(new_guy);
				log.info(username+" created");
				return true;
			}else {
				log.warn(username+" already exists");
				return false;
			}
		}catch(NullPointerException e) {
			log.warn(username+" attempted to log in with null value");
			return false;
		}
	}
}
