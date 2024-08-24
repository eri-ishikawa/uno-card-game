package com.niantic.models;

public class Player
{
    private String name;
    private Hand hand;
    private boolean isUser;

    public Player(String name)
    {
        this.name = name;
        hand = new Hand();
    }

    public Player(String name, boolean isUser)
    {
        this.name = name;
        this.isUser = isUser;
        hand = new Hand();
    }

    public String getName()
    {
        return name;
    }

    public Hand getHand()
    {
        return hand;
    }

    public void dealTo(Card card)
    {
        hand.dealTo(card);
    }

    public boolean isUser()
    {
        return isUser;
    }
}
