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
import java.util.Observable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class TablePanel extends JPanel implements MouseListener, java.util.Observer  {

	public String[] s = new String[] {"Ace of Hearts", "2 of Diamonds", "3 of Clubs"};
	

	List<HandPanel> hands = new ArrayList<HandPanel>();
	PilePanel pilePanel;// = new PilePanel();
	JFrame frame = new JFrame("Shithead");
	DisplayPanel displayPanel;
	MessagePanel messagePanel;
		
	List<List<CardImage>> lstBtnHand = new ArrayList<List<CardImage>>();
	

	boolean waitingForClick = true;
	List<Integer> cardClickedID = new ArrayList<Integer>();
	
	Game controller;
	
	public TablePanel(Game controller) {
		this.controller = controller;
		init();
	}
	
	public TablePanel() {
		CardPng.init();
	}
	
	// call this first, before init();
	// call it from the controller before init();
	public void addHandPanels(List<ShitHand> h, Pile p) {
		// loop through all players + computer and add a new HnadPanel to GUI
		for (int i = 0; i < Game.NUM_OF_PLAYERS +1; i++) {
			hands.add(new HandPanel(h.get(i)));
			hands.get(i).addMouseListener(this);
		}
		
		pilePanel = new PilePanel(p);
	}
	
	public void init() {
		
		
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		//this.setPreferredSize(new Dimension(ShitHeadGUI.TABLE_WIDTH,ShitHeadGUI.TABLE_HEIGHT));
		this.setBackground(ShitHeadGUI.BGCOLOR);
		pilePanel.setLayout(new FlowLayout());

		//addHandPanels();

		
		JLabel lbComputer = new JLabel("Computer");
		JLabel lbPlayer = new JLabel("Player");
		
		lbComputer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lbPlayer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		this.add(lbComputer);
		this.add(hands.get(1)); // add computer
		this.add(pilePanel);
		this.add(hands.get(0)); // add player
		
		this.add(lbPlayer);
				
		//this.setBorder(new EmptyBorder(10,10,10,10));

	}
	


	
	public List<Integer> getGUIInput() {
		cardClickedID.clear();
		while(waitingForClick) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
		waitingForClick = true;
		return cardClickedID;
	}
	

	public int getCardClickedID(HandPanel handPanel, MouseEvent e) {
		Point p = e.getPoint();		
		int id = -1;
		if(!handPanel.isMaxSize()) {
			double tmp = p.getX() % (CardImage.WIDTH + HandPanel.SPACE_X);
			if (tmp <= CardImage.WIDTH)
				id = (int) p.getX() / (CardImage.WIDTH + HandPanel.SPACE_X);
		} else {
			id = (int) (p.getX() / handPanel.getOverlapWidth());
		}
		
		return id;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

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
	public void mousePressed(MouseEvent e) {

		for (int i = 0; i < hands.size(); i++) {
			if (e.getSource() == hands.get(i)) {
				int id = getCardClickedID(hands.get(i), e);

				if (id != -1) {
					if (SwingUtilities.isRightMouseButton(e))
					{
						Game.debug("right mouse clicked");
						if (!hands.get(i).hand.cards.get(id).getSelected()) {
							hands.get(i).hand.cards.get(id).selected(true);
							cardClickedID.add(id);
						} else if (hands.get(i).hand.cards.get(id).getSelected()) {
							hands.get(i).hand.cards.get(id).selected(false);
							cardClickedID.remove(Integer.valueOf(id));
						}
	
						refreshGUI();
					} 
					else if (SwingUtilities.isLeftMouseButton(e))
					{
						Game.debug("Left mouse clicked");
						if (hands.get(i).hand.size() != 0) {
							if (!hands.get(i).hand.cards.get(id).getSelected())
								cardClickedID.add(id);
						} else {
							cardClickedID.add(id);
						}
					
						waitingForClick = false;
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg) {
		Game.debug("update(Obserable, Object)");
		ShitModel model = (ShitModel)arg;

		refreshGUI();

		
	}
	
	public void refreshGUI() {
		for (int i = 0; i < hands.size(); i++) {
			hands.get(i).repaint();
			hands.get(i).revalidate();
		}
		
		pilePanel.repaint();
		pilePanel.revalidate();		
	}
	
	// temp method?
	public void display(String s) {
		System.out.println(s);
	}
	

}
