import java.util.ArrayList;

public class DosPlayer extends Player {

	ArrayList<DosCard> hand = new ArrayList<DosCard>();

	public DosPlayer(String name) {
		super(name);
	}

	public void obtain(DosCard card) {
		hand.add(card);
	}

}
