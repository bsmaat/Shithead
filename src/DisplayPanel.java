import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel {

	JLabel lbMessage;
	
	public DisplayPanel() {
		this.setBackground(Color.BLACK);
		lbMessage = new JLabel("DEFAULT");
		lbMessage.setForeground(Color.white);
		lbMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(lbMessage);
		this.repaint();
	}
	
	public void setText(String s) {
		lbMessage.setText(s);
	}
	
	
}
