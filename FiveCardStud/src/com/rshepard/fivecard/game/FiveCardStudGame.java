/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.game;

import com.rshepard.fivecard.interfaces.State;
import com.rshepard.fivecard.states.BetState;
import com.rshepard.fivecard.states.CheckWinState;
import com.rshepard.fivecard.states.DealState;
import com.rshepard.fivecard.states.NewGameState;

/**
 *Simulates the different states of a 
 *game of Five Card Stud.  I experimented with
 *using the state machine design pattern
 */
public class FiveCardStudGame {
	
	public Dealer dealer;
	private HumanPlayer player;
	private Deck deck;
	private int pot;
	private int betAmount;
	private boolean isWon;
	
	private State currentState;
	
	private State bet;
	private State checkWin;
	private State deal;
	private State newGame;

	/**
	 * Creates a new Five Card Stud instance and initializes
	 * all the possible states
	 */
	public FiveCardStudGame() {
		deck = new Deck();
		initStates();
	}
	
	/**
	 * Loads two players (dealer, player)
	 */
	public void loadPlayers() {
		if(player == null) {
			player = new HumanPlayer();
		} else {
			player.clearHand();
		}
		dealer = new Dealer();
	}
	
	/**
	 * Initializes all the different states
	 * of the game and registers this instance
	 * of blackjack for a reference in each state
	 */
	private void initStates() {
		bet = new BetState(this);
		checkWin = new CheckWinState(this);
		deal = new DealState(this);
		newGame = new NewGameState(this);
	}
	
	/**
	 * @return If the value of the bet is valid when
	 * compared to the players remaining amount in their
	 * wallet
	 */
	public boolean isBetValid() {
		boolean betValid = false;
		if(betAmount == 0) {
			betValid = false;
		}else if(betAmount <= player.getWallet()) {
			player.subtractBet(betAmount);
			betValid = true;
			pot += betAmount;
			betAmount = 0;
		} else {
			betValid = false;
		}
		return betValid;
	}
	
	/**
	 * @return The boolean indicating whether or 
	 * not the game has been won
	 */
	public boolean isWon() {
		return isWon;
	}
	
	/**
	 * Begins a new game of Five Card Stud inits the
	 * pot to zero and isWon to false and sets the
	 * current state to new game and calls that method
	 */
	public void newGame() {
		isWon = false;
		pot = 0;
		currentState = newGame;
		currentState.newGame();
	}
	
	/**
	 * Calls the bet method of the current state
	 */
	public void bet() {
		currentState.bet();
	}
	
	/**
	 * Calls the deal method of the current state
	 * @param cards the number of cards to be dealt
	 */
	public void deal(int cards) {
		currentState.deal(cards);
	}
	
	/** Gets the winner of the game
	 * @return String of the winner's name
	 */
	public String getWinner() {
		String winner = currentState.getWinner();
		return winner;
	}
	
	/**
	 * Calls the playerTurn method of the current state
	 */
	public void playerTurn() {
		currentState.bet();
	}
	
	
	/**
	 * Doubles the pot and adds it to the player's wallet
	 */
	public void payout() {
		player.addToWallet(pot * 2);
	}

	/**
	 * @param state State to set the current state of the game
	 */
	public void setState(State state) {
		System.out.println("Setting current state to: " + state.toString());
		this.currentState = state;
	}

	/**
	 * @return the currentState
	 */
	public State getState() {
		return currentState;
	}

	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return the dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * @return the player
	 */
	public HumanPlayer getPlayer() {
		return player;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @return the bet
	 */
	public State getBet() {
		return bet;
	}

	/**
	 * @return the checkWin
	 */
	public State getCheckWin() {
		return checkWin;
	}

	/**
	 * @return the deal
	 */
	public State getDeal() {
		return deal;
	}

	/**
	 * @return the newGame
	 */
	public State getNewGame() {
		return newGame;
	}

	/**
	 * @return the pot
	 */
	public int getPot() {
		return pot;
	}

	/**
	 * @param pot the pot to set
	 */
	public void setPot(int pot) {
		this.pot = pot;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * @param isWon the isWon to set
	 */
	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}

	/**
	 * @return the betAmount
	 */
	public int getBetAmount() {
		return betAmount;
	}

	/**
	 * @param betAmount the betAmount to set
	 */
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
}
