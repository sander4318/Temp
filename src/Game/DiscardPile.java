package Game;

import java.util.ArrayList;

public class DiscardPile extends CardCollection {

	public DiscardPile() {
		cards = new ArrayList<Card>();
	}

	@Override
	public boolean allowedToAdd(Object obj) {
		return true;
	}
}
