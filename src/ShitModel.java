import java.util.ArrayList;
import java.util.List;


public class ShitModel {

	private Deck deck = new Deck();
	private Pile pile = new Pile();
	
	List<ShitHand> hands;// = new ArrayList<ShitHand>();

	public ShitModel() {
		hands = new ArrayList<ShitHand>();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public Pile getPile() {
		return pile;
	}
	
	
	
}
