import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Server implements Runnable {

	ObjectMapper mapper = new ObjectMapper();

	ObjectInputStream inStream = null;
	// port number
	static int portNumber = 7777;
	
	ServerSocket serverSocket;

	public void startServer() {
		try {
			System.out.println("Startin server...");
			serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected!");
            
			inStream = new ObjectInputStream(clientSocket.getInputStream());
			String s = (String)inStream.readObject();
			System.out.println(s);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			ShitModel shitModel = mapper.readValue(s, ShitModel.class);
			
			wait(3000);
			System.out.println("The server printing:");
			shitModel.hands.get(0).printCards();
 
                    
                
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Server closed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace	();
		} 
	}

	
	public void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		startServer();

	}
}
