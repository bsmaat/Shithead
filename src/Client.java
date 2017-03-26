import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	// Socket stuff
	Socket socket = null;
	ObjectInputStream inputStream = null;
	ObjectOutputStream outputStream = null;
	
	public Client() {
		
	}
	
	public void send(String str) {
		try { 
			socket = new Socket("localhost", 7777);
			System.out.println("Connected!");
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(str);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
