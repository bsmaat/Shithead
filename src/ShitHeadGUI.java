import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class ShitHeadGUI extends JPanel implements MouseListener {

	public String[] s = new String[] {"Ace of Hearts", "2 of Diamonds", "3 of Clubs"};
	
	public static Color BGCOLOR = Color.GREEN;
	public static int FRAME_WIDTH = 300;
	public static int FRAME_HEIGHT = 300;

	//JPanel pile = new JPanel();
	List<HandPanel> hands = new ArrayList<HandPanel>();
	PilePanel pilePanel = new PilePanel();
	JFrame frame = new JFrame("Shithead");
	
	//List<List<JButton> > lstBtnHand = new ArrayList<List<JButton> >();
	//List<List<CardLabel> > lstBtnHand = new ArrayList<List<CardLabel> >();
	List<List<CardImage>> lstBtnHand = new ArrayList<List<CardImage>>();
	

	boolean waitingForClick = true;
	int btnID = -1;
	
	Game controller;
	
	public ShitHeadGUI(Game controller) {
		this.controller = controller;
		init();
	}
	
	public ShitHeadGUI() {
		init();

	}
	
	public void init() {
		//this.setLayout(new GridLayout(3, 1));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(ShitHeadGUI.FRAME_WIDTH,ShitHeadGUI.FRAME_HEIGHT));
		this.setBackground(BGCOLOR);
		pilePanel.setLayout(new FlowLayout());

		
		// loop through all players + computer and add a new HnadPanel to GUI
		for (int i = 0; i < Game.NUM_OF_PLAYERS +1; i++) {
			lstBtnHand.add(new ArrayList<CardImage>());
			hands.add(new HandPanel());
			hands.get(i).addMouseListener(this);
			
			//this.add(hands.get(i));
		}
		
		// only focus on two players now
		//this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(hands.get(1)); // add computer
		//this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(pilePanel);
		//this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(hands.get(0)); // add player
		//this.add(Box.createRigidArea(new Dimension(0,10)));
		
		
		frame.setMinimumSize(this.getSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		frame.pack();	
	}
	
	public void addHand(int id, Hand h) {
		hands.get(id).removeAll(); // empty the handpanel
		hands.get(id).clearCards();
		//List<JButton> btnCard = new ArrayList<JButton>();
		List<CardImage> btnCard = new ArrayList<CardImage>();
		
		for (int i = 0; i < h.size(); i++) {
			CardImage btn = new CardImage(h.getCard(i));
			//btn.addMouseListener(this);
		
			btnCard.add(btn);
			
			hands.get(id).addCardToHand(btnCard.get(i));
		}	
		lstBtnHand.add(btnCard);

	}
	
	
	public void addPile(PilePanel pilePanel, Pile pile) {
		for (int i = 0; i < pile.size(); i++) {
			CardImage cardImage = new CardImage(pile.getCard(i));
			pilePanel.addCardToHand(cardImage);
		}
	}
	
	public void displayCards(List<ShitHand> lstHands, Pile pile) {	
		for(int i = 0; i < lstHands.size(); i++) {
			addHand(i, lstHands.get(i).getFaceUp());	
		}
		
		
		pilePanel.clearCards();
		pilePanel.removeAll();
		if (pile.isEmpty()) {
			pilePanel.add(new JLabel("Empty pile"));
		} else {
			//pilePanel.add(new CardLabel(pile.peakCardFromTop()));
			addPile(pilePanel, pile);
		}
	
		//this.add(pilePanel);
		this.repaint();
		//need to pack to get consistency
		frame.pack();
	}

	
	public int getGUIInput() {
		while(waitingForClick) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
		waitingForClick = true;
		return btnID;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//waitingForClick = false;
		
		// loop through all HandPanels
		for (int i = 0; i < hands.size(); i++) {
			if (e.getSource() == hands.get(i)) {
				System.out.println("Hand " + i + " clicked on");
				int id = getCardClickedID(e);
				System.out.println(id);
				btnID = id;
				waitingForClick = false;

			}
		}
		
		/*
		for (int i = 0; i < lstBtnHand.size(); i++) {
			List<CardImage> btnHand = lstBtnHand.get(i);
			for (int j = 0; j < btnHand.size(); j++) {
				if (e.getSource() == btnHand.get(j)) {
					btnID = j;
					//System.out.println(btnHand.get(j).getText());
				}
			}
		}
		*/
	}

	public int getCardClickedID(MouseEvent e) {
		Point p = e.getPoint();
		System.out.println(p.getX() + ", " + p.getY());
		
		int xmod = (int) p.getX() / (CardImage.WIDTH + HandPanel.SPACE_X);
		
		return xmod;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
