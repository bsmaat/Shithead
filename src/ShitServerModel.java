
public class ShitServerModel extends java.util.Observable {
	Server server;
	
	public ShitServerModel() {
		
	}
	
	public void updateChanges() {
		setChanged();
		notifyObservers();
	}
	
	
}
