package blackjack;

import cardgame.Card;

public class Player 
{
    protected BlackJackHand _hand;
    protected String _name = "(no name)";
    protected String _state = "canHit";
    
    public static int numPlayers = 0;

    public Player(String name) 
    {
        _hand = new BlackJackHand();
        _name = name;
    }

    public Player(String name, Card card1, Card card2) 
    {
        this(name);
        _hand = new BlackJackHand(card1, card2);
        if (_hand.getScore() == 21)
            _state = "blackjack";
    }

    public String getName() 
    {
        return _name;
    }

    public String printFormatted() 
    {
        String toPrint = "";
        toPrint += getName() + "'s cards:\n";
        toPrint += _hand.toString();
        toPrint += "\nScore: " + _hand.getScore() + "\n---------------\n";
        return toPrint;
    }

    public int getScore() 
    {
        return _hand.getScore();
    }

    public void hit(Card card) 
    {
        if (_state == "canHit") 
        {
            _hand.hit(card);
            
            if (_hand.getScore() > 21)
                _state = "bust";
            else if (_hand.getScore() == 21) 
                _state = "stand"; // must stand at 21
        } 
        else
            throw new RuntimeException("Can't hit. Object state: " + _state);
    }

    public boolean stand() 
    {
        if (_state == "canHit") 
        {
            _state = "standing";
            return true;
        } else
            return false;
    }

    public String getState() 
    {
        return _state;
    }
}
