package model;

/**
 * The Chips class represents a collection of playing Chips with a specific name
 * and each name has an integer representing the associated value.
 */
public enum Chips {
	CHIPS100(100), CHIPS50(50), CHIPS25(25), CHIPS5(5);

	private final int value;

	/**
	 * constructor of the enumeration,representing the integer value associated to
	 * each chip
	 *
	 * @param value
	 */
	Chips(int value) {
		this.value = value;
	}

	/**
	 * this method is a getter for the associated value of a specific chip
	 *
	 * @return value, the chip associated integer value
	 */
	public int getValue() {
		return value;
	}

}
