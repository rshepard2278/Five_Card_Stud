/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.controller;

import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.gui.GUI;

/**
 *Controller handles the flow of the Five Card Stud game
 *with a game loop that is exit once the user closes the 
 *window, clicks quit, or runs out of money
 */
public class Controller {

	private FiveCardStudGame game;
	private GUI gui;

	/**
	 * Instantiates a new game controller
	 */
	public Controller() {
		game = new FiveCardStudGame();        
	}
	
	/**
	 * Begins a gameloop of blackjack
	 */
	public void play() {
		gui = new GUI();
		while(true) {
			game.newGame();
			game.deal(2);
			gui.loadTable(game);
			for(int i = 0; i < 4; i++) {
				game.deal(1);
				game.bet();
				gui.getInput();
			}
			game.setState(game.getCheckWin());
			gui.setInfoText(game.getWinner());
			gui.getInput();
		}
	}
}
