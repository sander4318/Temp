public class Field extends Stack {
	private boolean allowedToHarvest;

	/**
	 * Call Stack constructor and make the stack addable
	 */
	public Field(boolean allowedToHarvest) {
		super(true);
		this.allowedToHarvest = allowedToHarvest;
	}

	/**
	 * Returns true if the stack is empty or the card to be added is the same as
	 * the top card
	 */
	public boolean addAllowed(Card c) {
		if (allowedToAdd) {
			if (cards.isEmpty()) {
				return true;
			} else if (c.name() == cards.get(0).name()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * empties the stack and returns the points scored
	 */
	public int harvest() throws HarvestException {
		if (allowedToHarvest) {
			int amount = size();
			int value = Card.value(cards.get(0).name(), amount);
			return value;
		} else {
			throw new HarvestException("Not allowed to harvest");
		}

	}
}
