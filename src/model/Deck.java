package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Deck class represents a collection of playing cards, which can be
 * shuffled and used to draw cards. It supports initialization with multiple
 * decks.
 */
public class Deck {
	private List<Card> cards;
	private int numberOfDecks;

	/**
	 * Constructor: Initializes the deck with the specified number of decks.
	 *
	 * @param numberOfDecks the number of decks to be included in the deck
	 */
	public Deck(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
		constructorSupporter();
	}

	/**
	 * Private method to initialize and shuffle a deck array.
	 */
	private void constructorSupporter() {
		cards = new ArrayList<>();
		for (int i = 0; i < numberOfDecks; i++) {
			cards.addAll(getDeckStream());
		}
		shuffle();
	}

	/**
	 * Private method to create a single deck using streams.
	 *
	 * @return a list of cards representing a single deck
	 */
	private List<Card> getDeckStream() {
		return Stream.of(Card.Suit.values())
				.flatMap(suit -> Stream.of(Card.Rank.values()).map(rank -> new Card(suit, rank)))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Private method to Shuffles the cards in the deck.
	 */
	private void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Draws a card from the deck.
	 *
	 * @return the drawn card
	 * @throws IllegalStateException if the deck is empty
	 */
	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("The deck is empty.");
		}
		return cards.removeLast();
	}

	/**
	 * Returns the number of remaining cards in the deck.
	 *
	 * @return the integer number of remaining cards
	 */
	public int getRemainingCards() {
		return cards.size();
	}

	/**
	 * Returns a string representation of the deck, listing all cards.
	 *
	 * @return a string representation of the deck
	 */
	@Override
	public String toString() {
		return cards.toString();
	}

	/**
	 * Method to restart the deck from begin,"to be used at the end of every round"
	 */
	public void restartDeck() {
		this.cards.clear();
		constructorSupporter();
	}
	   /**
     * Returns the number of decks used.
     * @return the number of decks
     */
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	  /**
     * Sets the number of decks to be used
     * @param numberOfDecks the new number of decks
     */
	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}


}
