import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class ShitHeadGUI extends JPanel implements ActionListener {

	public String[] s = new String[] {"Ace of Hearts", "2 of Diamonds", "3 of Clubs"};
	

	//JPanel pile = new JPanel();
	List<JPanel> hands = new ArrayList<JPanel>();
	JPanel pilePanel = new JPanel();
	JFrame frame = new JFrame("Shithead");
	
	List<List<JButton> > lstBtnHand = new ArrayList<List<JButton> >();
	
	boolean waitingForClick = true;
	int btnID = -1;
	public ShitHeadGUI() {
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));#
		this.setLayout(new GridLayout(3, 1));
		this.setPreferredSize(new Dimension(800,300));
		
		pilePanel.setLayout(new GridLayout(1,0));

		
		for (int i = 0; i < Game.NUM_OF_PLAYERS +1; i++) {
			lstBtnHand.add(new ArrayList<JButton>());
			hands.add(new JPanel());
			this.add(hands.get(i));
			hands.get(i).setLayout(new GridLayout(1,0));
		}
		
		frame.setVisible(true);
		frame.add(this);
		frame.pack();	

	}
	
	public void addHand(int id, Hand h) {
		hands.get(id).removeAll();
		
		
		List<JButton> btnCard = new ArrayList<JButton>();
		
		for (int i = 0; i < h.size(); i++) {
			JButton btn = new JButton("<html><center>" + h.getCard(i).toString().replaceAll(" ", "<br>") + "</center></html>");
			btn.addActionListener(this);
			btnCard.add(btn);
			hands.get(id).add(btnCard.get(i));
		}	
		lstBtnHand.add(btnCard);


	}
	
	public void displayCards(List<ShitHand> lstHands, Pile pile) {
		for(int i = 0; i < lstHands.size(); i++) {
			addHand(i, lstHands.get(i).getFaceUp());	
		}
		
		pilePanel.removeAll();
		if (pile.isEmpty()) {
			pilePanel.add(new JButton("Empty pile"));
		} else 
			pilePanel.add(new JButton(pile.peakCardFromTop().toString()));
		
		this.add(pilePanel);

		//need to pack to get consistency
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		waitingForClick = false;
		System.out.println("HERE");
		for (int i = 0; i < lstBtnHand.size(); i++) {
			List<JButton> btnHand = lstBtnHand.get(i);
			for (int j = 0; j < btnHand.size(); j++) {
				if (e.getSource() == btnHand.get(j)) {
					btnID = j;
					//System.out.println(btnHand.get(j).getText());
				}
			}
		}
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
}
