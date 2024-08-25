package com.niantic.models;

import com.niantic.models.cards.Card;
import com.niantic.ui.ColorCodes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest
{
    @Test
    public void toString_shouldReturnCardWithNumberAndColorAndCorrectColor()
    {
        // arrange
        String expectedOutput = ColorCodes.CYAN + "8 Blue" + ColorCodes.RESET;

        // act
        Card card = new Card("Blue", 8);
        String actualOutput = card.toString();

        // assert
        assertEquals(expectedOutput, actualOutput, "Because it should output '8 Blue' as the string representation.");
    }
}