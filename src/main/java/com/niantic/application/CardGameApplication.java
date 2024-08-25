package com.niantic.application;

import com.niantic.models.*;
import com.niantic.models.cards.ActionCard;
import com.niantic.models.cards.Card;
import com.niantic.ui.UserInterface;
import java.util.*;

import static com.niantic.ui.Helper.getUserString;

public class CardGameApplication
{
    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    ArrayList<Player> players = new ArrayList<>();
    Player winner = new Player("no winner");
    public static Scanner userInput = new Scanner(System.in);
    Queue<Player> queuedPlayers;
    
    public void run()
    {
        UserInterface.displayIntro();

        addPlayers();
        UserInterface.displayPlayers(players.getFirst().getName(), players.getLast().getName());

        dealCards();
        startDiscardPile();
        takeTurns();

        UserInterface.displayWinner(winner);
    }

    public void dealCards()
    {
        int numOfStartingCards = 7;
        deck.shuffle();
        System.out.println("Deck has been shuffled!");

        // each player starts out with 7 cards
        for (int i = 0; i < numOfStartingCards; i++)
        {
            for(Player player : players)
            {
                Card card = deck.takeCard();
                player.dealTo(card);
            }
        }
    }

    // This is the first card that will be put down to start the game.
    private void startDiscardPile()
    {
        Card card = deck.takeCard();
        discardPile.addCard(card);
    }

    private void addPlayers()
    {
        String user = getUserString("Enter your name: ");

        players.add(new Player(user, true));
        players.add(new Player("Kirby"));

        queuedPlayers = new LinkedList<>(players);
    }

    public void takeTurns()
    {
        while(!queuedPlayers.isEmpty())
        {
            var player = queuedPlayers.poll();

            UserInterface.displayPlayerTurn(player.getName());

            playerTurn(player);

            // As soon as one player has 0 cards in their hand, we end the game
            if(player.getHand().getCardCount() == 0)
            {
                winner = player;
                break;
            }
        }
    }

    // This is the main action in the game
    public void playerTurn(Player player)
    {
        var topCard = discardPile.getTopCard();
        var playerCards = player.getHand().getCards();

        UserInterface.displayTopCardInDiscardPile(topCard);

        // Get a list of all the color cards that can be put in the discard pile
        // Used a hash set in case I get dupes in the list
        Set<Card> initialPlayableCards = new HashSet<>();

        for(Card card : playerCards)
        {
            if(card.getColor().equals(topCard.getColor()))
            {
                initialPlayableCards.add(card);
            }

            if(card instanceof ActionCard && topCard instanceof ActionCard)
            {
                if(((ActionCard) card).getActionType().equals(((ActionCard) topCard).getActionType()))
                {
                    initialPlayableCards.add(card);
                }
            }

            if(card.getNumber() != -1 && card.getNumber() == topCard.getNumber())
            {
                initialPlayableCards.add(card);
            }
        }

        ArrayList<Card> playableCards = new ArrayList<>(initialPlayableCards);

        // Are there cards that can be played?
        if(playableCards.isEmpty())
        {
            drawCard(player, topCard);
        }
        else
        {
            discardCard(player, playableCards);
        }
    }

    public void drawCard(Player player, Card topCard)
    {
        // Check to see if the deck to draw cards from is empty; if it is, refill the deck
        if(deck.getCardCount() == 0)
        {
            System.out.println("The draw deck is empty! Refilling...");
            deck.refillDeck(discardPile);
        }

        // Player draws a card from the draw deck
        Card card = deck.takeCard();
        System.out.println();
        System.out.println(player.getName() + " drew a card because they didn't have any cards to play.");

        // Can the card be immediately played?
        if(card.getColor().equals(topCard.getColor()))
        {
            if(player.isUser())
            {
                String choice = UserInterface.displayOptionToPlayDrawnCard(card);

                if(!choice.equals("y") && !choice.equals("n"))
                {
                    System.out.println("Please enter a valid response.");
                    UserInterface.displayOptionToPlayDrawnCard(card);
                }

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
        queuedPlayers.offer(player);
    }

    public void drawTwoCards(Player player)
    {
        if(deck.getCardCount() == 1)
        {
            Card card = deck.takeCard();
            player.getHand().dealTo(card);

            System.out.println("The draw deck is empty! Refilling...");
            deck.refillDeck(discardPile);

            card = deck.takeCard();
            player.getHand().dealTo(card);
        }

        Card card = deck.takeCard();
        player.getHand().dealTo(card);

        card = deck.takeCard();
        player.getHand().dealTo(card);
    }

    public void discardCard(Player player, ArrayList<Card> playableCards)
    {
        // Player puts one of their playable cards in the discard pile

        Card cardToDiscard;

        if(player.isUser())
        {
            UserInterface.displayUserCards(player.getHand().getCards());
            UserInterface.displayUserPlayableCards(playableCards);
            cardToDiscard = UserInterface.selectUserPlayableCard(playableCards);
        }
        else
        {
            Collections.shuffle(playableCards);
            cardToDiscard = playableCards.getFirst();
        }

        player.getHand().getCards().remove(cardToDiscard);
        discardPile.addCard(cardToDiscard);

        UserInterface.displayCardToPlay(player.getName(), cardToDiscard);
        queuedPlayers.offer(player);

        if(cardToDiscard instanceof ActionCard)
        {
            playActionCard();
        }
    }

    public void playActionCard()
    {
        String actionType = ((ActionCard)discardPile.getTopCard()).getActionType();
        Player skippedPlayer = null;

        if(actionType.equals("Skip"))
        {
            skippedPlayer = queuedPlayers.poll();
            System.out.println("Skipped " + skippedPlayer.getName() + "'s turn.");
        }
        else if(actionType.equals("Draw Two"))
        {
            skippedPlayer = queuedPlayers.poll();
            System.out.println(skippedPlayer.getName() + " will draw two cards and skip their turn.");
            drawTwoCards(skippedPlayer);
        }
        queuedPlayers.offer(skippedPlayer);
    }
}
