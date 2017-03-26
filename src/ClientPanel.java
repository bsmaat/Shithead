import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ClientPanel extends JPanel {

	JLabel lbClientAddress = new JLabel("Client address:");
	JLabel lbClientPort = new JLabel("Client port: ");
	
	JTextField txtClientAddress = new JTextField("localhost");
	JTextField txtPort = new JTextField("7777");
	
	JButton btnConnect = new JButton("Connect");
	
	public ClientPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Client"));
		this.setLayout(new MigLayout());
		this.add(lbClientAddress, "align label");
		this.add(txtClientAddress, "span,wrap");
		this.add(lbClientPort, "align label");
		this.add(txtPort, "span, wrap");
		this.add(btnConnect);
		
	}
	
	
	
}
