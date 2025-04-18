package model;

import java.util.*;

/**
 * The HumanPlayer class represents a human player ,inheriting from the Player
 * class
 */
public class HumanPlayer extends Player {
	/**
	 * The current bet placed by the human player, represented as a list of Chips.
	 */
	private ArrayList<Chips> currentBet;

	/** The remaining chips the human player has */
	private int remainingChips;

	/** Indicates if the current round is a splittable or double round */
	private boolean isSpecialRound;

	/** indicates if the player is doing actions on second hand */
	private boolean isPlayingOnSecondHand;

	/** determines if the player intention is to play split round */
	private boolean hasChosenToSplit;

	/** The second hand of cards used in case of a split. */
	private ArrayList<Card> secondHand = new ArrayList<>();
    /**
     * Constructs a new HumanPlayer with a specified name and an initial chip amount of 1000.
     * @param name the name of the human player
     */
	public HumanPlayer(String name) {
		super(name);
		currentBet = new ArrayList<>();
		this.remainingChips = 1000;
	}
	 /**
     * Returns the list of chips placed as the current bet.
     * @return the current bet
     */
	public ArrayList<Chips> getCurrentBet() {
		return currentBet;
	}
    /**
     * Returns the number of remaining chips the player has.
     * @return the remaining chips
     */
	public int getRemainingChips() {
		return remainingChips;
	}
	
    /**
     * Adds chips to the player's remaining chip count.
     * @param chips the number of chips to add
     */
	public void addChips(int chips) {
		remainingChips += chips;
	}
    /**
     * Removes chips from the player's remaining chip count.
     * @param chips the number of chips to remove
     */
	public void removeChips(int chips) {
		remainingChips -= chips;
	} 
	/**
    * Places a bet with a given chip if the player has enough remaining chips.
    * @param chip the chip to bet
    * @throws IllegalArgumentException if the chip value exceeds remaining chips
    */
	public void placeHumanBet(Chips chip) {
		if (remainingChips - chip.getValue() >= 0) {
			currentBet.add(chip);
			remainingChips -= chip.getValue();
		}
		else {
			throw new IllegalArgumentException("The Bet Exceed the Remaining Chips: "+ remainingChips+ " Available");
		}

	}
	 /**
     * Returns the second hand of cards, used in split rounds.
     * @return the second hand
     */
	public ArrayList<Card> getSecondHand() {
		return secondHand;
	}
	/**
     * Checks if the second hand is busted (i.e., total value > 21).
     * @param hand the hand to evaluate
     * @return true if busted, false otherwise
     */
	public boolean isSplitBusted(ArrayList<Card> hand) {
		return calculateHandValue(hand) > 21;
	}
    /**
     * Adds a card to the second hand.
     * @param card the card to add
     */
	public void receiveSecondHandCard(Card card) {
		secondHand.add(card);
	}
    /**
     * Checks whether the current round qualifies as a special round (split/double).
     * @return true if a special round, false otherwise
     */
	public boolean isSpecialRound() {
		if (getHand().size() == 2 && getSecondHand().isEmpty()) {
			isSpecialRound = (getHand().getFirst().getRank().getValue() == getHand().getLast().getRank().getValue());
		}
		return isSpecialRound;
	}
    /**
     * Checks if the player is currently acting on the second hand.
     * @return true if playing on the second hand
     */
	public boolean isPlayingOnSecondHand() {
		return isPlayingOnSecondHand;
	}
    /**
     * Sets whether the player is currently acting on the second hand.
     * @param isPlayingOnSecondHand true if acting on second hand
     */
	public void setPlayingOnSecondHand(boolean isPlayingOnSecondHand) {
		this.isPlayingOnSecondHand = isPlayingOnSecondHand;
	}
	 /**
     * Returns whether the player has chosen to split their hand.
     * @return true if the player has chosen to split
     */
	public boolean hasChosenToSplit() {
		return hasChosenToSplit;
	}
    /**
     * Sets whether the player has chosen to split their hand.
     * @param hasChosenToSplit true if chosen to split
     */
	public void setHasChosenToSplit(boolean hasChosenToSplit) {
		this.hasChosenToSplit = hasChosenToSplit;
	}
	 /**
     * Sets the player's name.
     * @param name the new name of the player
     */
	public void setName(String name) {
		this.name = name;
	}
    /**
     * Clears the second hand of cards.
     */
	@Override
	public void clearSecondHand() {
		secondHand.clear();
	}
    /**
     * Resets the player’s state for a new game:
     * clears both hands, resets chips to 1000, clears bets and resets split-related flags.
     */
	public void reset() {

		setHasChosenToSplit(false);
		setPlayingOnSecondHand(false);
		secondHand.clear();
		this.getHand().clear();
		this.remainingChips = 1000;
		this.currentBet.clear();
		setHasChosenToSplit(false);
		setPlayingOnSecondHand(false);
	}
	  /**
     * Empty method implementation of the abstract method {@code playTurn} from the {@code Player} superclass.
     * <p>
     * This method is intentionally left blank as a design choice to utilize polymorphism
     * rather than implementing the Strategy pattern.
     *
     * @param deck the deck of cards (unused)
     */
	@Override
	public void playTurn(Deck deck) {
		// Intentionally left blank: defined abstract in superclass
        // using polymorphism instead of a strategy pattern
	}

}
