package com.niantic.application;

import com.niantic.models.*;
import com.niantic.ui.UserInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardGameApplication
{
    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    ArrayList<Player> players = new ArrayList<>();
    Player winner = new Player("no winner");
    
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
        UserInterface.displayWinner(winner);

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
            discardCard(player.getHand());
            UserInterface.displayDiscardPile(discardPile);

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

    // This is the main action in the game
    public void discardCard(Hand playerHand)
    {
        Card topCard = discardPile.getTopCard();

        // Get a list of all the cards that can be put in the discard pile
        List<Card> discardableCards = playerHand.getCards().stream()
                .filter(card -> card.getColor().equals(topCard.getColor())
                        || card.getNumber() == topCard.getNumber())
                .toList();

        // If there are no cards that can be put in the discard pile...
        if(discardableCards.isEmpty())
        {
            // Check to see if the deck to draw cards from is empty; if it is, refill the deck
            if(deck.getCardCount() == 0)
            {
                System.out.println("deck is empty! refilling...");
                deck.refillDeck(discardPile);
            }

            // Player draws a card from the draw deck and adds it to their hand
            Card card = deck.takeCard();
            playerHand.dealTo(card);
            System.out.println("drew a card because no discardable card. cards left in deck: " + deck.getCardCount());
        }
        else
        {
            // Player puts one of their discardable cards in the discard pile
            Card cardToDiscard = discardableCards.getFirst();
            playerHand.getCards().remove(cardToDiscard);
            discardPile.addCard(cardToDiscard);
        }
    }
}
