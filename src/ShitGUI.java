

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShitGUI {
	public static void main(String[] args) {
		ShitGUI gui = new ShitGUI();
	}

	
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel();
	public ShitGUI() {
		mainPanel.setPreferredSize(new Dimension(500,500));

		//HandPanel pan = new HandPanel(new Card(2,2));
		
		//mainPanel.add(pan);
		
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
