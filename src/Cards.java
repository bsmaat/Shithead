import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
	
	public void clear() {
		cards.clear();
	}
	
	public Card getCard(int n) {
		return cards.get(n);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public Card getMaxValueCard() {
		Card maxCard = null;
		for(Card c : cards) {
			if (maxCard == null) {
				maxCard = c;
			} else if (maxCard.getValue() < c.getValue()) {
				maxCard = c;
			}
		}
		return maxCard;
	}
	
	public Card getMinValueCard() {
		Card minCard = null;
		for (Card c : cards) {
			if (minCard == null) {
				minCard = c;
			} else if (minCard.getValue() > c.getValue()) {
				minCard = c;
			}
		}
		return minCard;
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
		if (!isEmpty()) {
			cards.remove(n);
			return true;
		} else
			return false;
	}

	public void sortBySuit() {
		
	}
	
	public boolean sortByValue(boolean ascending) {
		if (!isEmpty()) {
			if (!ascending) {
				Collections.sort(cards, new Comparator<Card>() {

					@Override
					public int compare(Card c1, Card c2) {
						int val = compare(c2.getValue(), c1.getValue());
						return val;
					}

					public int compare(int a, int b) {
						return a < b ? -1
								: a > b ? 1
										:0;
					}
				});
				return true;
			} else 	{
				Collections.sort(cards, new Comparator<Card>() {

					@Override
					public int compare(Card c1, Card c2) {
						int val = compare(c1.getValue(), c2.getValue());
						return val;
					}

					public int compare(int a, int b) {
						return a < b ? -1
								: a > b ? 1
										:0;
					}
				});
				return true;
			}
		} else
			return false;
			
	}

	public Card removeCardFromBottom() {
		if (!isEmpty()) {
			Card c = new Card(cards.get(0));
			cards.remove(0);
			return c;
		} else
			return null;
	}
	
	public Card removeCardFromTop() {
		if (!isEmpty()) {
			Card c = new Card(cards.get(cards.size()-1));
			cards.remove(cards.size()-1);
			return c;
		} else
			return null;
		
	}

	public Card peakCardFromBottom() {
		if (!isEmpty()) 
			return new Card(cards.get(0));
		else
			return null;
	}
	
	public Card peakCardFromTop() {
		if (!isEmpty()) 
			return new Card(cards.get(cards.size()-1));
		else
			return null;
	}
	
	public Card getSmallestCardGreaterThanOrEqualTo(Card c) {
		if (!isEmpty()) {
			this.sortByValue(true);
			for (int i = 0; i < cards.size(); i++) {
				if (this.cards.get(i).isGreaterThanOrEqualTo(c)) {
					return this.cards.get(i);
				}
			}
		} 
		return null;
	}
	
	public Card getSmallestCardLessThanOrEqualTo(Card c) {
		if (!isEmpty()) {
			this.sortByValue(true);
			for (int i = 0; i < cards.size(); i++) {
				if (this.cards.get(i).isLessThanOrEqualTo(c)) {
					return this.getCard(i);
				}
			}
		}
		return null;
	}
	
	public void printCards() {
		if (!isEmpty()) {
			for (int i = 0; i < cards.size(); i++) {
				String s = "\t" + i + ": " + cards.get(i) + ", ";
				Game.display(s);
				//System.out.print("\t");
				//System.out.println(i + ": " + cards.get(i) + ", ");
			}
			//System.out.println(cards.size()-1 + ": " + cards.get(cards.size()-1));
		} else
			//System.out.println("Empty hand");
			Game.display("Empty hand");
		Game.display();
		//System.out.println();
	}
	
	public boolean isEmpty() {
		if (cards.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
