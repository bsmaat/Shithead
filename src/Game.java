import java.util.List;
import java.util.Scanner;

public class Game {

	
	static final int NUM_OF_CARDS = 3;
	static final int NUM_OF_PLAYERS = 1;
	static int COMPUTER_INDEX = 0;
	static final boolean COMPUTER_ON = true;
	
	ShitHeadGUI gui;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.startGame();
	}
	
	
	ShitModel shitModel;
	GameController gameController;
	
	public Game() {
		gui = new ShitHeadGUI();
		shitModel = new ShitModel();
		gameController = new GameController(gui, shitModel);
	}
	
	public void startGame() {
		gameController.startGame();
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
	
	public static void debug(String s) {
		System.out.println("DEBUG: " + s);
	}
	
	// some game rules
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
	
}
