package com.niantic.models;

import com.niantic.ui.UserInterface;

import java.util.List;

public class Action
{
    // This is the main action in the game
    public static void discardCard(Player player, Deck deck, DiscardPile discardPile)
    {
        Card topCard = discardPile.getTopCard();
        UserInterface.displayTopCardInDiscardPile(topCard);

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
                System.out.println("The draw deck is empty! Refilling...");
                deck.refillDeck(discardPile);
            }

            // Player draws a card from the draw deck
            Card card = deck.takeCard();
            System.out.println(player.getName() + " drew a card because they didn't have any cards to play.");

            // Can the card be immediately played?
            if(card.getColor().equals(topCard.getColor()) || card.getNumber() == topCard.getNumber())
            {
                if(player.isUser())
                {
                    String choice = UserInterface.displayOptionToPlayDrawnCard(card);
                    if (choice.equals("y"))
                    {
                        discardPile.addCard(card);
                        System.out.println("You played the card that you drew.");
                    }
                    else if(choice.equals("n"))
                    {
                        System.out.println("You chose not to play the card.");
                    }
                }
                else
                {
                    discardPile.addCard(card);
                    System.out.println(player.getName() + " played the card that they drew.");
                }
            }
            else
            {
                player.getHand().dealTo(card);
            }
        }
        else
        {
            // Player puts one of their discardable cards in the discard pile

            Card cardToDiscard;

            if(player.isUser())
            {
                UserInterface.displayUserCards(player.getHand().getCards());
                UserInterface.displayUserPlayableCards(discardableCards);
                cardToDiscard = UserInterface.selectUserPlayableCard(discardableCards);
            }
            else
            {
                cardToDiscard = discardableCards.getFirst();
            }

            player.getHand().getCards().remove(cardToDiscard);
            discardPile.addCard(cardToDiscard);
            UserInterface.displayCardToPlay(player.getName(), cardToDiscard);
        }
    }
}
