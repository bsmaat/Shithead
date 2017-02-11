import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HandPanel extends CardPanel {

	public static int SPACE_X = 10;
	public static int SPACE_Y = 0;

	boolean max_size = false;
	
	//List<CardImage> cardImage;
	
	public HandPanel() {
		super();
	}
	
	public HandPanel(Hand hand) {
		super(hand);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(cardImage.size() * (CardImage.WIDTH + HandPanel.SPACE_X) - HandPanel.SPACE_X, CardImage.HEIGHT + 2* HandPanel.SPACE_Y);
	}
	
	@Override
	public Dimension getMaximumSize() {
		// if size of handpanel is bigger than the width of the preferred size, 
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
	
	/*
    @Override
    public Dimension getPreferredSize() {
        //Image img = cardImage.get(0).getImage();
        //return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(null), img.getHeight(null));
    	return super.getPreferredSize();
    }*/
    
    
    //paint the cards
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		if (!max_size) {

			for (int i = 0; i < cardImage.size(); i++) {	
				Image img = cardImage.get(i).getImage();
				//g.drawImage(img, i*(CardImage.WIDTH + HandPanel.SPACE_X) + HandPanel.SPACE_X, HandPanel.SPACE_Y, null);				
				//g.drawImage(img,  i * (CardImage.WIDTH + HandPanel.SPACE_X) + HandPanel.SPACE_X,
				g.drawImage(img,  i*(CardImage.WIDTH) + i * HandPanel.SPACE_X , HandPanel.SPACE_Y, null);
			}
		} else {
			// here we have overlapping
			double width = (this.getParent().getSize().getWidth())/cardImage.size();
			//double gap_width = (this.getParent().getSize().getWidth()-cardImage.size()*CardImage.WIDTH)/(cardImage.size()-1);
			
			for (int i = 0; i < cardImage.size(); i++) {
				Image img = cardImage.get(i).getImage();
				//g.drawImage(img, (int) (width/2 + i*width - CardImage.WIDTH/2), HandPanel.SPACE_Y, null);
				g.drawImage(img,  (int)(i*width),  HandPanel.SPACE_Y,  null);
			}
		}
		
	
	}
	
	
}
