import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CardPanel extends JPanel {
	
	
	public CardPanel() {
		this.setBackground(ShitHeadGUI.BGCOLOR);
	}
	
	public CardPanel(Cards hand) {
		this.setBackground(ShitHeadGUI.BGCOLOR);
	}
	
	/*
	@Override
	public Dimension getPreferredSize() {
		//return new Dimension(cardImage.size() * (CardImage.WIDTH + HandPanel.SPACE_X), CardImage.HEIGHT + 2*HandPanel.SPACE_Y);
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
    */
	
    
    
	
	
}
