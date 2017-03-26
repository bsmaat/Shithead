/**
 * 
 * @author billy
 * The card class
 * 
 */
public class Card {

	/** 
	 * Rank of card (0..3)
	 */
	private int rank;
	
	/**
	 * Value of card (1..13)
	 */
	private int value;
	
	/*
	 * 
	 * 
	 */
	private boolean selected = false;
	
	//public static final int ACE = 1;
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
	public static final int ACE = 14; // ACE is high
	/**
	 * Card rank
	 */
	public static final int HEARTS = 1;
	/**
	 * Card rank
	 */
	public static final int DIAMONDS = 2;
	/**
	 * Card rank
	 */
	public static final int SPADES = 3;
	/**
	 * Card rank
	 */
	public static final int CLUBS = 4;

	
	/**
	 * Default constructor
	 */
	public Card() {
	}
	
	/**
	 * Make new card given card as parameter
	 * @param c
	 */
	public Card(Card c) {
		this.rank = c.rank;
		this.value = c.value;
	}
	
	/**
	 * Make new card given rank and value as parameters
	 * @param rank
	 * @param value
	 */
	public Card(int rank, int value) {
		this.rank = rank;
		this.value = value;
	}
	
	/**
	 * Copy card c 
	 * @param c
	 * @return
	 */
	public Card copy(Card c) {
		Card newCard = new Card();
		newCard.rank = c.rank;
		newCard.value = c.value;
		return newCard;
	}
	
	/**
	 * Return string representation of card
	 */
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
				str1 = "Jack";
				break;
			case(Card.QUEEN):
				str1 = "Queen";
				break;
			case(Card.KING):
				str1 = "King";
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
	
	/**#
	 * Get card rank
	 * @return
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Get card value
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Set card rank
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * Set card value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Compare card to card c by value. If this card is: greater than c, return 1; equal to c, return 0;
	 *  less than c, return -1;.
	 * @param c
	 * @return
	 */
	public int compareTo(Card c) {
		if (this.getValue() > c.getValue()) {
			return 1;
		} else if (this.value == c.getValue()) {
			return 0;
		} else 
			return -1;
	}
	
	/**
	 * Compare this card to card c by value
	 * @param c
	 * @return
	 */
	public boolean isGreaterThanOrEqualTo(Card c) {
		if (this.compareTo(c) == 1 || this.compareTo(c) == 0) 
			return true;
		else
			return false;
	}
	
	public boolean isLessThanOrEqualTo(Card c) {
		if (this.compareTo(c) == -1 || this.compareTo(c) == 0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Check if two cards are equal by rank and value
	 * @param c
	 * @return
	 */
	public boolean equals(Card c) {
		if (this.rank == c.rank && this.value == c.value) 
			return true;
		else
			return false;
	}
	
	public boolean getSelected() 
	{
		return selected;
	}
	
	public void selected(boolean selected) {
		this.selected = selected;
	}


}
