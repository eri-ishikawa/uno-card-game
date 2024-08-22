package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.Player;
import com.niantic.ui.UserInterface;

import java.util.ArrayList;

public class CardGameApplication
{
    Deck deck = new Deck();
    ArrayList<Player> players = new ArrayList<>();
    
    public void run()
    {
        addPlayers();
        dealCards();
        UserInterface.displayAllPlayersCards(players);
    }

    private void dealCards()
    {
        deck.shuffle();

        // each player starts out with 7 cards
        for (int i = 0; i < 7; i++)
        {
            for(Player player : players)
            {
                Card card = deck.takeCard();
                player.dealTo(card);
            }
        }
    }

    private void addPlayers()
    {
        players.add(new Player("Kirby"));
        players.add(new Player("Waddle Dee"));
    }
}
