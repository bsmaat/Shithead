import java.util.ArrayList;
import java.util.List;

public class Game extends Cards {

	
	
	public static void main(String[] args) {
		Game game = new Game();
	}
	
	Deck deck;
	Hand hand = new Hand();
	
	public Game() {
		super(0);
	}
	
	public void startGame() {
		deck = new Deck();
		deck.shuffle();
		List<Hand> hands = deck.dealHands(2, 3);
		hands.get(0).printCards();
		hands.get(1).printCards();
	}
	
}
