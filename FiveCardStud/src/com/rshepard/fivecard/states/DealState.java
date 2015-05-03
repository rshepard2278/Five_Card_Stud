/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.states;

import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.game.Deck;
import com.rshepard.fivecard.interfaces.State;

/**
 * This class defines the actions of the Deal State
 */
public class DealState implements State {

	private FiveCardStudGame game;

	/**
	 * Sets a reference to the current game
	 * @param game Current FiveCard object to reference to
	 */
	public DealState(FiveCardStudGame game) {
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see com.rshepard.blackjack.interfaces.State#deal()
	 * Deals the cards to the players using the deck from the
	 * current game and sets the state to bet
	 */
	@Override
	public void deal(int cards) {
		Deck deck = shuffleDeck();
		dealCards(deck, cards);
		game.setCurrentState(game.getBet());
	}
	
	/**
	 * Shuffles and returns the deck
	 * @return The shuffled deck to be used
	 */
	private Deck shuffleDeck() {
		game.getDeck().shuffle();
		return game.getDeck();		
	}
	
	/**
	 * Deals the cards to the players
	 * @param The Deck object to deal from
	 */
	private void dealCards(Deck deck, int cards) {
		for(int i = 0; i < cards; i++) {
			game.getPlayer().addCard(game.getDeck().deal());
			game.getDealer().addCard(game.getDeck().deal());
		}
	}
	
	@Override
	public void newGame() {}
	
	@Override
	public void bet() {}

	@Override
	public String getWinner() {
		return "Called from wrong state";
	}
}
