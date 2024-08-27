package com.niantic.application;

import com.niantic.models.Player;
import com.niantic.models.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardGameApplicationTest
{
    private CardGameApplication cardGameApplication;

    @BeforeEach
    public void setup()
    {
        cardGameApplication = new CardGameApplication();

        cardGameApplication.players.add(new Player("User", true));
        cardGameApplication.players.add(new Player("Opponent"));
    }

    @Test
    public void dealCards_shouldGiveEachPlayer7Cards_atTheBeginningOfTheGame()
    {
        // arrange
        int expectedCardCount = 7;

        // act
        cardGameApplication.dealCards();
        int actualCardCount = cardGameApplication.players.getFirst().getHand().getCardCount();

        // assert
        assertEquals(expectedCardCount, actualCardCount, "Because it should have generated 7 cards for each player.");
    }

    @Test
    public void playCard_shouldAddSelectedCardToDiscardPile_andBeAtEndOfDiscardPile()
    {
        // arrange
        Card cardToPlay = new Card("Blue", 8);
        Card expectedCard = cardToPlay;
        Player player = cardGameApplication.players.getFirst();

        // act
        cardGameApplication.playCard(player, cardToPlay);
        Card actualCard = cardGameApplication.discardPile.getCards().getLast();

        // assert
        assertEquals(expectedCard, actualCard);
    }

    @Test void getAllPlayableCards_shouldIncludeAllSameColorCards()
    {
        // arrange
        Card topCard = new Card("Blue", 8);
        Card playableCard1 = new Card("Blue", 1);
        Card playableCard2 = new Card("Blue", 2);
        Card unplayableCard = new Card("Yellow", 1);

        ArrayList<Card> playerCards = new ArrayList<>(3);
        ArrayList<Card> expectedPlayableCards = new ArrayList<>(2);

        playerCards.add(playableCard1);
        playerCards.add(playableCard2);
        playerCards.add(unplayableCard);

        expectedPlayableCards.add(playableCard1);
        expectedPlayableCards.add(playableCard2);

        cardGameApplication.discardPile.addCard(topCard);

        // act
        ArrayList<Card> actualPlayableCards = cardGameApplication.getAllPlayableCards(topCard, playerCards);

        // assert
        assertEquals(expectedPlayableCards, actualPlayableCards, "Top card in discard: 8 Blue - Player cards count: 3 - There are 2 blue cards that should be in playable list.");
    }
}