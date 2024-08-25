package com.niantic.models;

import com.niantic.models.cards.Card;

import java.util.ArrayList;

public class DiscardPile
{
    private final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public Card getTopCard()
    {
        return cards.getLast();
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
