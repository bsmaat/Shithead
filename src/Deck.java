import java.util.ArrayList;
import java.util.List;

public class Deck extends Cards {
	
	public Deck() {
		super(52);
		initialiseDeck();
	}

	public void initialiseDeck() {
		for(int j = 1; j < 5; j++) {
			for(int k = 1; k < 14; k++) {
				cards.add(new Card(j, k));
			}
		}
	}

	public Hand dealHand(int sizeOfHand) {
		Hand hand = new Hand(sizeOfHand);
		
		for (int i = 0; i < sizeOfHand; i++) {
			Card card = new Card(this.peakCardFromTop());
			hand.addCard(card);
			this.removeCardFromTop();
		}
		
		return hand;
	}
	
	public List<Hand> dealHands(int numOfHands, int sizeOfHand) {
		List<Hand> hands = new ArrayList<Hand>(numOfHands);
		for (int i = 0; i < numOfHands; i++) {
			hands.add(dealHand(sizeOfHand));
		}
		return hands;
		
	}
}
