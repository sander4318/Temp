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
	
	@Override
	public boolean add(Object obj) {
		if (obj instanceof Card) {
			Card c = (Card) obj;
			cards.add(0,c);
			return true;
		} else {
			return false;
		}

	}
}
