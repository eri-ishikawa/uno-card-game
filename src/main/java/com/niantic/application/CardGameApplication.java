package com.niantic.application;

import com.niantic.models.*;
import com.niantic.ui.UserInterface;

import java.util.*;

import static com.niantic.ui.Helper.getUserString;

public class CardGameApplication
{
    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    ArrayList<Player> players = new ArrayList<>();
    Player winner = new Player("no winner");
    public static Scanner userInput = new Scanner(System.in);
    
    public void run()
    {
        UserInterface.displayIntro();

        addPlayers();
        UserInterface.displayPlayers(players.getFirst().getName(), players.getLast().getName());

        dealCards();
        startDiscardPile();
        takeTurns();

        UserInterface.displayWinner(winner);

    }

    private void dealCards()
    {
        deck.shuffle();
        System.out.println();
        System.out.println("Deck has been shuffled!");

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
        String user = getUserString("Enter your name: ");

        players.add(new Player(user, true));
        players.add(new Player("Kirby"));
    }

    private void takeTurns()
    {
        Queue<Player> queuedPlayers = new LinkedList<>(players);

        while(!queuedPlayers.isEmpty())
        {
            var player = queuedPlayers.poll();

            UserInterface.displayPlayerTurn(player.getName());

            Action.discardCard(player, deck, discardPile);

            // As soon as one player has 0 cards in their hand, we end the game
            if(player.getHand().getCardCount() == 0)
            {
                winner = player;
                break;
            }
            else
            {
                queuedPlayers.offer(player);
            }
        }
    }
}
