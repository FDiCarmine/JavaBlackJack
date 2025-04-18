package model;

import java.util.*;
/**
 * The Card class represents a single playing card with a specific suit and
 * rank, it contains enumerations for Suit and Rank, and each Rank has an
 * integer representing the associated value.
 */
public class Card {
	/**
	 * Card fields relative to inner nested enumerations, each field representing
	 * respective suit and value of a single card.
	 */
	private  Suit suit;
	private  Rank rank;

	/**
	 * Enumeration representing the four suits of a deck of cards.
	 */
	 enum Suit {
		HEARTS, DIAMONDS, CLUBS, SPADES;
	}

	/**
	 * Enumeration representing the ranks of a deck of cards, each with an
	 * associated value relative to BlackJack game.
	 */
	 enum Rank {
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
		ACE(1);

		private final int value;
		/**
		 * Constructor for enumeration Rank
		 *
		 * @param value the value associated with the rank
		 */
		Rank(int value) {
			this.value = value;
		}

		/**
		 * Returns the value of the rank.
		 *
		 * @return the value of the rank
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * Constructs a Card with the specified suit and rank.
	 *
	 * @param suit the suit of the card
	 * @param rank the rank of the card
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/**
	 * Returns the suit of the card.
	 *
	 * @return the suit of the card
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Returns the rank of the card.
	 *
	 * @return the rank of the card
	 */
	public Rank getRank() {
		return rank;
	}
	/**
	 * Returns a string representation of the card in the format "SUIT_RANK". the
	 * string representation concatenated with the ".PNG" string can be used ad
	 * final part of the resource path.
	 *
	 * @return a string representation of the card
	 */
	@Override
	public String toString() {
		return suit.name() + "_" + rank.name();
	}
	
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}
}
