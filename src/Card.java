
public class Card {

	int rank;
	int value;
	
	public static final int ACE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	
	public static final int HEARTS = 1;
	public static final int DIAMONDS = 2;
	public static final int SPADES = 3;
	public static final int CLUBS = 4;

	
	public Card() {
	}
	
	public Card(Card c) {
		this.rank = c.rank;
		this.value = c.value;
	}
	public Card(int rank, int value) {
		this.rank = rank;
		this.value = value;
	}
	
	public Card copy(Card c) {
		Card newCard = new Card();
		newCard.rank = c.rank;
		newCard.value = c.value;
		return newCard;
	}
	
	public String toString() {
		String str1, str2;
		switch(this.value) {
			case(Card.ACE):
				str1 = "Ace";
				break;
			case(Card.TWO):
				str1 = "2";
				break;
			case(Card.THREE):
				str1 = "3";
				break;
			case(Card.FOUR):
				str1 = "4";
				break;
			case(Card.FIVE):
				str1 = "5";
				break;
			case(Card.SIX):
				str1 = "6";
				break;
			case(Card.SEVEN):
				str1 = "7";
				break;
			case(Card.EIGHT):
				str1 = "8";
				break;
			case(Card.NINE):
				str1 = "9";
				break;
			case(Card.TEN):
				str1 = "10";
				break;
			case(Card.JACK):
				str1 = "J";
				break;
			case(Card.QUEEN):
				str1 = "Q";
				break;
			case(Card.KING):
				str1 = "K";
				break;	
			default:
				str1 = "Error1";
		}
		
		switch(this.rank) {
			case(Card.HEARTS):
				str2 = "Hearts";
				break;
			case(Card.DIAMONDS):
				str2 = "Diamonds";
				break;
			case(Card.SPADES):
				str2 = "Spades";
				break;
			case(Card.CLUBS):
				str2 = "Clubs";
				break;
			default:
				str2 = "Error2";
		}
		
		return (str1 + " of " + str2);
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public boolean equals(Card c) {
		if (this.rank == c.rank && this.value == c.value) 
			return true;
		else
			return false;
	}


}
