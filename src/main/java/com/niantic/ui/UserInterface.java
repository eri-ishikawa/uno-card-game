package com.niantic.ui;

import com.niantic.models.Card;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import com.niantic.models.Deck;

import java.util.ArrayList;
import java.util.List;

import static com.niantic.ui.Helper.*;

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

    public static void displayIntro()
    {
        System.out.println();
        System.out.println(ColorCodes.YELLOW + "*****************************************" + ColorCodes.RESET);
        System.out.println("           Let's play Uno!");
        System.out.println(ColorCodes.YELLOW + "*****************************************" + ColorCodes.RESET);
    }

    public static void displayPlayers(String user, String opponent)
    {
        System.out.println();
        System.out.println("Hi, " + user + "!");
        System.out.println("You are playing against " + opponent + "!");
        waitForUser();
    }

    public static void displayUserCards(ArrayList<Card> userCards)
    {
        System.out.println();
        System.out.println("Here are your 7 starting cards:");
        userCards.forEach(card -> System.out.println(card.getNumber() + " " + card.getColor()));
        waitForUser();
    }

    public static void displayTopCardInDiscardPile(Card topCard)
    {
        System.out.println();
        System.out.println("This is the first card in the discard pile: ");
        System.out.println(topCard.getNumber() + " " + topCard.getColor());
        waitForUser();
    }

    public static void displayPlayableCards(List<Card> playableCards)
    {
        System.out.println();
        System.out.println("These are the cards you can play: ");
        for (int i = 0; i < playableCards.size(); i++)
        {
            // i + 1 is there purely as an aesthetic choice. It's just awkward if
            // the user can select a choice of "0"

            System.out.print((i + 1) + ". ");
            System.out.println(playableCards.get(i).getNumber() + " " + playableCards.get(i).getColor());
        }
    }

    public static Card selectPlayableCard(List<Card> playableCards)
    {
        // In displayPlayableCards, I added i + 1 for an aesthetic reason, so here we
        // revert it back (i - 1) for functional reasons

        System.out.println();
        int chosenCard = getUserInt("Type the number of the card you'd like to play, and press ENTER: ") - 1;
        return playableCards.get(chosenCard);
    }
}
