package com.niantic.models;

import java.util.ArrayList;
import java.util.List;

import com.niantic.models.DiscardPile;

public class Hand
{
    private final ArrayList<Card> cards = new ArrayList<>();
    DiscardPile discardPile = new DiscardPile();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public void dealTo(Card card)
    {
        cards.add(card);
    }

    public void discardCard(DiscardPile discardPile)
    {
        Card topCard = discardPile.getTopCard();

        List<Card> discardableCards = cards.stream()
                                           .filter(card -> card.getColor().equals(topCard.getColor())
                                                        || card.getNumber() == topCard.getNumber())
                                           .toList();

        Card cardToDiscard = discardableCards.getFirst();
        cards.remove(cardToDiscard);
        discardPile.addCard(cardToDiscard);
    }

//    public int getPointValue()
//    {
//        // return sum of all card points
//        int sum = 0;
//
//        for(Card card : cards)
//        {
//            sum += card.getNumber();
//        }
//        return sum;
//    }
}
