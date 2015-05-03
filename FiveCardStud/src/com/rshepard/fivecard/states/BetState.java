/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.states;

import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.interfaces.State;

public class BetState implements State {

	/**
	 * This class defines the Betting State
	 * @param game The FiveCard game object reference
	 */
	public BetState(FiveCardStudGame game) {}
	
	@Override
	public void bet() {}

	@Override
	public void newGame() {}

	@Override
	public void deal(int cards) {}

	@Override
	public String getWinner() {
		return null;
	}
}
