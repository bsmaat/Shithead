
/*
 * ShitHand is the hand in Shithead: three face down cards and face up cards
 */
public class ShitHand {// extends Hand {
	
	Hand faceUp;
	Hand faceDown;
		
	class Size {
		public int upSize;
		public int downSize;
		public Size(int x, int y) {
			upSize = x;
			downSize = y;
		}
		public String toString() {
			return (upSize + " up, " + downSize + " down");
		}
	}
	
	public ShitHand() {
		// TODO Auto-generated constructor stub
	}
	
	public ShitHand(Hand faceUp, Hand faceDown) {
		this.faceUp = new Hand(faceUp);
		this.faceDown = new Hand(faceDown);
	}

	public void setPile(Pile pile) {
		faceUp.setPile(pile);
		faceDown.setPile(pile);
	}
	
	public void printCards() {
		System.out.println("Face down: "); 
		faceDown.printCards();
		System.out.println("Face up: ");
		faceUp.printCards();
	}
	
	public boolean removeCardById(int id) {
		faceUp.removeCardById(id);
		return true;
	}
	
	public Size size() {
		return new Size(faceUp.size(), faceDown.size());
	}
	
	public void dropFaceUpCard(int id) {
		faceUp.dropCard(id);
	}
	
	public void dropFaceDownCard(int id) {
		faceDown.dropCard(id);
	}
	
	public Card getCardFaceUpById(int id) {
		return new Card(faceUp.getCard(id));
	}
	
	public Hand getFaceUp() {
		return faceUp;
	}
	
	public Hand getFaceDown() {
		return faceDown;
	}
	

}
