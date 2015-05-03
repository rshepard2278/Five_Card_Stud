/**
 * @author Richard Shepard
 * @version May 2, 2015
 */
package com.rshepard.fivecard.enumerators;

/**
 * This enum sets the values for different hand
 * ranks of poker
 */
public enum CardRank {
	ROYAL_FLUSH(9),
	STRAIGHT_FLUSH(8),
	FOUR_OF_A_KIND(7),
	FULL_HOUSE(6),
	FLUSH(5),
	STRAIGHT(4),
	THREE_OF_A_KIND(3),
	TWO_PAIR(2),
	PAIR(1),
	HIGH_CARD(0);
	
	private int rank;
	 

	/**
	 * Sets the rank of the hand
	 * @param rank Integer value of the score
	 * of a poker hand
	 */
	CardRank(int rank) {
		this.rank = rank;
	}
	
	
	/**
	 * Gets the value of the Rank
	 * @return Integer value of the rank
	 */
	public int getValue() {
		return rank;
	}
		
}
