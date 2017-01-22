import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

	List<Card> cards;
	
	public Cards() {
		cards = new ArrayList<Card>();
	}
	
	public Cards(int numOfCards) {
		cards = new ArrayList<Card>(numOfCards);
	}
	
	public Cards(Cards cards) {
		this.cards = new ArrayList<Card>(cards.size());
	}
	
	public Cards copy(Cards cards) {
		Cards copied = new Cards(cards.size());
		for(int i = 0; i < cards.size(); i++) {
			copied.addCard(cards.getCard(i));
		}
		return copied;
	}
	
	public Card getCard(int n) {
		return cards.get(n);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public int size() {
		return cards.size();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}	
	
	public void addCards(List<Card> c) {
		cards.addAll(c);
	}
	
	public void setCardById(int n, Card card) {
		this.cards.set(n,  card);
	}

	public boolean removeCard(Card card) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).equals(card)) {
				cards.remove(i);
				return true;
			} 
		}
		return false;
	}
	
	public boolean removeCardById(int n) {
		cards.remove(n);
		return true;
	}

	public void sortBySuit() {
		
	}
	
	public void sortByValue() {
		
	}

	public Card removeCardFromBottom() {
		Card c = new Card(cards.get(0));
		cards.remove(0);
		return c;
	}
	
	public Card removeCardFromTop() {
		Card c = new Card(cards.get(cards.size()-1));
		cards.remove(cards.size()-1);
		return c;
	}

	public Card peakCardFromBottom() {
		return new Card(cards.get(0));
	}
	
	public Card peakCardFromTop() {
		return new Card(cards.get(cards.size()-1));
	}
	
	public void printCards() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(i + ": " + cards.get(i));
		}
	}

}
