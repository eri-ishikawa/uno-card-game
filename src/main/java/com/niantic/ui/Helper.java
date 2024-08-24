package com.niantic.ui;

import static com.niantic.application.CardGameApplication.userInput;

public class Helper
{
    public static void waitForUser()
    {
        System.out.println();
        System.out.print("Press ENTER to continue >");
        userInput.nextLine();
    }

    public static String getUserString(String message)
    {
        System.out.print(message);
        return userInput.nextLine().strip();
    }

    public static int getUserInt(String message)
    {
        return Integer.parseInt(getUserString(message));
    }
}
