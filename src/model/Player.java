package model;

import java.util.ArrayList;


/**
 * Represents a player in the card game. This is an abstract class that provides
 * the basic functionalities of a player, such as receiving cards and
 * calculating the hand value.
 */
public abstract class Player {

	protected String name;
	private ArrayList<Card> hand;

	/**
	 * Constructs a player with the specified name and initialize the hand ArrayList
	 *
	 * @param name the name of the player
	 */
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	/**
	 * Adds a card to the player's hand
	 *
	 * @param card the card to be added to the hand
	 */
	public void receiveCard(Card card) {
		hand.add(card);
	}

	/**
	 * Returns the player's hand
	 *
	 * @return a list of cards in the player's hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * Checks if the player has exceeded the value of 21
	 *
	 * @return true if the player's hand value is greater than 21, false otherwise
	 */
	public boolean isBusted() {
		return calculateHandValue(this.hand) > 21;
	}

	/**
	 * Calculates the value of the player's hand
	 *
	 * @param hand the list of cards in the player's hand
	 * @return the total value of the hand
	 */
	// METODO: CALCOLA IL VALORE DELLA MANO DEL GIOCATORE
		public int calculateHandValue(ArrayList<Card> hand) {
			int value = 0;
			int aceCount = 0;

			for (Card card : hand) {
				switch (card.getRank()) {
				case TWO:
					value += 2;
					break;
				case THREE:
					value += 3;
					break;
				case FOUR:
					value += 4;
					break;
				case FIVE:
					value += 5;
					break;
				case SIX:
					value += 6;
					break;
				case SEVEN:
					value += 7;
					break;
				case EIGHT:
					value += 8;
					break;
				case NINE:
					value += 9;
					break;
				case TEN:
					value += 10;
					break;
				case JACK,QUEEN,KING:
					value += 10;
					break;
				case ACE:
					aceCount++;
					value += 11;
					break; // TRATTIAMO INIZIALMENTE L'ASSO COME 11
				}
			}

			// REGOLA L'ASSO A 1 SE IL VALORE TOTALE SUPERA 21
			while (value > 21 && aceCount > 0) {
				value -= 10;
				aceCount--;
			}

			return value;
		}

	 /**
	 * Clears the player's hand of cards.
	 */
	public void clearHand() {
		hand.clear();
	}
	/**
     * Clears the second hand of cards.
     * This method is implemented only in classes that support hand splitting,
     * like HumanPlayer. It exists here to allow polymorphic use and potential
     * extension to automated players with split logic.
     */
	public void clearSecondHand() {
	}
    /**
     * Returns the name of the player.
     * @return the player’s name
     */
	public String getName() {
		return name;
	}
    /**
     * Defines how a player takes their turn.
     * This method must be implemented by concrete subclasses to define specific turn logic
     * (e.g., user input for human players, decision logic for AI).
     * @param deck the current deck of cards
     */
     public abstract void  playTurn(Deck deck) ;
}
