package model;

/**
 * The Dealer class represents the dealer in a card game, inheriting from the
 * Player class The dealer has specific rules for drawing cards, typically
 * stopping when the hand value is 17 or more
 */
public class Dealer extends Player {

	/** Constant representing the dealer's name. */
	public final static String DEALER = "DEALER";

	/**
	 * Constructs a Dealer with a predefined name.
	 */
	public Dealer() {
		super(DEALER);
	}

	/**
	 * The dealer plays their turn by drawing cards from the deck until the value of
	 * their hand is at least 17
	 *
	 * @param deck the deck of cards from which the dealer draws
	 */
	@Override
	public void playTurn(Deck deck) {
		while (calculateHandValue(getHand()) < 17) {
			super.receiveCard(deck.drawCard());
			//ho scritto un metodo play turn sia nella classe bot sia nella classe dealer , perche in futuro potrebbero avere comportamenti diversi 
		}
	}

}
