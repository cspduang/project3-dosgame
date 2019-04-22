import java.util.ArrayList;
import java.util.Arrays;

public class CenterRow {

	private ArrayList<DosCard> cards;
	private ArrayList<DosCard> matchs;
	private ArrayList<ArrayList<DosCard>> matchPairs;


	public CenterRow() {
		this.cards = new ArrayList<DosCard>();
		this.matchs = new ArrayList<DosCard>();
		this.matchPairs = new ArrayList<ArrayList<DosCard>>();
	}

	public ArrayList<DosCard> getCards() {
		return cards;
	}

	public void add(DosCard card) {
		cards.add(card);
	}

	public void addMatch(DosCard card) {
		matchs.add(card);
	}

	public void addMatchpair(ArrayList<DosCard> p) {
		matchPairs.add(p);
	}

	public Integer countBounsPoints() {
		int count = 0;
		for (DosCard m : matchs) {
			for(DosCard card: cards) {
				if(m.getNumber() == card.getNumber() && m.getColor() == card.getColor())
					count++;
			}
		}
		for (ArrayList<DosCard> pair : matchPairs) {
			for(DosCard card: cards) {
				if(pair.get(0).getNumber() + pair.get(1).getNumber() == card.getNumber() &&
					pair.get(0).getColor() == card.getColor() && pair.get(1).getColor() == card.getColor())
					count++;
			}
		}
		return count;
	}

	public void discardto(DiscardPile discardto) {
		ArrayList<DosCard> needRemove = new ArrayList<>();

		for (DosCard m : matchs) {
			for(DosCard card: cards) {
				if(m.getNumber() == card.getNumber()) {
					needRemove.add(card);
					discardto.add(card);
					discardto.add(m);
				}
			}
		}
		for (ArrayList<DosCard> pair : matchPairs) {
			for(DosCard card: cards) {
				if(pair.get(0).getNumber() + pair.get(1).getNumber() == card.getNumber()) {
					needRemove.add(card);
					discardto.add(card);
					discardto.add(pair.get(0));
					discardto.add(pair.get(1));
				}
			}
		}

		cards.removeAll(needRemove);
		matchs.clear();
		matchPairs.clear();
	}

	public Boolean lessThenTwo() {
		return cards.size() < 2;
	}

	public void show() {
		System.out.println("==== center row ====");
		if(cards.size() > 0)
			System.out.println("====" + Arrays.toString(cards.toArray())+"====");
		if(matchs.size() > 0)
			System.out.println("====" + Arrays.toString(matchs.toArray())+"====");
		if(matchPairs.size() > 0)
			System.out.println("====" + Arrays.toString(matchPairs.toArray())+"====");
	}
}
