package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import com.niantic.ui.UserInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CardGameApplication
{
    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    ArrayList<Player> players = new ArrayList<>();
    
    public void run()
    {
        addPlayers();
        dealCards();

        UserInterface.displayAllPlayersCards(players);
        UserInterface.displayDeck(deck);

        startDiscardPile();

        UserInterface.displayDiscardPile(discardPile);
        UserInterface.displayDeck(deck);

        // Testing whether a card is getting discarded properly

//        players.getFirst().getHand().discardCard(discardPile);
//        UserInterface.displayAllPlayersCards(players);
//        UserInterface.displayDiscardPile(discardPile);

        // Testing queue
        takeTurns();
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

    // This is the first card that will be put down to start the game.
    private void startDiscardPile()
    {
        Card card = deck.takeCard();
        discardPile.addCard(card);
    }

    private void addPlayers()
    {
        players.add(new Player("Kirby"));
        players.add(new Player("Waddle Dee"));
    }

    private void takeTurns()
    {
        Queue<Player> queuedPlayers = new LinkedList<>(players);

        while(!queuedPlayers.isEmpty())
        {
            var player = queuedPlayers.poll();
            System.out.println(player.getName());
            player.getHand().discardCard(discardPile);

            if(player.getHand().getCardCount() > 0)
            {
                queuedPlayers.offer(player);
            }
        }
    }
}
