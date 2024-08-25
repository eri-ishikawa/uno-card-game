package com.niantic.models.cards;

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

    @Override
    public String toString()
    {
        return actionType + " " + color;
    }
}
