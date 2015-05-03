/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.game;

import java.util.Collections;
import java.util.Stack;

/**
 * This class models a typical playing card
 * deck of 52 cards
 */
public class Deck {
	
	private Stack<Card> deck;
	private Stack<Card> dealt;

	/**
	 * Creates a new Deck object
	 */
	public Deck() {
		deck = new Stack<>();
		dealt = new Stack<>();
		loadDeck();
	}
	
	/**
	 * Loads the deck with 52 sequential cards
	 */
	private void loadDeck() {
		for(int i = 0; i < 52; i++){
			deck.push(new Card(i));
		}
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		shuffle(deck);
	}
	
	/**
	 * Shuffles a specific deck of cards
	 * @param deckToShuffle A stack representing the deck to be shuffled
	 */
	public void shuffle(Stack<Card> deckToShuffle) {
		Collections.shuffle(deckToShuffle);
	}
	
	/**
	 * Deals out one card from the deck. Tracks the dealt 
	 * cards by placing them into another stack for later use
	 * @return A Card object 
	 */
	public Card deal() {
		Card cardToDeal = null;
		if(!deck.isEmpty()) {
			cardToDeal = deck.pop();
			dealt.push(cardToDeal);
		} else {
			getNewDeck();
			cardToDeal = deck.pop();
		}
		return cardToDeal;
	}
	
	/**
	 *  Swaps the current empty deck with the dealt deck
	 *  and shuffles the cards
	 */
	@SuppressWarnings("unchecked")
	private void getNewDeck() {
		shuffle(dealt);
		deck = (Stack<Card>)dealt.clone();
		dealt.clear();
	}
}
