import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PilePanel extends CardPanel {

	public static int SPACE_X = 10;
	public static int SPACE_Y = 0;
	public static int OVERLAP = CardImage.WIDTH/5;

	boolean max_size = false;
		
	public PilePanel() {
		super();
	}
	
	public PilePanel(Hand hand) {
		super(hand);
		setBackground(Color.red);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(cardImage.size() * (CardImage.WIDTH + HandPanel.SPACE_X) - HandPanel.SPACE_X, CardImage.HEIGHT + 2* HandPanel.SPACE_Y);
	}
	
	@Override
	public Dimension getMaximumSize() {
		if (this.getParent().getSize().getWidth() > this.getPreferredSize().getWidth()) {
			max_size = false;
			return getPreferredSize();
		}
		else
		{
			max_size = true;
			return new Dimension((int)this.getParent().getSize().getWidth(), (int)this.getPreferredSize().getHeight());
		}
	}
	
    
    
    //paint the cards
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < cardImage.size(); i++) {	
			Image img = cardImage.get(i).getImage();
			g.drawImage(img,  i*PilePanel.OVERLAP , PilePanel.SPACE_Y, null);
		} 
	
	}
	
}
