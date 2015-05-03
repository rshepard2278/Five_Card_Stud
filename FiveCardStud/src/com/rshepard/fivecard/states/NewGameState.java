/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.states;

import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.interfaces.State;

/**
 *This class defines the NewGame state
 */
public class NewGameState implements State {

	private FiveCardStudGame game;

	/**
	 * Sets a reference to the current game
	 * @param game The FiveCard to be referenced
	 */
	public NewGameState(FiveCardStudGame game) {
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see com.rshepard.blackjack.interfaces.State#newGame()
	 * Loads the two players and sets the state to deal
	 */
	@Override
	public void newGame() {
		game.loadPlayers();
		game.setCurrentState(game.getDeal());
	}
	
	@Override
	public void deal(int cards) {}

	@Override
	public void bet() {}

	@Override
	public String getWinner() {
		return "Called from wrong state";
	}
}
