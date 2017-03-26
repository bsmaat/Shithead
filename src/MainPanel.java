import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	TablePanel tablePanel;
	
	public MainPanel(TablePanel tablePanel) {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Game"));
		this.setSize(new Dimension(ShitHeadGUI.TABLE_WIDTH, ShitHeadGUI.TABLE_HEIGHT));
		this.tablePanel = tablePanel;
		this.add(this.tablePanel, BorderLayout.CENTER);
		
	}
}
