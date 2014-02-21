package Game;

import java.util.ArrayList;

public class Hand extends CardCollection {
	public Hand(){
		cards = new ArrayList<Card>();
	}

	@Override
	public boolean allowedToAdd(Object obj) {
		return true;
	}
}
