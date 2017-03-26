import java.io.IOException;

public class ServerController {
	ShitServerModel serverModel;
	ServerPanel serverView;
	Server server = new Server();


	public ServerController(ShitServerModel serverModel, ServerPanel serverView) {
		this.serverModel = serverModel;
		this.serverView = serverView;
		
		serverModel.addObserver(serverView);
		
		
	}
	
	// start the server
	public void startServer() {
		System.out.println("WE BE HERE");

		Thread t = new Thread(server);
		t.start();

		System.out.println("Server started");
	}
	
	public void stopServer() {
		try {
			server.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
