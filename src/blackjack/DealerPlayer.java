package blackjack;

import cardgame.Card;

public class DealerPlayer extends Player
{

	public DealerPlayer() {
		super();
		super._name = "dealer";
	}

	public DealerPlayer(Card card1, Card card2) {
		super("dealer", card1, card2);
	}

}
