package com.niantic.models.cards;

import com.niantic.ui.ColorCodes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionCardTest
{
    @Test
    public void toString_shouldReturnCardWithNumberAndColorAndCorrectColor()
    {
        // arrange
        String expectedOutput = ColorCodes.CYAN + "Skip Blue" + ColorCodes.RESET;

        //act
        Card card = new ActionCard("Blue", "Skip");
        String actualOutput = card.toString();

        // assert
        assertEquals(expectedOutput, actualOutput, "Because it should output 'Skip Blue' as the string representation.");
    }
}