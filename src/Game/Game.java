package Game;

import UI.iLogging;

public class Game {
	private iLogging console;
	private boolean running;
	private String in;

	private DrawPile drawpile;
	private DiscardPile discardpile;
	private Hand hand;

	private MobField mobfield1;
	private MobField mobfield2;
	private MobField mobfield3;

	private PlayerField playerfield1;
	private PlayerField playerfield2;
	private PlayerField playerfield3;

	private CentralField centralfield1;
	private CentralField centralfield2;
	private CentralField centralfield3;

	private PscoreField pscorefield;
	private MscoreField mscorefield;

	public Game(iLogging console) {
		this.console = console;
		init();
		start();

	}

	/**
	 * Instantiate everything
	 */
	private void init() {
		wr("Initializing game");
		drawpile = new DrawPile();
		wr("Drawpile created and shuffled");
		discardpile = new DiscardPile();
		hand = new Hand();
		drawHand();
		wr("Your hand = " + hand);
		mobfield1 = new MobField(1);
		mobfield2 = new MobField(2);
		mobfield3 = new MobField(3);
		drawMobs();
		wr("Mob 1 has: " + mobfield1);
		wr("Mob 2 has: " + mobfield2);
		wr("Mob 3 has: " + mobfield3);
		playerfield1 = new PlayerField(1);
		playerfield2 = new PlayerField(2);
		playerfield3 = new PlayerField(3);
		centralfield1 = new CentralField(1);
		centralfield2 = new CentralField(2);
		centralfield3 = new CentralField(3);
		pscorefield = new PscoreField();
		mscorefield = new MscoreField();
		wr("Done initializing");
	}

	/**
	 * gameloop
	 */
	private void start() {
		wr("- - - - - - - - - - Game starting - - - - - - - - - -");
		wr("type 'help' or 'h' for info about the phase you are currently in.");
		wr("type 'next' or 'n' to go to next phase (for testing only)");
		running = true;
		int turn = 1;
		while (running) {
			wr("* * * * * * * * TURN " + turn);
			phase1();
			phase2();
			phase3();
			phase4();
			phase5();
			if (turn == 7) {
				running = false;
				wr("- - - - - - - - - - Game end - - - - - - - - - -");
				// TODO make a proper game end
			}
			turn++;
		}
	}

	private void phase1() {
		wr("* * PHASE1");
		// wr("not really doing anythign here yet");
		// while (true) {
		// // TODO make this phase usefull
		// in = inp(">>>");
		// if (eq(in, "help")) {
		// // help(1);
		// break;
		// } else if (eq(in, "next")) {
		// break;
		// } else {
		// break;
		// }
		// }

	}

	private void phase2() {
		wr("* * PHASE2");
		Card first = hand.getFirst();
		inp("First Hand-Card (" + first + ") to field:");
		int result = whichField("player", first);
		wr("" + result);
		if (result == 0) {
			inp("you'll have to harvest");
		} else if (result == 1) {
			tryToMove(first, hand, playerfield1);
		} else if (result == 2) {
			tryToMove(first, hand, playerfield2);
		} else if (result == 3) {
			tryToMove(first, hand, playerfield3);
		}
	}

	private void phase3() {
		// wr("* * PHASE3");
	}

	private void phase4() {
		// wr("* * PHASE4");

	}

	private void phase5() {
		// wr("* * PHASE5");

	}

	private boolean tryToMove(Card c, CardCollection from, CardCollection to) {
		// wr("Trying to move " + simpleName(c) + " from " + simpleName(from) +
		// " to " + simpleName(to)+" .... ",false);
		if (to.allowedToAdd(c)) {
			if (from.remove(c)) {
				// wr("yes");
				return to.add(c);
			} else {
				// wr("no");
				return false;
			}
		} else {
			// wr("no");
			return false;
		}

	}

