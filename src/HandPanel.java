import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HandPanel extends CardPanel {

	public static int SPACE_X = 10;
	public static int SPACE_Y = 10;
	
	public static int DEFAULT_PANEL_WIDTH = 3 * CardImage.WIDTH + 2 * SPACE_X; 

	boolean max_size = false;
	
	double overlap_width = -1;
	
	Hand hand; 
	ShitHand shitHand;
	//List<CardImage> cardImage;
	CardImage backCard = new CardImage("back");

	/*
	public HandPanel() {
		super();
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));

	}*/
	
	//this isn't really used?
	public HandPanel(ShitHand shitHand) {
		//super(hand.getFaceUp());
		this.shitHand = shitHand;
		this.hand = shitHand.getFaceUp();

	}
	
	@Override
	public Dimension getPreferredSize() {
		if (hand.size() >= Game.NUM_OF_CARDS) 
			return new Dimension(hand.size() * (CardImage.WIDTH + HandPanel.SPACE_X) - HandPanel.SPACE_X, CardImage.HEIGHT + 2 * HandPanel.SPACE_Y);
		else
			return new Dimension(CardImage.WIDTH*3 + 2 * HandPanel.SPACE_X, CardImage.HEIGHT + 2 * HandPanel.SPACE_Y);
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
	
	@Override
	public Dimension getMinimumSize() {
		//return new Dimension(CardImage.WIDTH*3 + 2 * HandPanel.SPACE_X, CardImage.HEIGHT + 2 * HandPanel.SPACE_Y);
		return new Dimension((int)this.getParent().getSize().getWidth(), (int)this.getPreferredSize().getHeight()); 
	}
	
	/*
    @Override
    public Dimension getPreferredSize() {
        //Image img = cardImage.get(0).getImage();
        //return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(null), img.getHeight(null));
    	return super.getPreferredSize();
    }*/
    
	public boolean isMaxSize() {
		return max_size;
	}
	
	public double getOverlapWidth() {
		return overlap_width;
	}
    
	public void selectCard(int id) {
		//cardImage.get(id).selected = true;
	}
	
    //paint the cards
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D)gr;
		
		double centre = this.getSize().getWidth()/2;

		//draw the face down cards (3 of them)
		//for (int i = 0; i < Game.NUM_OF_CARDS; i++) {
		for (int i = 0; i < shitHand.getFaceDown().size(); i++) {
			double posx = (-1.5 + i) * CardImage.WIDTH + (i-1) * HandPanel.SPACE_X + centre;
			g.drawImage(backCard.getImage(), (int)posx, 0, null);
		}
		
		/*
		//when you have 2 or less cards, because of face down cards the panel is a little bit bigger so you should place them accordingly
		if (cardImage.size() <= 2) {
			for (int i = 0; i < cardImage.size(); i++) {
				Image img = cardImage.get(i).getImage();

				if (cardImage.size() == 2) {
					
				}
				//g.drawImage(img, centre , arg2, arg3)
			}
		} else {
		*/
		
		if (!max_size) {
			for (int i = 0; i < hand.size(); i++) {	
				CardImage cardImage = new CardImage();
				cardImage.setImage(hand.getCard(i));
				Image img = cardImage.getImage();
				if (hand.getCard(i).selected) {
					g.drawImage(img,  i*(CardImage.WIDTH) + i * HandPanel.SPACE_X , 0, null);
				}
				else
					g.drawImage(img,  i*(CardImage.WIDTH) + i * HandPanel.SPACE_X , HandPanel.SPACE_Y, null);

			}
		} else {
			// here we have overlapping
			overlap_width = (this.getSize().getWidth())/hand.size();
			//double gap_width = (this.getParent().getSize().getWidth()-cardImage.size()*CardImage.WIDTH)/(cardImage.size()-1);
			
			
			for (int i = 0; i < hand.size(); i++) {
				//Image img = cardImage.get(i).getImage();
				CardImage cardImage = new CardImage();
				cardImage.setImage(hand.getCard(i));
				Image img = cardImage.getImage();
				if (hand.getCard(i).getSelected()) 
					g.drawImage(img,  (int)(i*overlap_width),  0,  null);
				else 
					g.drawImage(img,  (int)(i*overlap_width),  HandPanel.SPACE_Y,  null);
				
			}
		}

		
	
	}
	
	
}
