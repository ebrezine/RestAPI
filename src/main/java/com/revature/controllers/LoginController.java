package com.revature.controllers;


import com.revature.models.LoginDTO;
import com.revature.models.worker;
import com.revature.services.LoginService;
import com.revature.services.WorkerService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {
	private LoginService login_service = new LoginService();
	
	Handler login = (ctx) ->{
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);
		
		if(login_service.login(attempt.username, attempt.password) ) {
			HttpSession session = ctx.req().getSession();
			WorkerService worker_service = new WorkerService();
			worker work = worker_service.find_worker_by_id(attempt.username);
			session.setAttribute("user", work);
			session.setAttribute("username", work.username);
			session.setAttribute("is_manager", work.is_manager);
			ctx.status(200);
		}else {
				ctx.status(HttpStatus.UNAUTHORIZED);
			}
	};
	
	Handler logout = (ctx)->{
		HttpSession session = ctx.req().getSession(false);
		if(session!=null) {
			session.invalidate();
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
	
	Handler register=(ctx)->{
		
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		HttpSession session = ctx.req().getSession();
		WorkerService worker_service = new WorkerService();
		worker work = new worker(attempt.username, attempt.password, attempt.manager);
		worker_service.add_worker(work);
		session.setAttribute("user", work);
		session.setAttribute("username", work.username);
		session.setAttribute("is_manager", work.is_manager);
		ctx.status(200);
	};
	

	
	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", login);
		app.get("/logout", logout);
		app.post("/register", register);
	}

}
