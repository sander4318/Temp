package Game;

import java.util.ArrayList;

public class MobField extends Field implements iRharvestable {
	public MobField(int id) {
		cards = new ArrayList<Card>();
		setID(id);
	}

	@Override
	public int valueField() {
		String fname = this.getClass().getSimpleName();
		int size = cards.size();
		if (size != 0) {
			Card c = cards.get(0);
			String cname = c.getClass().getSimpleName();
			int score;
			if (size >= c.one && size < c.two) {
				// System.out.println(fname+" with ("+size+"x) "+cname+" is worth: one thaler");
				score = 1;
			} else if (size >= c.two && size < c.three) {
				// System.out.println(fname+" with ("+size+"x) "+cname+" is worth: two thaler");
				score = 2;
			} else if (size >= c.three && size < c.four) {
				// System.out.println(fname+" with ("+size+"x) "+cname+" is worth: three thaler");
				score = 3;
			} else if (size >= c.four) {
				// System.out.println(fname+" with ("+size+"x) "+cname+" is worth: four thaler");
				score = 4;
			} else {
				// System.out.println(fname+" with ("+size+"x) "+cname+" is worth: zero thalers");
				score = 0;
			}
			return score;
		} else {
			return 0;
		}
	}

}
