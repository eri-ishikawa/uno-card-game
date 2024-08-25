package com.niantic.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> deck;

    public Deck()
    {
        // build the deck of deck
        deck = new ArrayList<>();

        // initializing an array of numbers 0-9
        int[] numbers = new int[10];
        for (int i = 0; i < 10; i++)
        {
            numbers[i] = i;
        }

        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        String[] actionTypes = {"Skip", "Draw Two"};
        String cardType;

        for(String color : colors)
        {
            for(int number : numbers)
            {
                deck.add(new Card(color, number));
            }

            for(String actionType : actionTypes)
            {
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
