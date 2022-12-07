package com.revature.controllers;

import java.util.List;

import com.revature.exceptions.ActiveDeactivateException;
import com.revature.models.worker;
import com.revature.models.LoginDTO;
import com.revature.services.WorkerService;
import com.revature.services.LoginService;


import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;


public class WorkerController implements Controller{
	private WorkerService worker_service = new WorkerService();
	
	Handler allWorkers=(ctx)->{
		HttpSession session = ctx.req().getSession(false);
		
		if(session!=null) {
			List<worker> list = worker_service.all_workers();
			ctx.json(list);
			ctx.status(200);
		}
		else {
			ctx.status(401);
		}
	};
	
	Handler newWorker=(ctx)->{
		worker work = ctx.bodyAsClass(worker.class);
		if(worker_service.add_worker(work)) {
			ctx.status(201);
		}
		else {
			ctx.status(400);
		}
	};
	
	Handler remove=(ctx)->{
		worker work = ctx.bodyAsClass(worker.class);
		try {
			if(worker_service.remove_worker(work)) {
				ctx.status(200);
			}
			else {
				ctx.status(500);
			}
		}catch(ActiveDeactivateException e) {
			ctx.status(400);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/worker", allWorkers);
		app.get("/worker/{username}", newWorker);
		app.post("/worker", newWorker);
		app.patch("/worker", remove);

	}
}
