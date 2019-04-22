import java.util.Objects;

public class DosCard{

	public static enum Color {
		RED, YELLOW, GREEN, BLUE, WILD
	}
	public static enum Number {
		ONE, TWO, THREE, FOUR, FIVE,
		SIX, SEVEN, EIGHT, NINE, TEN,
		WILD
	}

	private Color color;
	private Number number;

	public DosCard(Color color) {
		this.color = color;
		this.number = Number.TWO;
	}

	public DosCard(Color color, Number number) {
		this.color = color;
		this.number = number;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getNumber() {
		switch (number) {
			case ONE: return 1;
			case THREE: return 3;
			case FOUR: return 4;
			case FIVE: return 5;
			case SIX: return 6;
			case SEVEN: return 7;
			case EIGHT: return 8;
			case NINE: return 9;
			case TEN: return 10;
			case WILD: return -1;
			case TWO: return 2;
		}
		return -1;
	}

	public void setNumber(Number number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DosCard dosCard = (DosCard) o;

		if (color != dosCard.color) return false;
		return number == dosCard.number;
	}

	@Override
	public int hashCode() {
		int result = color != null ? color.hashCode() : 0;
		result = 31 * result + (number != null ? number.hashCode() : 0);
		return result;
	}

	public String toString() {
		return " {" + color + "-" + number + "}";
	}
}