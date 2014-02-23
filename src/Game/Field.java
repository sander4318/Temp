package Game;

public abstract class Field extends CardCollection {
	private int id;

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	@Override
	public boolean allowedToAdd(Object obj) {
		if (cards.isEmpty()) {
			//TODO also check if not on other field of same class
			return true;
		} else if (obj.getClass() == getFirst().getClass()) {
			return true;
		} else {
			return false;
		}
	}

}
