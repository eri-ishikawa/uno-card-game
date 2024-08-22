package com.niantic.models;

import java.util.ArrayList;

public class DiscardPile
{
    private final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

}
