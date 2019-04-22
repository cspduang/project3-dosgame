import java.util.ArrayList;
import java.util.Collections;

public class DosDeck {
	ArrayList<DosCard> stack;

	public DosDeck() { }

	public void addCard(DosCard c)
	{
		stack.add(c);
	}

	public void shuffle()
	{
		Collections.shuffle(stack);
	}

}
