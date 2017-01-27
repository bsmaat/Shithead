import java.util.List;
import java.util.Scanner;

public class Game {

	
	static final int NUM_OF_CARDS = 3;
	static final int NUM_OF_PLAYERS = 1;
	static int COMPUTER_INDEX = 0;
	static final boolean COMPUTER_ON = true;

	
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
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input;
		int id;
		
		deck = new Deck();
		List<ShitHand> hands;

		deck.shuffle();
		if (COMPUTER_ON) {
			hands = deck.dealHands(NUM_OF_PLAYERS+1, NUM_OF_CARDS);
			COMPUTER_INDEX = hands.size()-1;
		} else {
			hands = deck.dealHands(NUM_OF_PLAYERS, NUM_OF_CARDS);
		}
		
		for (int i = 0; i < hands.size(); i++)
			hands.get(i).setPile(pile);


		
		//begin pile
		//Card c = new Card(deck.peakCardFromTop());
		pile.addCard(new Card(deck.removeCardFromTop()));
		

		while(true) {
			System.out.println("Pile: ");
			pile.printCards();
			
			for (int i = 0; i < NUM_OF_PLAYERS; i++) {
				System.out.println("Player " + i + " cards: ");
				hands.get(i).faceUp.sortByValue(true); // sort by ascending value
				hands.get(i).faceUp.printCards();
			}
			
			if(COMPUTER_ON) {
				System.out.println("Computer cards: ");
				hands.get(COMPUTER_INDEX).getFaceUp().sortByValue(true); // sort by ascending value
				hands.get(COMPUTER_INDEX).getFaceUp().printCards();
			}
			
			for (int i = 0; i < NUM_OF_PLAYERS; i++) {
				
				// if the hand is playable, allow player to select next card
				if (hands.get(i).getFaceUp().isPlayable()) {
					boolean playable = false;
					while (!playable) {
						System.out.print("Player " + i + ", drop a card: ");
						input = scanner.nextLine();
						id = Integer.parseInt(input);
						if (hands.get(i).getFaceUp().isCardPlayable(id)) {
							playable = true;
							hands.get(i).getFaceUp().dropCard(id); // drop card (adds to pile automatically)
							Card pickUpCard = new Card(deck.removeCardFromTop()); // pick up card from top of deck
							hands.get(i).getFaceUp().addCard(pickUpCard); // pick up card
						} else {
							System.out.println(hands.get(i).getFaceUp().getCard(id) + " is not playable!");
						}
					}
				} else {
					System.out.println("Not playable, player " + i + " picks up");
					hands.get(i).getFaceUp().pickUpPile();
				}
			}
			
			//computer plays
			if (COMPUTER_ON) { 
				System.out.println("Computer player");
				//hands.get(COMPUTER_INDEX).getFaceUp().printCards();
				if(hands.get(COMPUTER_INDEX).getFaceUp().isPlayable()) {
					Card c;
					if (!pile.isEmpty()) {
						if (pile.peakCardFromTop().isMagicCard()) {
							c = hands.get(COMPUTER_INDEX).getFaceUp().getMinValueCard();
						} else {
							c = hands.get(COMPUTER_INDEX).getFaceUp().getSmallestCardGreaterThanOrEqualTo(pile.peakCardFromTop());
						}
						if (c != null) {
							hands.get(COMPUTER_INDEX).getFaceUp().dropCard(c);
							Card pickUpCard = new Card(deck.removeCardFromTop());
							hands.get(COMPUTER_INDEX).getFaceUp().addCard(pickUpCard);
						} else {
							System.out.println("Computer does not have a higher card. Picking up...");
							hands.get(COMPUTER_INDEX).getFaceUp().pickUpPile();
						}
					} else {
						// if it's empty just drop the lowest card
						//System.out.println("Computer dropping lowest card");
						c = hands.get(COMPUTER_INDEX).getFaceUp().getMinValueCard();
						hands.get(COMPUTER_INDEX).getFaceUp().dropCard(c);
						Card pickUpCard = new Card(deck.removeCardFromTop());
						hands.get(COMPUTER_INDEX).getFaceUp().addCard(pickUpCard);
					}
					System.out.println("Computer played " + c);
				} else {
					System.out.println("Computer can't playing, picking up");
					hands.get(COMPUTER_INDEX).getFaceUp().pickUpPile();
				}
			}
			
						
		}
		
		
		//scanner.close();
		
	}
	
	public boolean checkIfCanDrop(Hand hand) {
		boolean result = false;
		return result;
	}
	
}
