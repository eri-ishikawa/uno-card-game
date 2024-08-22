package com.niantic.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        // build the deck of cards
        cards = new ArrayList<>();

        // initializing an array of numbers 0-9
        int[] numbers = new int[10];
        for (int i = 0; i < 10; i++)
        {
            numbers[i] = i;
        }

        String[] colors = {"Red", "Blue", "Green", "Yellow"};

        for(String color : colors)
        {
            for(int number : numbers)
            {
                Card card = new Card(color,number);
                cards.add(card);
            }
        }
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public Card takeCard()
    {
        Card card = cards.removeFirst();
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}
