import java.util.ArrayList;

public class Stack {
	protected boolean allowedToAdd;
	protected ArrayList<Card> cards;

	/**
	 * Initiate the cards array and set the allowedToAdd param.
	 * 
	 * @param allowedToAdd
	 *            give false to prevent the stack from being addable (e.g. for
	 *            the drawpile)
	 */
	public Stack(boolean allowedToAdd) {
		this.allowedToAdd = allowedToAdd;
		cards = new ArrayList<Card>();
	}

	/**
	 * test if card is allowed to be added
	 * 
	 * @param c
	 *            - card to test
	 * @return boolean
	 */
	private boolean addAllowed(Card c) {
		if (allowedToAdd) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * add card to stack
	 * 
	 * @param c
	 *            - card to add to the stack
	 */
	public void add(Card c) throws AddCardException {
		if(addAllowed(c)){
			cards.add(c);
		} else{
			throw new AddCardException("Not allowed to add card!");
		}
	}

	/**
	 * returns the number of cards in this stack
	 */
	public int size() {
		return cards.size();
	}

}
