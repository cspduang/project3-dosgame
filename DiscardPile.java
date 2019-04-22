import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
	private ArrayList<DosCard> cards = new ArrayList<DosCard>();

	public DiscardPile() {
	}

	public void add(DosCard card) {
		cards.add(card);
	}

	public void add(List<DosCard> cs) {
		cs.forEach(card -> this.cards.add(card));
	}


}
