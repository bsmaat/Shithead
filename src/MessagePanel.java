import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MessagePanel extends JPanel {

	JList lstMessages;
	
	DefaultListModel model;
	
	static int PANE_WIDTH = 300;
	
	public MessagePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setMinimumSize(new Dimension(200, (int)this.);
		this.setBackground(Color.CYAN);
		model = new DefaultListModel();
		lstMessages = new JList(model);
		lstMessages.setLayoutOrientation(JList.VERTICAL);
		lstMessages.setVisibleRowCount(-1);
		JScrollPane pane = new JScrollPane(lstMessages);
		pane.setPreferredSize(new Dimension(PANE_WIDTH, 80));
		//int height = (int)(this.getSize().height * 0.2);
		int height = 300;
		Game.display("height: " + height);
        pane.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));

		this.add(new JLabel("Messages:"));
		this.add(pane);
	}
	
	public void addItemToList(String s) {
		model.addElement(s);
	}
	
}
