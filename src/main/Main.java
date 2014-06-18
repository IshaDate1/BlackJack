package main;

//import cardgame.Card;
import cardgame.Deck;
import blackjack.Player;

public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck(true);
		
		Player player = new Player("George", deck.draw(), deck.draw());
		Player dealer = new Player("Dealer", deck.draw(), deck.draw());

		System.out.print(player.printFormatted());
		while (player.getScore() < 18) {
			player.hit(deck.draw());
			System.out.print(player.printFormatted());
		}
		
		System.out.println("Dealer's turn:\n");
		System.out.print(dealer.printFormatted());
		while (dealer.getScore() < 18) {
			dealer.hit(deck.draw());
			System.out.print(dealer.printFormatted());
		}
		
	}

}
