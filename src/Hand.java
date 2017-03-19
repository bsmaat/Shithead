
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
	
	// drop multiple cards
	public void dropCard(int[] id) {
		for (int i = 0; i < id.length; i++) {
			Card c = new Card(this.getCard(id[i]));
			pile.addCard(c);
			this.removeCardById(id[i]);
		}
	}
	
	public void dropCard(Card c) {
		pile.addCard(c);
		this.removeCard(c);
	}
	
	public void pickUpPile() {
		this.addCards(pile.getCards());
		pile.clear();
	}
	
	public Card getLowestCard() {
		Card lowCard = null;
		for(Card c : cards) {
			if (lowCard == null) {
				lowCard = c;
			} else if (lowCard.getValue() > c.getValue() && Game.isMagicCard(c) == 0) {
				lowCard = c;
				return lowCard;
			}
		}
		return lowCard;
	}
	
	public Card getLowestCardGreaterThanOrEqualTo(Card card) {
		Card lowCard = null;
		for (Card c: cards) {
			if (c.getValue() >= card.getValue() && Game.isMagicCard(c) == 0)
			{
				lowCard = c;
				return lowCard;
			}			
		}
		return lowCard;
	}

	
}
