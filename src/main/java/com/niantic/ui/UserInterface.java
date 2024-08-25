package com.niantic.ui;

import com.niantic.models.cards.Card;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import com.niantic.models.Deck;

import java.util.ArrayList;

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
                System.out.println("  " + card.toString());
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
            System.out.println(numOfCards + "  " + card.toString());
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
            System.out.println(numOfCards + "  " + card.toString());
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
        System.out.println("Here are your current cards:");
        userCards.forEach(card -> System.out.println(card.toString()));
        waitForUser();
    }

    public static void displayTopCardInDiscardPile(Card topCard)
    {
        System.out.println();
        System.out.print("Top card in the discard pile: ");
        System.out.println(topCard.toString());
    }

    public static void displayUserPlayableCards(ArrayList<Card> playableCards)
    {
        System.out.println("These are the cards you can play: ");
        for (int i = 0; i < playableCards.size(); i++)
        {
            // i + 1 is there purely as an aesthetic choice. It's just awkward if
            // the user can select a choice of "0"

            System.out.print((i + 1) + ". ");
            System.out.println(playableCards.get(i).toString());
        }
    }

    public static Card selectUserPlayableCard(ArrayList<Card> playableCards)
    {
        // In displayUserPlayableCards, I added i + 1 for an aesthetic reason, so here we
        // revert it back (i - 1) for functional reasons

        System.out.println();
        int chosenCard = 0;

        try
        {
            chosenCard = getUserInt("Type the number on the list of the card you'd like to play, and press ENTER: ") - 1;
        }
        catch(Exception e)
        {
            System.out.println();
            System.out.println("Please enter a valid response.");
            selectUserPlayableCard(playableCards);
        }
        return playableCards.get(chosenCard);
    }

    public static void displayCardToPlay(String playerName, Card cardToPlay)
    {
        System.out.println();
        System.out.println(playerName + " added " + cardToPlay.toString() + " to the discard pile.");
        waitForUser();
    }

    public static void displayPlayerTurn(String playerName)
    {
        System.out.println("-".repeat(20));
        System.out.println("It's " + playerName + "'s turn.");
    }

    public static String displayOptionToPlayDrawnCard(Card card)
    {
        System.out.println();
        System.out.println("You drew " + card.toString() + ".");
        return getUserString("This card can be placed. Would you like to play the card? (y/n): ");
    }
}
