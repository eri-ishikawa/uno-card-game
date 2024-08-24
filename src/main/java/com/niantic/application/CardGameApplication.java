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

        UserInterface.displayUserCards(players.getFirst().getHand().getCards());
        UserInterface.displayTopCardInDiscardPile(discardPile.getTopCard());

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

        players.add(new Player(user));
        players.add(new Player("Kirby"));
    }

    private void takeTurns()
    {
        Queue<Player> queuedPlayers = new LinkedList<>(players);

        while(!queuedPlayers.isEmpty())
        {
            var player = queuedPlayers.poll();

            System.out.println("It's " + player.getName() + "'s turn.");

            discardCard(player);

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
    public void discardCard(Player player)
    {
        Card topCard = discardPile.getTopCard();

        // Get a list of all the cards that can be put in the discard pile
        List<Card> discardableCards = player.getHand().getCards().stream()
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

            // Player draws a card from the draw deck
            Card card = deck.takeCard();

            // Can the card be immediately played?
            if(card.getColor().equals(topCard.getColor()) || card.getNumber() == topCard.getNumber())
            {
                discardPile.addCard(card);
                System.out.println("drew a card that could be immediately played! cards left in deck: " + deck.getCardCount());
            }
            else
            {
                player.getHand().dealTo(card);
                System.out.println("drew a card because no discardable card. cards left in deck: " + deck.getCardCount());
            }
        }
        else
        {
            // Player puts one of their discardable cards in the discard pile

            Card cardToDiscard;

            if(player.getName().equals(players.getFirst().getName()))
            {
                UserInterface.displayPlayableCards(discardableCards);
                cardToDiscard = UserInterface.selectPlayableCard(discardableCards);
            }
            else
            {
                cardToDiscard = discardableCards.getFirst();
            }

            player.getHand().getCards().remove(cardToDiscard);
            discardPile.addCard(cardToDiscard);
        }
    }
}
