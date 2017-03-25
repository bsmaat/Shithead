import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameController {

	ShitHeadGUI gui;
	TablePanel tablePanel;
	ShitModel shitModel;
	
	Deck deck;
	Pile pile;
	List<ShitHand> hands;
	
	public GameController(ShitHeadGUI view, ShitModel model) {
		this.gui = view;
		this.tablePanel = view.tablePanel; 
		this.shitModel = model;
		
		this.shitModel.addObserver(tablePanel);
		this.deck = shitModel.deck;
		this.hands = shitModel.hands;
		this.pile = shitModel.pile;
	}


	public void startGame() {
		@SuppressWarnings("resource")
		List<Integer> id;


		deck.shuffle();
		if (Game.COMPUTER_ON) {
			hands = deck.dealHands(Game.NUM_OF_PLAYERS+1, Game.NUM_OF_CARDS);
			Game.COMPUTER_INDEX = hands.size()-1;
		} else {
			hands = deck.dealHands(Game.NUM_OF_PLAYERS, Game.NUM_OF_CARDS);
		}
		
		for (int i = 0; i < hands.size(); i++)
			hands.get(i).setPile(pile);

		for (int i = 0; i < hands.size(); i++) {
			shitModel.hands.add(hands.get(i));
		}

		tablePanel.addHandPanels(hands, pile);
		tablePanel.init();
		//begin pile
		//Card c = new Card(deck.peakCardFromTop());
		pile.addCard(new Card(deck.removeCardFromTop()));
		
		// cards have been dealt so update changes
		shitModel.updateChanges();

		while(true) {
			//gui.display("Deck size: " + deck.size());
			Game.display("Pile: ");
			pile.printCards();
			
			
			for (int i = 0; i < Game.NUM_OF_PLAYERS; i++) {
				Game.display("Player " + i + " cards: ");
				hands.get(i).faceUp.sortByValue(true); // sort by ascending value
				hands.get(i).faceUp.printCards();
			}
			
			
			if(Game.COMPUTER_ON) {
				Game.display("Computer cards: ");
				hands.get(Game.COMPUTER_INDEX).getFaceUp().sortByValue(true); // sort by ascending value
				hands.get(Game.COMPUTER_INDEX).getFaceUp().printCards();
			}
			

			
			//players play
			for (int i = 0; i < Game.NUM_OF_PLAYERS; i++) {
				
				// if the hand is playable, allow player to select next card
				// first check if its a hand with faceUp cards that we can play
				int isShitHandPlayable = isShitHandPlayable(hands.get(i));
				if (isShitHandPlayable == 1) {
					boolean playing = true;
					while (playing) {
						//shitModel.updateChanges();
						System.out.print("Player " + i + ", drop a card: ");
						
						//get which card is being clicked on
						id = tablePanel.getGUIInput();
						
						
						Game.debug("Call tryToPlayCard()");
						Card c = hands.get(i).getFaceUp().getCard(id.get(0));
						int output = tryToPlayCard(hands.get(i), id);
						if (output == 0) 
						{
							gui.display("Player " + i + " can't play " + c + ". Choose another card.");
							playing = true;
						}
						else if (output == 1)
						{
							gui.display("Player " + i + " played " + c);
							playing = false;
						}
						else if (output == 2)
						{
							gui.display("Player " + i + " played " + c + ". Player " + i + " play again" );
							playing = true;
						}
						shitModel.updateChanges();

					}
				} else if (isShitHandPlayable == 2) { // if we have only faceDown cards, should be able to just pick one of them
					Game.display("On to face down cards");
					boolean playing = true;
					while (playing) {
						//gui.displayCards(hands,  pile);
						shitModel.updateChanges();

						Game.display("Player " + i + ", pick a face down card");
						id = tablePanel.getGUIInput();
						Card c = hands.get(i).getFaceDown().getCard(id.get(0));
						hands.get(i).getFaceUp().addCard(c);
						hands.get(i).getFaceDown().removeCard(c);

						int output = tryToPlayCard(hands.get(i), id);
						if (output == 0) {
							// card can't be played, will pick up card
							gui.display("Player " + i + " can't play " + c + ". Choose another card.");
							playing = false;
						} 
						else if (output == 1) {
							// card was played
							gui.display("Player " + i + " played " + c);
							playing = false;
						}
						else if (output == 2) {
							//card was played, and it's still our go
							gui.display("Player " + i + " played " + c + ". Player " + i + " play again" );
							playing = true;
						}
					}
				}
				else if (isShitHandPlayable == 0) {
					gui.display("Not playable, player " + i + " picks up");
					hands.get(i).getFaceUp().pickUpPile();
				} else {
					Game.display("Error: we shouldn't get here!");
				}
			}
			
			Game.display("DECK SIZE: " + deck.size());
			
			// player has played or picked up, so update changes
			shitModel.updateChanges();

			wait(1000);
			
			//computer plays
			if (Game.COMPUTER_ON) { 
				Game.display("Computer player");
				computerPlayingLogic();
			}
			Game.display("DECK SIZE: " + deck.size());
			// computer has done playing so update view
			shitModel.updateChanges();
			Game.display("-------------------------------------------------------------------");
			
						
		}
		
		//scanner.close();
		
	}
	
	// this method handles the computer playing logic and how it selects its card to play
	public void computerPlayingLogic() {
		if (isHandPlayable(hands.get(Game.COMPUTER_INDEX).getFaceUp())) {
			boolean playing = true;
			while(playing) {
				Card c;
				if (!pile.isEmpty()) {
					// if 7, go lower
					if (Game.isMagicCard(pile.peakCardFromTop()) == 7) {
						c = hands.get(Game.COMPUTER_INDEX).getFaceUp().getLowestCard();
					} else {
						//c = hands.get(Game.COMPUTER_INDEX).getFaceUp().getSmallestCardGreaterThanOrEqualTo(pile.peakCardFromTop());
						c = hands.get(Game.COMPUTER_INDEX).getFaceUp().getLowestCardGreaterThanOrEqualTo(pile.peakCardFromTop());
					}
					if (c != null) {
						hands.get(Game.COMPUTER_INDEX).getFaceUp().dropCard(c);
						//Card pickUpCard = new Card(deck.removeCardFromTop());
						if (Game.isMagicCard(c) == 10) {
							gui.display("Computer cleared the pile with " + c);
							pile.clear();
							continue;
						}
						pickUpCard(hands.get(Game.COMPUTER_INDEX).getFaceUp());

					} else {
						gui.display("Computer does not have a higher card. Picking up...");
						hands.get(Game.COMPUTER_INDEX).getFaceUp().pickUpPile();
					}
				} else {
					// if it's empty just drop the lowest card
					c = hands.get(Game.COMPUTER_INDEX).getFaceUp().getLowestCard();
					hands.get(Game.COMPUTER_INDEX).getFaceUp().dropCard(c);
					pickUpCard(hands.get(Game.COMPUTER_INDEX).getFaceUp());
				}
				gui.display("Computer played " + c);
				playing = false;
			}
		} else {
			gui.display("Computer can't playing, picking up");
			hands.get(Game.COMPUTER_INDEX).getFaceUp().pickUpPile();
		}
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
			if (Game.isMagicCard(cardToPlay) == 10) {
				pile.clear();
				return 2;
			}
			//playing = false;
			pickUpCard(h.getFaceUp());
			return 1; //successfully played card
		} else {
			Game.display(h.getFaceUp().getCard(id) + " is not playable!");
			return 0;
		}
	}

	public int tryToPlayCard(ShitHand h, List<Integer> id) {
		Collections.sort(id);
		Card cardToPlay = new Card();
		
		// if the faceUp cards are empty, then we probably clicked on a face down card
		if (h.getFaceUp().isEmpty()) {
			//pick up a facedown card and move it to the faceup cards
			for (int i = id.size()-1; i >= 0; i--) 
				h.getFaceUp().addCard(h.getFaceDown().getCard(id.get(i)));
			for (int i = id.size()-1; i >= 0; i--) 
				h.getFaceDown().removeCardById(id.get(i));
			return 3;
		} 
		else 
		{
			Game.debug("tryToPlayCard()");
			for (int i = id.size()-1; i >= 0; i--) 
				cardToPlay = h.getFaceUp().getCard(id.get(i));
		
			if (isCardPlayable(cardToPlay)) {
				//gui.display("Played " + cardToPlay);
				for (int i = id.size()-1; i >= 0; i--) 
					h.getFaceUp().dropCard(id.get(i)); // drop card (adds to pile automatically)							
				if (Game.isMagicCard(cardToPlay) == 10) {
					pile.clear();
					return 2;
				}
				//playing = false;
				pickUpCard(h.getFaceUp());
				return 1; //successfully played card
			} else {
				gui.display(h.getFaceUp().getCard(id.get(0)) + " is not playable!");
				return 0;
			}
		}
	}
	
	
	public void pickUpCard(Hand h) {
		while (h.size() < Game.NUM_OF_CARDS) {
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
		Game.display("GAME OVER!");
	}
	
	public boolean isCardPlayable(Card c) {
		if (pile.isEmpty()) {
			return true;
		} else {
			if (Game.isMagicCard(c) == 2 || Game.isMagicCard(c) == 10) { 
				// magic card 2 and 10 are always playable
				return true;
			}
			else if (Game.isMagicCard(pile.peakCardFromTop()) == 7) {
				Game.display("Magic card! Must go lower");
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
	

	public void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			
		}
	}

}
