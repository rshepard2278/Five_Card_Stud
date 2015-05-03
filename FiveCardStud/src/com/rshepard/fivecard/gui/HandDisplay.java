/**
 * @author Richard Shepard
 * @version Apr 24, 2015
 */
package com.rshepard.fivecard.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.game.Card;
import com.rshepard.fivecard.game.Dealer;
import com.rshepard.fivecard.interfaces.Player;
import com.rshepard.fivecard.states.CheckWinState;

/**
 *  This class handles the display of a single players hand
 *  in the GUI with a JPanel
 */
public class HandDisplay extends JPanel {

	private static final long serialVersionUID = 2813901392941793710L;
	private Stack<Card> hand;
	private ArrayList<BufferedImage> cards;
	private CardBuilder cb;
	private Player player;
	private FiveCardStudGame game;


	/**
	 * Creates a HandDisplay for a specific player. Initializes
	 * the player, game, cards(arrayList), hand. Also sets the 
	 * layout manager and creates a CardBuilder object that returns
	 * the needed cards
	 * @param player The player's hand to be displayed
	 * @param game A reference to the current game. This is
	 * used to update the display based upon the current state
	 * of the game
	 */
	public HandDisplay(Player player , FiveCardStudGame game) {
		this.player = player;
		this.game = game;
		cards = new ArrayList<>();
		hand = player.getHand();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		cb = new CardBuilder();
		loadCards();
	}
	

	/**
	 * Loads the proper cards on the screen depending
	 * on the current state of the game
	 */
	private void loadCards() {
		if(!(game.getState() instanceof CheckWinState) && player instanceof Dealer) {
			for(int i = 0; i < hand.size(); i++) {
				if(i == 0) {
					cards.add(cb.getCardImage(null));
				} else {
					cards.add(cb.getCardImage(hand.get(i)));
				}
			}
		} else {
			for(Card card : hand) {
				cards.add(cb.getCardImage(card));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		cards.clear();
		hand = player.getHand();
		loadCards();
		drawCards(g);
	}
	
	/**
	 * Draws the cards to the screen and displays the proper label depending
	 * on which player is displayed and will also display the current value 
	 * of the hand depending on which state the game is in.
	 * @param Graphics context for drawing on the screen
	 */
	private void drawCards(Graphics g) {
		int x = 85; //width of card
		BufferedImage cardImage = null;
		for(int i = 0; i < cards.size(); i++) {
			cardImage = cards.get(i);
			g.drawImage(cardImage, x * i, 10, this);
		}
		g.setColor(new Color(0, 255, 30));
		g.drawString(player.getName() + "'s cards", 10, 155);
	}
}
