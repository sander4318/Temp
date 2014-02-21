package Game;

import java.util.ArrayList;

public abstract class CardCollection {
	protected ArrayList<Card> cards;

	abstract public boolean allowedToAdd(Object obj);

	public Card getFirst() {
		return cards.get(0);
	}

	public int indexOf(Object obj) {
		return cards.indexOf(obj);
	}

	public boolean add(Object obj) {
		if (obj instanceof Card) {
			Card c = (Card) obj;
			return cards.add(c);
		} else {
			return false;
		}

	}

	public boolean remove(Object obj) {
		return cards.remove(obj);
	}

	public String toString() {
		String temp = "";
		for (Card c : cards) {
			temp += c.getClass().getSimpleName() + " ";
		}
		return temp;
	}

}
