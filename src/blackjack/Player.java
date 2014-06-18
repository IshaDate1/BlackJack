package blackjack;

import cardgame.Card;

public class Player {
	private BlackJackHand _hand;
	private String _name = "(no name)";
	public static int numPlayers = 0;
	private String _state = "canHit";

	public Player() {
		_hand = new BlackJackHand();
	}

	public Player(String name) {
		this();
		_name = name;
	}
	
	public Player(String name, Card card1, Card card2) {
		this(name);
		_hand = new BlackJackHand(card1, card2);
	}
	
	public String getName() {
		return _name;
	}
	
	public String printFormatted() {
		String toPrint = "";
		toPrint += getName() + "'s cards:\n";
		toPrint += _hand.toString();
		toPrint += "\nScore: " + _hand.getScore()
				+ (_hand.getScore() < 21 ? "\n---------------\n" : "\n===============\n");
		return toPrint;
	}
	
	public int getScore() {
		return _hand.getScore();
	}
	
	public void hit(Card card)
	{
		_hand.hit(card);
	}
}
