package com.niantic.models;

import com.niantic.models.cards.ActionCard;
import com.niantic.models.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> deck;

    public Deck()
    {
        // build the deck of deck
        deck = new ArrayList<>(92);

        // initializing an array of numbers 1-9
        int[] numbers = new int[9];
        for (int i = 1; i < 9; i++)
        {
            numbers[i] = i;
        }

        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        String[] actionTypes = {"Skip", "Draw Two"};

        for(String color : colors)
        {
            // There's only one of each color 0 cards
            deck.add(new Card(color, 0));

            for(int number : numbers)
            {
                // There are two of each number card
                deck.add(new Card(color, number));
                deck.add(new Card(color, number));
            }

            for(String actionType : actionTypes)
            {
                // There are two of each action card
                deck.add(new ActionCard(color, actionType));
                deck.add(new ActionCard(color, actionType));
            }
        }
    }

    public int getCardCount()
    {
        return deck.size();
    }

    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    public Card takeCard()
    {
        return deck.removeFirst();
    }

    public void refillDeck(DiscardPile discardPile)
    {
        // Transferring the discard pile -> draw deck and shuffling the deck
        deck = new ArrayList<>(discardPile.getCards());
        Collections.shuffle(deck);

        // Get the card that will become the new top card
        Card newTopCard = discardPile.getCards().getLast();

        // The discard pile will now be empty again, with only the new top card in it
        discardPile.getCards().clear();
        discardPile.getCards().add(newTopCard);
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }
}
