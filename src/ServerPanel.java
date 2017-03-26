

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ServerPanel extends JPanel implements ActionListener, java.util.Observer {
	
	
	JTextField txtPortNumber = new JTextField("7777",5);
	JLabel lbPortNumber = new JLabel("Port number:");
	JButton btnStartServer = new JButton("Start server");
	JButton btnStopServer = new JButton("Stop server");
	
	ServerController controller;
	
	public ServerPanel() {
		
		this.setBorder(BorderFactory.createTitledBorder("Server"));
		
		this.setLayout(new MigLayout());
		this.add(lbPortNumber, "align label");
		this.add(txtPortNumber, "span,wrap");
		
		this.add(btnStartServer);
		this.add(btnStopServer, "wrap");
		
		btnStartServer.addActionListener(this);
		btnStopServer.addActionListener(this);
		
		
	}

	public void setController(ServerController controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnStartServer) {
			btnStartServer_click();
		} else if (e.getSource() == btnStopServer ) {
			btnStopServer_click();
		}
		
		
		
	}
	
	public void btnStartServer_click() {
		controller.startServer();
	}
	
	public void btnStopServer_click() {
		controller.stopServer();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		ShitServerModel serverModel = (ShitServerModel) o;
		
	}
}
