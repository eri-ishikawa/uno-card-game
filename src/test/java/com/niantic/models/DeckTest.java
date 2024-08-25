package com.niantic.models;

import com.niantic.models.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest
{
    private Deck deck;
    private DiscardPile discardPile;

    @BeforeEach
    public void setup()
    {
        deck = new Deck();
        discardPile = new DiscardPile();
    }

    @Test
    public void deck_shouldGenerate_aDeckOf92Cards()
    {
        // arrange
        int expectedCardCount = 92;

        // act
        int actualCardCount = deck.getCardCount();

        // assert
        assertEquals(expectedCardCount, actualCardCount, "Because it should have generated 92 cards.");
    }

    @Test
    public void refillDeck_shouldTransferDiscardPile_toDeck()
    {
        // arrange
        int expectedCardCount = 1;
        Card card = new Card("Blue", 1);
        discardPile.addCard(card);

        // act
        deck.getDeck().clear();
        deck.refillDeck(discardPile);
        int actualCardCount = deck.getCardCount();

        // assert
        assertEquals(expectedCardCount, actualCardCount, "Because it should have transferred the 1 card that was in the discard pile to the deck.");
    }
}