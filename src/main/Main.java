package main;

//import cardgame.Card;
import cardgame.Deck;
import blackjack.Player;
import blackjack.DealerPlayer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


public class Main
{
    public static void main(String[] args) throws IOException 
    {
		Font font = new Font("Arial", Font.PLAIN, 18);
		
		// Constants
		int MAXPLAYERS = 4;
		int WIDTH = 1200;
		int HEIGHT = 900;
  
		// AWT elements
		Frame BJFrame = new Frame("Java BlackJack");
		BJFrame.setSize(WIDTH, HEIGHT);
		BJFrame.setLayout(null);
		BJFrame.setBackground(new Color(0, 200, 0));
		
		Label playerLabels[] = new Label[MAXPLAYERS];
		Label dealerLabel = new Label();
		dealerLabel.setText("Dealer");
		
		Panel controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		// Input Stream
        BufferedReader br = 
            new BufferedReader(new InputStreamReader(System.in));
        
		//Initializing deck and players
        Deck deck = new Deck(true);
        ArrayList<Player> players = new ArrayList<Player>(0);
        
        players.add(new Player("George", deck.draw(), deck.draw()));
        players.add(new Player("John", deck.draw(), deck.draw()));
        players.add(new Player("Evi", deck.draw(), deck.draw()));
		players.add(new Player("Fotini", deck.draw(), deck.draw()));
        DealerPlayer dealer = new DealerPlayer(deck.draw(), deck.draw());
    	
		//DYNAMIC CONSTANTS
		int NUMPLAYERS = players.size();
		int PLAYER_SPACE = WIDTH/NUMPLAYERS;
		
		// Prepare GUI
		BJFrame.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}        
		});
		
		for(int i = 0; i < MAXPLAYERS; i ++)
		{
			playerLabels[i] = new Label();
		}
		
		// Iterate through the number of players, and set the label alignments
		for(int i = 0; i < NUMPLAYERS; i ++)
		{
			
			playerLabels[i].setText(players.get(i).getName() + "'s Cards:");
			playerLabels[i].setFont(font);
			Label current = playerLabels[i];
			BJFrame.add(current);
			
			FontMetrics metrics = current.getFontMetrics(font);
			
			int textWidth = metrics.stringWidth(current.getText());
			int textHeight = metrics.getHeight();
			
			int x = ((PLAYER_SPACE-textWidth)/2) + i*PLAYER_SPACE;
			int y = (HEIGHT * 6) / 16;
			current.setBounds(x, y, textWidth, textHeight);
			
		}
		dealerLabel.setText("Dealer's Cards:");
		dealerLabel.setFont(font);
		FontMetrics metrics = dealerLabel.getFontMetrics(font);
		int textWidth = metrics.stringWidth(dealerLabel.getText());
		int textHeight = metrics.getHeight();

		int x = ((WIDTH-textWidth)/2);
		int y = (HEIGHT) / 16;
		dealerLabel.setBounds(x, y, textWidth, textHeight);
		BJFrame.add(dealerLabel);
		
		BJFrame.add(controlPanel);
		BJFrame.setVisible(true);  
	  
		////////////////////////////////////////////////////////////////////////
	    //////////    Main Game loop 
		////////////////////////////////////////////////////////////////////////
        for (Player player : players)
        {
            System.out.print(player.printFormatted());
            while (player.getState() == "canHit") 
            {
                System.out.print(player.getName() + "'s turn: ");
                String decision = br.readLine();
                switch(decision)
                {
                    case "hit":
                        player.hit(deck.draw());
                        break;
                    case "stand":
                        player.stand();
                        break;
                    case "double": case "split":
                        System.out.println("Unimplemented feature!");
                        break;
                    default:
                        System.out.println("Unrecognized input, try again:");
                }

                System.out.print(player.printFormatted());
            }
        }

        System.out.println("Dealer's turn:\n");
        System.out.print(dealer.printFormatted());
        while (dealer.getState() == "canHit") 
        {
            dealer.hit(deck.draw());
            System.out.print(dealer.printFormatted());
        }
        
        int dealerScore = dealer.getScore();
        int playerScore = 0; //iterate through user scores
        if(dealer.getState() != "bust")
        {
            for(Player current : players)
            {
                playerScore = current.getScore();
                if(current.getState() == "bust")
                {
                    System.out.println(current.getName() + " busted!");
                }
                else if(playerScore > dealerScore)
                {
                    System.out.println(current.getName() + " won over the Dealer!");
                }
                else if(playerScore == dealerScore)
                {
                    System.out.println(current.getName() + " tied with the Dealer!");
                }
                else
                {
                    System.out.println(current.getName() + " lost to the Dealer!");
                }
            }
        }
        else
        {
            System.out.println("Dealer Busted!");
            for(Player current : players)
            {
                if(current.getState() != "bust") 
                {
                    System.out.println(current.getName() + " won over the Dealer!");
                }
            }
        }
    }

}
