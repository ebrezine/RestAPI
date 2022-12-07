
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controllers.WorkerController;
import com.revature.controllers.Controller;
import com.revature.controllers.TicketController;
import com.revature.controllers.LoginController;


import io.javalin.Javalin;


public class App {
	private static Javalin app;
	private static Logger log = LoggerFactory.getLogger(App.class);

	
	public static void configure(Controller... controllers) {
		for(Controller c: controllers) {
			c.addRoutes(app);
		}
	}

	public static void main(String[] args) {
		app = Javalin.create();
		configure(new TicketController(), new WorkerController(), new LoginController());
		log.info("Configuration completed.");
		app.start(8083);
	}
	
	
	
}
