import java.util.List;
import java.util.Scanner;

public class Game {

	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}
	
	Deck deck;
	Pile pile;
	
	public Game() {
		pile = new Pile();
	}
	
	public void startGame() {
		int numOfPlayers = 2;
		Scanner scanner = new Scanner(System.in);
		String input;
		int id;
		
		deck = new Deck();
		//deck.shuffle();
		List<ShitHand> hands = deck.dealHands(numOfPlayers, 3);
		hands.get(0).printCards();
		hands.get(1).printCards();
		
		hands.get(0).setPile(pile);
		hands.get(1).setPile(pile);
		
		//begin pile
		Card c = new Card(deck.peakCardFromTop());
		pile.addCard(c);
		
		while(true) {
			for (int i = 0; i < numOfPlayers; i++) {
				System.out.println("Player " + i + " cards: ");
				hands.get(i).printCards();
			}
			
			for (int i = 0; i < numOfPlayers; i++) {
				System.out.print("Player " + i + ", drop a card: ");
				input = scanner.nextLine();
				id = Integer.parseInt(input);
				hands.get(i).getFaceUp().dropCard(id);
			}
			System.out.println("Pile: ");
			pile.printCards();			
		}
		
		//scanner.close();
		
	}
	
}
