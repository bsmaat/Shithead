import java.util.ArrayList;
import java.util.List;

public class Deck extends Cards {
	
	public Deck() {
		super(52);
		initialiseDeck();
	}

	public void initialiseDeck() {
		for(int j = 1; j < 5; j++) {
			for(int k = 2; k < 15; k++) { // 2 to 14 since ace is high (A = 14)
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
	
	// deal ShitHand (face up and face down cards)
	public List<ShitHand> dealHands(int numOfHands, int sizeOfHand) {
		List<ShitHand> hands = new ArrayList<ShitHand>(numOfHands);
		for (int i = 0; i < numOfHands; i++) {
			hands.add(new ShitHand(dealHand(sizeOfHand), dealHand(sizeOfHand)));
		}
		return hands;
		
	}
}
