/**
 * Old class, not used in app just for testing purposes
 * @author Sander
 *
 */
public class Card {
	private String name;
	private static final int[] blauw = { 4, 6, 8, 10 };
	private static final int[] kidney = { 5, 6, 7, 8 };
	private static final int[] vuur = { 3, 6, 8, 9 };
	private static final int[] stoot = { 4, 5, 6, 7 };
	private static final int[] bruin = { 3, 5, 7, 8 };
	private static final int[] sperzie = { 3, 5, 6, 7 };
	private static final int[] bluf = { 3, 4, 5, 6 };

	public Card(String name) {
		this.name = name;
	}
	
	public String name(){return this.name;}
	
	public String toString() {
		return (this.name);
	}

	public static int value(String name, int amount) {
		int[] values;
		switch (name) {
		case "Blauweboon":
			values = blauw;
			break;
		case "Kidneyboon":
			values = kidney;
			break;
		case "Vuurboon":
			values = vuur;
			break;
		case "Stootboon":
			values = stoot;
			break;
		case "Bruineboon":
			values = bruin;
			break;
		case "Sperzieboon":
			values = sperzie;
			break;
		case "Blufboon":
			values = bluf;
			break;
		default:
			values = new int[]{0,0,0,0};
			break;
		}
		if (amount >= values[0] && amount <values[1]) {
			return 1;
		} else if (amount >= values[1] &&amount<values[2]) {
			return 2;
		} else if (amount >= values[2]&&amount<values[3]) {
			return 3;
		} else if (amount >= values[3]) {
			return 4;
		} else {
			return 0;
		}
	}
}