import java.util.ArrayList;
import java.util.List;

public class ShitModel extends java.util.Observable {

	Deck deck = new Deck();
	Pile pile = new Pile();
	
	List<ShitHand> hands;// = new ArrayList<ShitHand>();

	public ShitModel() {
		hands = new ArrayList<ShitHand>();
	}
	
	public void updateChanges() {
		Game.debug("updateChanges()");
		setChanged();
		notifyObservers(this);
	}
	
}
