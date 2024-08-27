package com.niantic.models.cards;

import com.niantic.ui.ColorCodes;

public class ActionCard extends Card
{
    private String actionType;

    public ActionCard(String color, String actionType)
    {
        super(color, -1);
        this.actionType = actionType;
    }

    public String getActionType()
    {
        return actionType;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    @Override
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
        return colorCode + actionType + " " + color + ColorCodes.RESET;
    }
}
