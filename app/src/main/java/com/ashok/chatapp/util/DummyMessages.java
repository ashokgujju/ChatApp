package com.ashok.chatapp.util;

import com.ashok.chatapp.data.Message;

import java.util.Random;

/**
 * Created by ashok on 22/11/17.
 */

public class DummyMessages {
    private static Random random = new Random();

    private static String[] messages = {"Hii, how are you?",
            "How’s it going?",
            "It’s been a while",
            "Yo!"};

    public static Message getRandomMessage() {
        return new Message(messages[getRandomIndex()],
                AppConstants.OTHER, AppConstants.ME);
    }

    private static int getRandomIndex() {
        return random.nextInt(messages.length);
    }
}