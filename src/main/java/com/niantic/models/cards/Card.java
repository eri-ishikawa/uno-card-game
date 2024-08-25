package com.niantic.models.cards;

public class Card
{
    protected String color;
    protected int number;

    public Card(String color, int number)
    {
        this.color = color;
        this.number = number;
    }

    public String getColor()
    {
        return color;
    }

    public int getNumber()
    {
        return number;
    }

    public String toString()
    {
        return number + " " + color;
    }


}