	/**
	 * CardCollection.allowedToAdd-wrapper for testing if the card is already on
	 * one of the fields of 'subject'.. if not: returns first empty field.. if not returns 0
	 * 
	 * @param field
	 *            - ("player","mobs") subject
	 * @param card
	 * @return - (0) if not on fields - (1,2,3) if on corresponding field of
	 *         subject or if not: (1,2,3) for first empty field
	 */
	private int whichField(String subject, Card card) {
		if (!subject.equals("player") && !subject.equals("mob")) {
			wr("wrong argument passed to alreadyOnField");
			return 0;
		} else {
			boolean f1empty;
			boolean f2empty;
			boolean f3empty;
			boolean f1allow;
			boolean f2allow;
			boolean f3allow;
			switch (subject) {
			case "player":
				if (playerfield1.cards.isEmpty()) {
					f1empty = true;
				} else {
					f1empty = false;
				}
				if (playerfield2.cards.isEmpty()) {
					f2empty = true;
				} else {
					f2empty = false;
				}
				if (playerfield3.cards.isEmpty()) {
					f3empty = true;
				} else {
					f3empty = false;
				}
				if (playerfield1.allowedToAdd(card)) {
					f1allow = true;
				} else {
					f1allow = false;
				}
				if (playerfield2.allowedToAdd(card)) {
					f2allow = true;
				} else {
					f2allow = false;
				}
				if (playerfield3.allowedToAdd(card)) {
					f3allow = true;
				} else {
					f3allow = false;
				}
				break;
			case "mob":
				if (mobfield1.cards.isEmpty()) {
					f1empty = true;
				} else {
					f1empty = false;
				}
				if (mobfield2.cards.isEmpty()) {
					f2empty = true;
				} else {
					f2empty = false;
				}
				if (mobfield3.cards.isEmpty()) {
					f3empty = true;
				} else {
					f3empty = false;
				}
				if (mobfield1.allowedToAdd(card)) {
					f1allow = true;
				} else {
					f1allow = false;
				}
				if (mobfield2.allowedToAdd(card)) {
					f2allow = true;
				} else {
					f2allow = false;
				}
				if (mobfield3.allowedToAdd(card)) {
					f3allow = true;
				} else {
					f3allow = false;
				}
				break;
			default:
				wr("case default");
				f1empty = false;
				f2empty = false;
				f3empty = false;
				f1allow = false;
				f2allow = false;
				f3allow = false;
			}
			if (f1allow && !f1empty) {
				return 1;
			} else if (f2allow && !f2empty) {
				return 2;
			} else if (f3allow && !f3empty) {
				return 3;
			} else if (f1allow && f1empty) {
				return 1;
			} else if (f2allow && f2empty) {
				return 2;
			} else if (f3allow && f3empty) {
				return 3;
			} else {
				return 0;
			}
		}
	}

	//////////////////////////////
	// Below are wrapper methods

	
	/**
	 * tryToMove-wrapper that determines the score for harvesting and moves
	 * appropriate cards to score- and discard fields
	 * 
	 * @param field
	 */
	private void harvest(Field field) {
		if (field instanceof PlayerField) {
			field = (PlayerField) field;
		} else if (field instanceof MobField) {
			field = (MobField) field;
		} else {
			wr("ERROR: field to harvest is neither player or mobfield ................");
			wr("THIS ERROR SHOULD NEVER BE SHOWN!");
			return;
		}
		int cardOnField = field.cards.size();
		int cardToScore = ((iRharvestable) field).valueField();
		int cardToDiscard = cardOnField - cardToScore;
		for (int i = 0; i < cardToScore; i++) {
			wr("cardtoscore");
			if (field instanceof PlayerField) {
				tryToMove(field.getFirst(), field, pscorefield);
			} else if (field instanceof MobField) {
				tryToMove(field.getFirst(), field, mscorefield);
			}
		}
		for (int i = 0; i < cardToDiscard; i++) {
			wr("cardtodiscard");
			tryToMove(field.getFirst(), field, discardpile);
		}
	}

	/**
	 * wr-wrapper that prints the help string for the current game-phase
	 */
	private void help(int phase) {
		wr("Hulp:");
		switch (phase) {
		case 1:
			wr("i dont wanna help in phase1");
			break;
		case 2:
			wr("i dont wanna help in phase2");
			break;
		case 3:
			wr("i dont wanna help in phase3");
			break;
		case 4:
			wr("i dont wanna help in phase4");
			break;
		case 5:
			wr("i dont wanna help in phase5");
			break;
		}
	}

	private boolean eq(String one, String two) {
		if (one.equalsIgnoreCase(two) || two.startsWith(one)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * tryToMove-wrapper that moves 7 cards from drawpile to hand (starting
	 * hand)
	 */
	private void drawHand() {
		for (int i = 0; i < 7; i++) {
			tryToMove(drawpile.getFirst(), drawpile, hand);
		}
	}

	/**
	 * tryToMove-wrapper gives the mobs starting cards
	 */
	private void drawMobs() {
		while (true) {
			// wr("Next card = "+drawpile.getFirst());
			if (tryToMove(drawpile.getFirst(), drawpile, mobfield3)) {
				// wr("mob1 got card");
			} else if (tryToMove(drawpile.getFirst(), drawpile, mobfield2)) {
				// wr("mob2 got card");
			} else {
				tryToMove(drawpile.getFirst(), drawpile, mobfield1);
				break;
			}
		}
	}

	/**
	 * console.write-wrapper
	 * 
	 * @param str
	 */
	private void wr(String str) {
		console.write(str);
	}

	/**
	 * console.write-wrapper
	 * 
	 * @param str
	 * @param oneline
	 *            - give false to make it print() instead of println
	 */
	private void wr(String str, boolean oneline) {
		console.write(str, oneline);
	}

	/**
	 * console.askInput-wrapper
	 * 
	 * @param out
	 * @return
	 */
	private String inp(String out) {
		return console.askInput(out);
	}

	/**
	 * getClassName-wrapper
	 * 
	 * @param obj
	 * @return
	 */
	private String simpleName(Object obj) {
		if (obj instanceof Field) {
			return "MobField" + ((Field) obj).getID();
		} else {
			return obj.getClass().getSimpleName();
		}
	}

}
