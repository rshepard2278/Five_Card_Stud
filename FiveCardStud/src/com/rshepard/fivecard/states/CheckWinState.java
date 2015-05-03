/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.states;

import java.util.Arrays;
import java.util.Stack;

import com.rshepard.fivecard.enumerators.CardRank;
import com.rshepard.fivecard.game.Card;
import com.rshepard.fivecard.game.FiveCardStudGame;
import com.rshepard.fivecard.interfaces.State;

/**
 * This class defines the checkWinState
 */
public class CheckWinState implements State {

	private FiveCardStudGame game;

	/**
	 * @param game A reference to the current game
	 */
	public CheckWinState(FiveCardStudGame game) {
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see com.rshepard.fivecard.interfaces.State#getWinner()
	 * Returns the String value of the winner
	 * @return String value of the winner
	 */
	@Override
	public String getWinner() {
		String winner = "";
		Stack<Card> dHand = game.getDealer().getHand();
		Stack<Card> pHand = game.getPlayer().getHand();
		CardRank dRank = getHandRank(dHand);
		CardRank pRank = getHandRank(pHand);
		CardRank winRank;
		if(dRank.getValue() > pRank.getValue()) {
			winner = game.getDealer().getName();
			winRank = dRank;
		} else if(dRank.getValue() == pRank.getValue()){
			winner = tieBreaker(dHand, pHand);
			if(winner.equals("Player")) {
				winRank = pRank;
				game.payout();
			} else {
				winRank = dRank;
			}
		} else {
			winner = game.getPlayer().getName();
			game.payout();
			winRank = pRank;
		}
		return winner + " wins with " + winRank.toString().toLowerCase();
	}
	
	
	
	/**
	 * Gets the rank of the given hand
	 * @param handStack The hand to be ranked
	 * @return The CardRank Enum of the evaluated hand
	 */
	public CardRank getHandRank(Stack<Card> handStack) {
		CardRank rank;
		Card[] hand = new Card[5];
		handStack.toArray(hand);		
		if(checkRoyalFlush(hand)) {
			rank = CardRank.ROYAL_FLUSH;
		} else if(checkStraightFlush(hand)) {
			rank = CardRank.STRAIGHT_FLUSH;
		} else if(checkFourOfaKind(hand)) {
			rank = CardRank.FOUR_OF_A_KIND;
		} else if(checkFullHouse(hand)) {
			rank = CardRank.FULL_HOUSE;
		} else if(checkFlush(hand)) {
			rank = CardRank.FLUSH;
		} else if(checkStraight(hand)) {
			rank = CardRank.STRAIGHT;
		} else if(checkThreeOfaKind(hand)) {
			rank = CardRank.THREE_OF_A_KIND;
		} else if(checkTwoPair(hand)) {
			rank = CardRank.TWO_PAIR;
		} else if(checkPair(hand)) {
			rank = CardRank.PAIR;
		} else {
			rank = CardRank.HIGH_CARD;
		}
		return rank;
	}
	
	/**
	 * Checks the given hand for a Royal Flush
	 * @param hand The hand Stack to be tested
	 * @return True if Royal Flush
	 */
	public static boolean checkRoyalFlush(Card[] hand) {
		boolean isRoyalFlush = false;
		if(checkFlush(hand)) {
			if(hand[0].getValue() == 0 && hand[1].getValue() == 9 && hand[2].getValue() == 10 && hand[3].getValue() == 11 && hand[4].getValue() ==12) {
				isRoyalFlush = true;
			}
		}
		return isRoyalFlush;
	}
	
	/**
	 * Checks the given hand for a Straight Flush
	 * @param hand The hand to be tested
	 * @return True if Straight Flush
	 */
	public static boolean checkStraightFlush(Card[] hand) {
		boolean isStraightFlush = false;
		if(checkFlush(hand) && checkStraight(hand)) {
			isStraightFlush = true;
		}
		return isStraightFlush;
	}
	
	/**
	 * Checks the given hand for four of a kind
	 * @param hand The hand to be tested
	 * @return Boolean of the test
	 */
	public static boolean checkFourOfaKind(Card[] hand) {
		boolean isFour = false;
		Arrays.sort(hand);
		if(hand[0].getValue() == hand[3].getValue()) {
			isFour = true;
		}
		return isFour;
	}
	
	/**
	 * Checks if the given hand is a full house
	 * @param hand The hand to be tested
	 * @return Boolean value of the test
	 */
	public static boolean checkFullHouse(Card[] hand) {
		boolean isFullHouse;
		Arrays.sort(hand);
		if(hand[0].getValue() == hand[2].getValue() && hand[3].getValue() == hand[4].getValue()) {
			isFullHouse = true;
		} else if(hand[0].getValue() == hand[1].getValue() && hand[2].getValue() == hand[4].getValue()) {
			isFullHouse = true;
		} else {
			isFullHouse = false;
		}
		return isFullHouse;
	}
	
	/**
	 * Checks the given hand for straight
	 * @param hand The hand to be tested
	 * @return Boolean value of the test
	 */
	public static boolean checkStraight(Card[] hand) {
		boolean isStraight = true;
		Arrays.sort(hand);
		int value = hand[0].getValue();
		int value1 = hand[1].getValue();
		int value2 = hand[2].getValue();
		int value3 = hand[3].getValue();
		int value4 = hand[4].getValue();
		for(int i = 0; i < hand.length; i++) {
			if(hand[i].getValue() != hand[0].getValue() + i) {
				isStraight = false;
			}
		}
		if(value == 0 && value1 == 9 && value2 == 10 && value3 == 11 && value4 == 12) {
			isStraight = true;
		}
		return isStraight;
	}
	
	/**
	 * Checks the given hand for a flush
	 * @param hand The hand to be tested
	 * @return Boolean value of the test
	 */
	public static boolean checkFlush(Card[] hand) {
		Arrays.sort(hand, Card.CardSuitComparator);
		boolean isFlush = false;
		if(hand[0].getSuit() == hand[4].getSuit()) {
			isFlush = true;
		} else {
			isFlush = false;
		}
		return isFlush;
	}
	
	/**
	 * Checks the given hand for three of a kind
	 * @param hand The hand to be tested
	 * @return boolean value of the test
	 */
	public static boolean checkThreeOfaKind(Card[] hand) {
		boolean isThree;
		Arrays.sort(hand);
		if(hand[0].getValue() == hand[2].getValue()) {
			isThree = true;
		} else if(hand[1].getValue() == hand[3].getValue()) {
			isThree = true;
		} else if(hand[2].getValue() == hand[4].getValue()) {
			isThree = true;
		} else {
			isThree = false;
		}
		return isThree;
	}
	
	/**
	 * Checks the given hand for two pair
	 * @param hand The hand to be tested
	 * @return Boolean value of the test
	 */
	public static boolean checkTwoPair(Card[] hand) {
		boolean isTwoPair;
		Arrays.sort(hand);
		if(hand[0].getValue() == hand[1].getValue() && hand[2].getValue() == hand[3].getValue()) {
			isTwoPair = true;
		} else if(hand[1].getValue() == hand[2].getValue() && hand[3].getValue() == hand[4].getValue()) {
			isTwoPair = true;
		} else {
			isTwoPair = false;
		}
		return isTwoPair;
	}
	
	/**
	 * Checks the given hand for one pair
	 * @param hand The hand to be tested
	 * @return Boolean value of the test
	 */
	public static boolean checkPair(Card[] hand) {
		boolean isPair;
		Arrays.sort(hand);
		if(hand[0].getValue() == hand[1].getValue()) {
			isPair = true;
		} else if(hand[1].getValue() == hand[2].getValue()) {
			isPair = true;
		} else if(hand[2].getValue() == hand[3].getValue()) {
			isPair = true;
		} else if(hand[3].getValue() == hand[4].getValue()) {
			isPair = true;
		} else {
			isPair = false;
		}
		return isPair;
	}
	
	/**
	 * In the event of a tie both hands are tested for 
	 * the highest card. If an ace is found that hand is
	 * ranked higher
	 * @param dHandStack The dealer hand
	 * @param pHandStack The player hand
	 * @return String value of the better hand
	 */
	private String tieBreaker(Stack<Card> dHandStack, Stack<Card> pHandStack) {
		String winner = "";
		Card[] pHand = new Card[5];
		pHandStack.toArray(pHand);		
		Arrays.sort(pHand);
		Card[] dHand = new Card[5];
		dHandStack.toArray(dHand);		
		Arrays.sort(dHand);
		if(pHand[4].getValue() > dHand[4].getValue() || pHand[0].getValue() == 0) {
			winner = "Player";
		} else {
			winner = "Dealer";
		}
		return winner;
	}

	@Override
	public void newGame() {}

	@Override
	public void deal(int cards) {}

	@Override
	public void bet() {}	
}
