package Game;

import java.util.ArrayList;
import java.util.Collections;

public class DrawPile extends CardCollection {

	DrawPile() {
		cards = new ArrayList<Card>();
		createDeck();
	}

	private void createDeck() {
		for (int i = 0; i < 20; i++) {
			cards.add(new BlauweBoon());
		}
		for (int i = 0; i < 19; i++) {
			cards.add(new KidneyBoon());
		}
		for (int i = 0; i < 18; i++) {
			cards.add(new VuurBoon());
		}
		for (int i = 0; i < 16; i++) {
			cards.add(new StootBoon());
		}
		for (int i = 0; i < 16; i++) {
			cards.add(new BruineBoon());
		}
		for (int i = 0; i < 14; i++) {
			cards.add(new SperzieBoon());
		}
		for (int i = 0; i < 13; i++) {
			cards.add(new BlufBoon());
		}
		Collections.shuffle(cards);

	}

	@Override
	public boolean allowedToAdd(Object obj) {
		return false;
	}
}
