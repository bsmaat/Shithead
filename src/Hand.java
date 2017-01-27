
public class Hand extends Cards {

	Pile pile;
	
	public Hand() {
		super(0);
	}
	
	public Hand(int n) {
		super(n);
	}

	public Hand(Hand h) {
		super(h.size());
		this.cards = copy(h).getCards();
	}
	
	public Hand copy(Hand h) {
		Hand newHand = new Hand(h.size());
		for (int i = 0; i < h.size(); i++) {
			newHand.addCard(new Card(h.getCard(i)));
		}
		return newHand;
	}
	
	public void setPile(Pile pile) {
		this.pile = pile;
	}
	
	public void printHand() {
		for (int i = 0; i < this.size(); i++) {
			System.out.println(getCard(i));
		}
	}
	
	public void dropCard(int id) {
		Card c = new Card(this.getCard(id));
		pile.addCard(c);
		this.removeCardById(id);
	}
	
	public void dropCard(Card c) {
		pile.addCard(c);
		this.removeCard(c);
	}
	
	public void pickUpPile() {
		this.addCards(pile.getCards());
		pile.clear();
	}
	
	public boolean isCardPlayable(int id) {
		if (pile.isEmpty()) {
			return true;
		} else {
			Card c = this.cards.get(id);
			if (pile.peakCardFromTop().isMagicCard()) {
				System.out.println("Magic card!");
				if (c.isLessThanOrEqualTo(pile.peakCardFromTop())) {
					System.out.println("YEAH OKAY");
					return true;
				} else {
					System.out.println("NO");
					return false;
				}
			} else if (!c.isGreaterThanOrEqualTo(pile.peakCardFromTop())) {
				return false;
			} else
				return true;
		}
	}
	
	public boolean isPlayable() {
		for (int i = 0; i < this.size(); i++) {
			if (this.isCardPlayable(i)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	public boolean isCardPlayable(int id) {
		if (isEmpty()) {
			return true;
		} else {
			Card c = this.cards.get(id);
			if (!c.isGreaterThanOrEqualTo(pile.peakCardFromTop())) {
				return false;
			} else
				return true;
		}
	}
*/
	
	/*
	public boolean isPlayable() {
		//check it's a clear pile
		if (pile.isEmpty()) {
			return true;
		} else if (pile.peakCardFromTop() == MagicCard.SEVEN) { 
			if (this.getSmallestCardLessThanOrEqualTo(pile.peakCardFromTop()) != null) {
				return true;
			}
		}
		//else check for card greater than card on table
		else if (this.getSmallestCardGreaterThanOrEqualTo(pile.peakCardFromTop()) != null) {
			return true;
		}
		return false;
	}*/
	
	
}
