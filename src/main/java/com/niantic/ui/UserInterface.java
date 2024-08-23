package com.niantic.ui;

import com.niantic.models.Card;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import com.niantic.models.Deck;

import java.util.ArrayList;

public class UserInterface
{
    public static void displayAllPlayersCards(ArrayList<Player> players)
    {
        System.out.println();
        System.out.println("All Players");
        System.out.println("-".repeat(15));
        for (Player player : players)
        {
            System.out.println(player.getName());
            for (Card card : player.getHand().getCards())
            {
                System.out.println("  " + card.getColor() + " " + card.getNumber());
            }
            System.out.println();
        }
        System.out.println("=".repeat(30));
    }

    // This is for my sanity check, to see if cards were properly taken out of the deck
    public static void displayDeck(Deck deck)
    {
        int numOfCards = 1;

        System.out.println();
        System.out.println("Cards in deck");
        System.out.println("-".repeat(15));

        for (Card card : deck.getDeck())
        {
            System.out.println(numOfCards + "  " + card.getColor() + " " + card.getNumber());
            numOfCards++;
        }
        System.out.println();
        System.out.println("=".repeat(30));
    }

    // This is for my sanity check, to see which cards are added to the discard pile
    public static void displayDiscardPile(DiscardPile discardPile)
    {
        int numOfCards = 1;

        System.out.println();
        System.out.println("Discard pile");
        System.out.println("-".repeat(15));

        for (Card card : discardPile.getCards())
        {
            System.out.println(numOfCards + "  " + card.getColor() + " " + card.getNumber());
            numOfCards++;
        }
        System.out.println();
        System.out.println("=".repeat(30));
    }

    public static void displayWinner(Player winner)
    {
        System.out.println();
        System.out.println("Winner: " + winner.getName());
        System.out.println();
    }
}
