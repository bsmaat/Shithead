
public class Card {

	int rank;
	int value;
	
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
	
	public String toString() {
		return (this.rank + " " + this.value);
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
