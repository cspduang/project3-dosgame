import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DosHand extends DosDeck {
	public DosHand() {
		this.stack = new ArrayList<DosCard>();

		for (DosCard.Color color : Arrays.asList(DosCard.Color.BLUE, DosCard.Color.GREEN, DosCard.Color.RED, DosCard.Color.YELLOW)) {
			for (int i = 0; i < 3; i++) {
				addCard(new DosCard(color, DosCard.Number.ONE));
				addCard(new DosCard(color, DosCard.Number.THREE));
				addCard(new DosCard(color, DosCard.Number.FOUR));
				addCard(new DosCard(color, DosCard.Number.FIVE));
			}

			for (int i = 0; i < 2; i++) {
				addCard(new DosCard(color, DosCard.Number.SIX));
				addCard(new DosCard(color, DosCard.Number.SEVEN));
				addCard(new DosCard(color, DosCard.Number.EIGHT));
				addCard(new DosCard(color, DosCard.Number.NINE));
				addCard(new DosCard(color, DosCard.Number.TEN));
			}

			addCard(new DosCard(color, DosCard.Number.WILD));
			addCard(new DosCard(color, DosCard.Number.WILD));
		}

		for (int i = 0; i < 12; i++) {
			addCard(new DosCard(DosCard.Color.WILD));
		}

		this.shuffle();
	}

	public void print() {
		System.out.println("All Cards: ");
		System.out.println(Arrays.toString(this.stack.toArray()));
	}

	public DosCard deal() throws Exception {
		if (stack.size() > 0) {
			return stack.remove(0);
		} else {
			throw new Exception("NO Any More Card");
		}
	}

	public List<DosCard> getSingleNumberMatches (DosCard c) {

		List<DosCard> ans = new ArrayList<DosCard>();

		for(DosCard card: stack) {
			if(card.getNumber() == c.getNumber() && !ans.contains(card))
				ans.add(card);
		}
		return ans;
	}

	public List<ArrayList<DosCard>> getDoubleNumberMatches (DosCard c) {
		List<ArrayList<DosCard>> ans = new ArrayList<ArrayList<DosCard>>();

		for (int i = 0; i < stack.size(); i++) {
			DosCard card = stack.get(i);
			if(card.getNumber() < c.getNumber() && card.getNumber() != -1) {
				for (int j = i + 1; j < stack.size(); j++) {
					DosCard another = stack.get(j);
					if(another.getNumber() == c.getNumber() - card.getNumber()) {
						ArrayList<DosCard> twoCard = new ArrayList<DosCard>();
						twoCard.add(card);
						twoCard.add(another);
						if(!ans.contains(twoCard))
							ans.add(twoCard);
					}
				}
			}
		}

		// Wild Number
		for (int i = 0; i < stack.size(); i++) {
			DosCard card = stack.get(i);
			if(card.getNumber() == -1) {
				for (int j = i + 1; j < stack.size(); j++) {
					DosCard another = stack.get(j);
					if(another.getNumber() < c.getNumber()) {
						ArrayList<DosCard> twoCard = new ArrayList<DosCard>();
						twoCard.add(card);
						twoCard.add(another);
						if(!ans.contains(twoCard))
							ans.add(twoCard);
					}
				}
			}
		}

		return ans;
	}

}
