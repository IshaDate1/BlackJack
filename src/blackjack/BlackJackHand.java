package blackjack;

import java.util.ArrayList;

import cardgame.Hand;
import cardgame.Card;

public class BlackJackHand extends Hand {

	private int _score = 0;

	public BlackJackHand() {
		super();
	}

	public BlackJackHand(Card card1, Card card2) {
		this();
		this.add(card1);
		this.add(card2);

		_score += card1.getNumericValue() + card2.getNumericValue();
	}

	/*
	 * public BlackJackHand(String name, Card card1, Card card2) { this(card1,
	 * card2); super._name = name; }
	 */

	/*
	 * public BlackJackHand(String name) { this(name, Card.getRandomCard(),
	 * Card.getRandomCard()); }
	 */

	public int getScore() {
		return _score;
	}

	public void hit(Card card) {
		super.add(card);
		_score += card.getNumericValue();
		if (_score > 21)
			reduceAces();
	}

	private void reduceAces() {
		ArrayList<Card> cards = super.getCards();
		for (Card card : cards) {
			if (_score > 21) { // prevent unnecessary reductions
				if (card.getValue() == "Ace" && card.getNumericValue() == 11) {
					boolean reduced = card.reduce();
					if (reduced)
						// only reduce the score if the reduction is happening
						_score -= 10;
				}
			}
		}
	}

}
