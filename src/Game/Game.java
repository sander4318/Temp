package Game;

import java.util.ArrayList;

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
	private ArrayList<MobField> mobfields;

	private PlayerField playerfield1;
	private PlayerField playerfield2;
	private PlayerField playerfield3;
	private ArrayList<PlayerField> playerfields;

	private CentralField centralfield1;
	private CentralField centralfield2;
	private CentralField centralfield3;
	private ArrayList<CentralField> centralfields;

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

		// hand and draw- and discardpile
		wr("Initializing game");
		drawpile = new DrawPile();
		wr("Drawpile created and shuffled");
		discardpile = new DiscardPile();
		hand = new Hand();
		drawHand();
		wr("Your hand = " + hand);

		// mobfields
		mobfields = new ArrayList<MobField>();
		mobfield1 = new MobField(1);
		mobfield2 = new MobField(2);
		mobfield3 = new MobField(3);
		mobfields.add(mobfield1);
		mobfields.add(mobfield2);
		mobfields.add(mobfield3);

		drawMobs();
		wr("Mob 1 has: " + mobfield1);
		wr("Mob 2 has: " + mobfield2);
		wr("Mob 3 has: " + mobfield3);

		// playerfields
		playerfields = new ArrayList<PlayerField>();
		playerfield1 = new PlayerField(1);
		playerfield2 = new PlayerField(2);
		playerfield3 = new PlayerField(3);
		playerfields.add(playerfield1);
		playerfields.add(playerfield2);
		playerfields.add(playerfield3);

		// centralfields
		centralfields = new ArrayList<CentralField>();
		centralfield1 = new CentralField(1);
		centralfield2 = new CentralField(2);
		centralfield3 = new CentralField(3);
		centralfields.add(centralfield1);
		centralfields.add(centralfield2);
		centralfields.add(centralfield3);

		// scorefields
		pscorefield = new PscoreField();
		mscorefield = new MscoreField();
		wr("Done initializing");
	}

	/**
	 * gameloop
	 */
	private void start() {
		wr("- - - - - - - - - - Game starting - - - - - - - - - -");
		wr("! The game prints '...' when an automatic event is about to happen. press enter to continue.");
		wr("! The game prints '>>>' when it wants input from you");
		wr("! On either of these prompts:");
		wr("! Type 'help' or 'h' for info about the game phases.");
		wr("! Type 'field' or 'f' to get a preview of the field.");
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

	}

	private void phase2() {
		wr("* * PHASE2");
		Card first = hand.getFirst();
		PlayerField pf = whichPlayerField(first);
		if (pf == null) {
			wr("you'll have to harvest");
			inp("...");
			wr("which of your field would you like to harvest? (1/2/3)");
			while (true) {
				in = inp(">>>");
				if (eq(in, "1")) {
					pf = playerfield1;
					break;
				} else if (eq(in, "2")) {
					pf = playerfield2;
					break;
				} else if (eq(in, "3")) {
					pf = playerfield3;
					break;
				}
			}
			harvest(pf);
			tryToMove(first, hand, pf);
		} else {
			wr("First Hand-Card (" + simpleName(first) + ") to field: "
					+ simpleName(pf));
			inp("...");
			tryToMove(first, hand, pf);
		}

		Card second = hand.getFirst();
		wr("Do you want to plant second Hand-Card (" + simpleName(second)
				+ ")? ([y]es/[n]o)");
		while (true) {
			in = inp(">>>");
			if (eq(in, "yes")) {
				pf = whichPlayerField(second);
				if (pf == null) {
					wr("you'll have to harvest");
					inp("...");
					wr("which of your field would you like to harvest? (1/2/3)");
					while (true) {
						in = inp(">>>");
						if (eq(in, "1")) {
							pf = playerfield1;
							break;
						} else if (eq(in, "2")) {
							pf = playerfield2;
							break;
						} else if (eq(in, "3")) {
							pf = playerfield3;
							break;
						}
					}
					harvest(pf);
					tryToMove(first, hand, pf);
				} else {
					wr("i would say to field: " + simpleName(pf));
					wr("is that ok? ([y]es/[n]o)");
					while (true) {
						in = inp(">>>");
						if (eq(in, "yes")) {
							tryToMove(second, hand, pf);
							break;
						} else if (eq(in, "no")) {
							wr("too bad, only yes allowed :)"); // TODO make no
																// useful
						}
					}
				}
				break;
			} else if (eq(in, "no")) {
				break;
			}
		}
	}

	private void phase3() {
		// wr("* * PHASE3");
	}

	private void phase4() {
		// wr("* * PHASE4");

	}

	private void phase5() {
		wr("* * PHASE5");
		wr("getting two new cards");
		inp("...");
		tryToMove(drawpile.getFirst(), drawpile, hand);
		tryToMove(drawpile.getFirst(), drawpile, hand);

	}

	/**
	 * returns true if card `c` is movable from `from` to `to`
	 * 
	 * @param c
	 *            - card to be moved
	 * @param from
	 *            - cardcollection
	 * @param to
	 *            - cardcollection
	 * @return
	 */
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
	 * one of the fields of 'subject'.. if not: returns first empty field.. if
	 * not returns 0
	 * 
	 * @param field
	 *            - ("player","mobs") subject
	 * @param card
	 * @return - (0) if not on fields and no empty fields - (1,2,3) if card on
	 *         corresponding field of subject or if not: (1,2,3) for first empty
	 *         field
	 */
	private PlayerField whichPlayerField(Card card) {
		for (PlayerField pf : playerfields) {
			if (!pf.cards.isEmpty() && pf.allowedToAdd(card)) {
				// wr(simpleName(pf) + " (same beans)");
				return pf;
			}
		}
		for (PlayerField pf : playerfields) {
			if (pf.allowedToAdd(card)) {
				// wr(simpleName(pf) + " (empty field)");
				return pf;
			}
		}
		return null;
	}

	// ////////////////////////////
	// Below are wrapper methods

	/**
	 * tryToMove-wrapper that determines the score for harvesting and moves
	 * appropriate cards to score- and discard fields
	 * 
	 * @param field
	 *            - PlayerField or MobField instance
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
		Card first = field.getFirst();
		int cardOnField = field.cards.size();
		int cardToScore = ((iRharvestable) field).valueField();
		int cardToDiscard = cardOnField - cardToScore;
		wr(cardOnField + " " + simpleName(first) + "(" + first.one + ","
				+ first.two + "," + first.three + "," + first.four
				+ ") scored for: " + cardToScore + " points.");
		for (int i = 0; i < cardToScore; i++) {
			if (field instanceof PlayerField) {
				tryToMove(field.getFirst(), field, pscorefield);
			} else if (field instanceof MobField) {
				tryToMove(field.getFirst(), field, mscorefield);
			}
		}
		for (int i = 0; i < cardToDiscard; i++) {
			tryToMove(field.getFirst(), field, discardpile);
		}
	}

	/**
	 * test if two strings are 'equal'. returns true if `one` isn't empty string
	 * AND ( `one` equals `two` OR `two` starts with `one`)
	 * 
	 * @param one
	 *            - string, usually the input received
	 * @param two
	 *            - string, usually the string the input has to be tested to
	 * @return
	 */
	private boolean eq(String one, String two) {
		if (!one.equals("")
				&& (one.equalsIgnoreCase(two) || two.startsWith(one))) {
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
		if (out.equals(">>>") || out.equals("...")) {
			String res;
			while (true) {
				res = console.askInput(out);
				if (res.equals("")) {
					if (out.equals("...")) {
						return res;
					}
				} else if (eq(res, "help")) {
					help();
				} else if (eq(res, "field")) {
					previewField();
				} else if (eq(res, "deck")) {
					wr("" + drawpile.cards.size());
					wr("" + drawpile);
				} else {
					return res;
				}
			}
		} else {
			return console.askInput(out);
		}
	}

	/**
	 * draw the phases
	 */
	private void help() {
		wr("____________________________________");
		wr("**************  help  **************");
		wr("1: all mobs get (max 1) card which both you and they have on the field");
		wr("2: you have to place your first handcard to one of your fields, second is optional");
		wr("3: draw 3 cards to central fields, each draw checking: 1) does a mob collect it? y-> give (draw replacement) 2) same card on discard? y-> also place on central field");
		wr("4: plant all cards from central fields to your fields");
		wr("5: draw 2 cards");
		wr("____________________________________");
	}

	/**
	 * draw the field (only mob-, playerfield and hand so far)
	 */
	private void previewField() {
		wr("____________________________________");
		wr("********** field preview ***********");
		wr("mob1: " + mobfield1);
		wr("mob2: " + mobfield2);
		wr("mob3: " + mobfield3);
		wr("____________________________________");
		wr("field1: " + playerfield1);
		wr("field2: " + playerfield2);
		wr("field3: " + playerfield3);
		wr("____________________________________");
		wr("hand: " + hand);
		wr("____________________________________");
	}

	/**
	 * getClassName-wrapper
	 * 
	 * @param obj
	 * @return
	 */
	private String simpleName(Object obj) {
		if (obj instanceof MobField) {
			return "MobField" + ((Field) obj).getID();
		} else if (obj instanceof PlayerField) {
			return "PlayerField" + ((Field) obj).getID();
		} else {
			return obj.getClass().getSimpleName();
		}
	}

}
