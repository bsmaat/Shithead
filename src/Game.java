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
			display("DECK SIZE: " + deck.size());
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
				// first check if its a hand with faceUp cards that we can play
				int isShitHandPlayable = isShitHandPlayable(hands.get(i));
				if (isShitHandPlayable == 1) {
					boolean playing = true;
					while (playing) {
						gui.displayCards(hands,  pile);
						System.out.print("Player " + i + ", drop a card: ");
						//get which card is being clicked on
						id = gui.getGUIInput();
						gui.displayCards(hands,  pile);
						wait(1000);
						// need to wait for click instead here
						//input = scanner.nextLine(); 
						//id = Integer.parseInt(input);
						
						int output = tryToPlayCard(hands.get(i), id);
						if (output == 0) 
							playing = true;
						else if (output == 1)
							playing = false;
						else if (output == 2)
							playing = true;					
					}
				} else if (isShitHandPlayable == 2) { // if we have only faceDown cards, should be able to just pick one of them
					display("On to face down cards");
					boolean playing = true;
					while (playing) {
						gui.displayCards(hands,  pile);
						display("Player " + i + ", pick a face down card");
						id = gui.getGUIInput();

						//int output = 1;
						int output = tryToPlayCard(hands.get(i), id);
						if (output == 0) {
							// card can't be played, will pick up card
							playing = false;
						} 
						else if (output == 1) 
							// card was played
							playing = false;
						else if (output == 2) {
							//card was played, and it's still our go
							playing = true;
						}
					}
				}
				else if (isShitHandPlayable == 0) {
					display("Not playable, player " + i + " picks up");
					hands.get(i).getFaceUp().pickUpPile();
				} else {
					display("Error: we shouldn't get here!");
				}
			}
			
			display("DECK SIZE: " + deck.size());
			gui.displayCards(hands,  pile);

			wait(2000);
			
			//computer plays
			if (COMPUTER_ON) { 
				display("Computer player");
				if (isHandPlayable(hands.get(COMPUTER_INDEX).getFaceUp())) {
					boolean playing = true;
					while(playing) {
						Card c;
						if (!pile.isEmpty()) {
							if (isMagicCard(pile.peakCardFromTop()) == 7) {
								c = hands.get(COMPUTER_INDEX).getFaceUp().getLowestCard();
							} else {
								c = hands.get(COMPUTER_INDEX).getFaceUp().getSmallestCardGreaterThanOrEqualTo(pile.peakCardFromTop());
							}
							if (c != null) {
								hands.get(COMPUTER_INDEX).getFaceUp().dropCard(c);
								//Card pickUpCard = new Card(deck.removeCardFromTop());
								if (isMagicCard(c) == 10) {
									display("Computer cleared the pile with " + c);
									pile.clear();
									continue;
								}
								pickUpCard(hands.get(COMPUTER_INDEX).getFaceUp());

							} else {
								display("Computer does not have a higher card. Picking up...");
								hands.get(COMPUTER_INDEX).getFaceUp().pickUpPile();
							}
						} else {
							// if it's empty just drop the lowest card
							c = hands.get(COMPUTER_INDEX).getFaceUp().getLowestCard();
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
			display("DECK SIZE: " + deck.size());

			display("-------------------------------------------------------------------");
			
						
		}
		
		//scanner.close();
		
	}
	
	/*
	 * returns 0 if we couldn't play the card
	 * returns 1 if we successfully played a regular card and don't need to go again
	 * returns 2 if we played a magic card and need another go (i.e card = 10);
	 */
	public int tryToPlayCard(ShitHand h, int id) {
		Card cardToPlay = new Card();
		
		// if the faceUp cards are empty, then we probably clicked on a face down card
		if (h.getFaceUp().isEmpty()) {
			//pick up a facedown card and move it to the faceup cards
			h.getFaceUp().addCard(h.getFaceDown().getCard(id));
			h.getFaceDown().removeCardById(id);
		} else
			cardToPlay = h.getFaceUp().getCard(id);
		
		if (isCardPlayable(cardToPlay)) {
			h.getFaceUp().dropCard(id); // drop card (adds to pile automatically)							
			if (isMagicCard(cardToPlay) == 10) {
				pile.clear();
				return 2;
			}
			//playing = false;
			pickUpCard(h.getFaceUp());
			return 1; //successfully played card
		} else {
			display(h.getFaceUp().getCard(id) + " is not playable!");
			return 0;
		}
	}
	
	
	public void pickUpCard(Hand h) {
		while (h.size() < NUM_OF_CARDS) {
			if (!deck.isEmpty()) {
				Card pickUpCard = new Card(deck.removeCardFromTop());
				h.addCard(pickUpCard);
			} else {
				//Game over, Hand h has won!
				gameOver(h);
				break; // if deck is empty break out of loop
			}
		} 
	}
	
	public void gameOver(Hand h) {
		display("GAME OVER!");
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
	
	/* checks to see if faceup hand has any cards playable
	 * if not, or if there are no faceup cards, then no card playable
	 */
	
	public boolean isHandPlayable(Hand h) {
		for (int i = 0; i < h.size(); i++) {
			if (isCardPlayable(h.getCard(i))) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Return -1 if both faceUp and faceDown is empty (means player is out)
	 * Return 0 if we have faceUp cards but can't play any of them
	 * Return 1 if we have faceUp cards and they're playable
	 * Return 2 if we have no faceUp cards, and only faceDown cards
	 * Return -2 if we have any other cases (a problem...)
	 */
	public int isShitHandPlayable(ShitHand h) {
		if (h.getFaceUp().isEmpty() && h.getFaceDown().isEmpty()) { // if both faceup and facedown empty, not playable.
			return -1;
		} else {
			if (!h.getFaceUp().isEmpty())
				if (isHandPlayable(h.getFaceUp())) 
					return 1;
				else
					return 0;
			else if (!h.getFaceDown().isEmpty())
				return 2;
		}
		return -2;
		
	}
	
	public static int isMagicCard(Card c) {
		if (c.getValue() == MagicCard.TWO.getValue()) {
			return 2;
		} else if (c.getValue() == MagicCard.SEVEN.getValue()) {
			return 7;
		} else if (c.getValue() == MagicCard.TEN.getValue()){
			return 10;
		} else
			return 0;
	}
	
	public void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			
		}
	}
}
