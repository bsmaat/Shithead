import java.util.List;
import java.util.Scanner;

public class Game {

	
	static final int NUM_OF_CARDS = 3;
	static final int NUM_OF_PLAYERS = 1;
	static int COMPUTER_INDEX = 0;
	static final boolean COMPUTER_ON = true;
	static ShitHeadGUI gui;
	
	public static void main(String[] args) {
		gui = new ShitHeadGUI();
		
		Game game = new Game();
		game.startGame();
	}
	
	Deck deck;
	Pile pile;
	
	public Game() {
		pile = new Pile();
	}
	
	public static void display(String s) {
		System.out.println(s);;
	}
	
	public static void display(int s) {
		System.out.println(Integer.toString(s));
	}
	
	public static void display() {
		System.out.println();
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
			display("Pile: ");
			pile.printCards();
			
			
			for (int i = 0; i < NUM_OF_PLAYERS; i++) {
				display("Player " + i + " cards: ");
				hands.get(i).faceUp.sortByValue(true); // sort by ascending value
				hands.get(i).faceUp.printCards();
			}
			
			//gui.displayCards(hands, pile);

			
			if(COMPUTER_ON) {
				display("Computer cards: ");
				hands.get(COMPUTER_INDEX).getFaceUp().sortByValue(true); // sort by ascending value
				hands.get(COMPUTER_INDEX).getFaceUp().printCards();
			}
			
			
			//players play
			for (int i = 0; i < NUM_OF_PLAYERS; i++) {
				
				// if the hand is playable, allow player to select next card
				if (isHandPlayable(hands.get(i).getFaceUp())) {
					boolean playing = true;
					while (playing) {
						gui.displayCards(hands,  pile);
						System.out.print("Player " + i + ", drop a card: ");
						id = gui.getGUIInput();
						display(id);
						// need to wait for click instead here
						//input = scanner.nextLine(); 
						//id = Integer.parseInt(input);
						Card cardToPlay = hands.get(i).getFaceUp().getCard(id);
						if (isCardPlayable(cardToPlay)) {
							hands.get(i).getFaceUp().dropCard(id); // drop card (adds to pile automatically)							
							//if (isMagicCard(hands.get(i).getFaceUp().getCard(id)) == 10) { // if 10 clear pile
							if (isMagicCard(cardToPlay) == 10) {
								pile.clear();
								continue;
							}
							playing = false;
							pickUpCard(hands.get(i).getFaceUp());
						} else {
							display(hands.get(i).getFaceUp().getCard(id) + " is not playable!");
						}
					}
				} else {
					display("Not playable, player " + i + " picks up");
					hands.get(i).getFaceUp().pickUpPile();
				}
			}
			
			//computer plays
			if (COMPUTER_ON) { 
				display("Computer player");
				if (isHandPlayable(hands.get(COMPUTER_INDEX).getFaceUp())) {
					boolean playing = true;
					while(playing) {
						Card c;
						if (!pile.isEmpty()) {
							if (isMagicCard(pile.peakCardFromTop()) == 7) {
								c = hands.get(COMPUTER_INDEX).getFaceUp().getMinValueCard();
							} else {
								c = hands.get(COMPUTER_INDEX).getFaceUp().getSmallestCardGreaterThanOrEqualTo(pile.peakCardFromTop());
							}
							if (c != null) {
								hands.get(COMPUTER_INDEX).getFaceUp().dropCard(c);
								Card pickUpCard = new Card(deck.removeCardFromTop());
								pickUpCard(hands.get(COMPUTER_INDEX).getFaceUp());
								if (isMagicCard(c) == 10) {
									display("Computer cleared the pile with " + c);
									pile.clear();
									continue;
								}
							} else {
								display("Computer does not have a higher card. Picking up...");
								hands.get(COMPUTER_INDEX).getFaceUp().pickUpPile();
							}
						} else {
							// if it's empty just drop the lowest card
							c = hands.get(COMPUTER_INDEX).getFaceUp().getMinValueCard();
							hands.get(COMPUTER_INDEX).getFaceUp().dropCard(c);
							pickUpCard(hands.get(COMPUTER_INDEX).getFaceUp());
						}
						display("Computer played " + c);
						playing = false;
					}
				} else {
					display("Computer can't playing, picking up");
					hands.get(COMPUTER_INDEX).getFaceUp().pickUpPile();
				}
			}
			
			display("-------------------------------------------------------------------");
			
						
		}
		
		//scanner.close();
		
	}
	
	public void pickUpCard(Hand h) {
		while (h.size() < NUM_OF_CARDS) {
			if (!deck.isEmpty()) {
				Card pickUpCard = new Card(deck.removeCardFromTop());
				h.addCard(pickUpCard);
			} else
				break; // if deck is empty break out of loop
		} 
	}
	
	public boolean isCardPlayable(Card c) {
		if (pile.isEmpty()) {
			return true;
		} else {
			if (isMagicCard(c) == 2 || isMagicCard(c) == 10) { 
				// magic card 2 and 10 are always playable
				return true;
			}
			else if (isMagicCard(pile.peakCardFromTop()) == 7) {
				display("Magic card! Must go lower");
				if (c.isLessThanOrEqualTo(pile.peakCardFromTop())) {
					return true;
				} else {
					return false;
				}
			}
			else if (!c.isGreaterThanOrEqualTo(pile.peakCardFromTop())) {
				return false;
			} else
				return true;
		}
	}
	
	public boolean isHandPlayable(Hand h) {
		for (int i = 0; i < h.size(); i++) {
			if (isCardPlayable(h.getCard(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int isMagicCard(Card c) {
		if (c.getValue() == MagicCard.TWO.getValue()) {
			return 2;
		} else if (c.getValue() == MagicCard.SEVEN.getValue()) {
			return 7;
		} else if (c.getValue() == MagicCard.TEN.getValue()){
			return 10;
		} else
			return 0;
	}
}
