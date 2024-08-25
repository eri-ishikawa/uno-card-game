package com.niantic.models.cards;

import com.niantic.ui.ColorCodes;

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
        String colorCode = "";

        switch(color)
        {
            case "Red" -> colorCode = ColorCodes.RED;
            case "Blue" -> colorCode = ColorCodes.CYAN;
            case "Green" -> colorCode = ColorCodes.GREEN;
            case "Yellow" -> colorCode = ColorCodes.YELLOW;
        }
        return colorCode + number + " " + color + ColorCodes.RESET;
    }


}
