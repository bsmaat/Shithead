import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SidebarPanel extends JPanel {
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	ServerPanel serverPanel;
	ClientPanel clientPanel;
	MessagePanel messagePanel;
	
	public SidebarPanel(ClientPanel clientPanel, 
			ServerPanel serverPanel,
			MessagePanel messagePanel) {
		/*
		clientPanel = new ClientPanel();
		serverPanel = new ServerPanel();
		messagePanel = new MessagePanel();
		*/
		this.clientPanel = clientPanel;
		this.serverPanel = serverPanel;
		this.messagePanel = messagePanel;
		
		tabbedPane.add("Client", clientPanel);
		tabbedPane.add("Server",serverPanel);
		tabbedPane.add("Message", messagePanel);
		
		this.add(tabbedPane);
	}
	
}
