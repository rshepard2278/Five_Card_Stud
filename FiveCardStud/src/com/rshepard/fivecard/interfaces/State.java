/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.interfaces;

/**
 *  State interface defines the overall functionality
 *  of the states of the game
 */
public interface State {
	
	/**
	 *  Defines the newGame action
	 */
	public void newGame();
	
	/**
	 *  Defines the deal action
	 *  @param cards the number of cars to be dealt
	 */
	public void deal(int cards);
	
	/**
	 *  Defines the bet action
	 */
	public void bet();
	
	/**
	 * Defines the getWinner action
	 * @return The string value of the winner
	 */
	public String getWinner();
}
