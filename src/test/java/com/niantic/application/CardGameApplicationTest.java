package com.niantic.application;

import com.niantic.models.Deck;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardGameApplicationTest
{
    private Deck deck;
    private DiscardPile discardPile;
    ArrayList<Player> players;
    private CardGameApplication cardGameApplication;

    @BeforeEach
    public void setup()
    {
        deck = new Deck();
        discardPile = new DiscardPile();
        players = new ArrayList<>();
        cardGameApplication = new CardGameApplication();

        cardGameApplication.players.add(new Player("User", true));
        cardGameApplication.players.add(new Player("Opponent"));
    }

    @Test
    public void dealCards_shouldGiveEachPlayer7Cards()
    {
        // arrange
        int expectedCardCount = 7;

        // act
        cardGameApplication.dealCards();
        int actualCardCount = cardGameApplication.players.getFirst().getHand().getCardCount();

        // assert
        assertEquals(expectedCardCount, actualCardCount, "Because it should have generated 7 cards for each player.");
    }
}