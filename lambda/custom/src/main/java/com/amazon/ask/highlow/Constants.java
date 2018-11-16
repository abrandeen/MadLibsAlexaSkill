package com.amazon.ask.highlow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public static final String FALLBACK_MESSAGE_DURING_GAME = "The High Low Game skill can't help you with that.  Try guessing a number between 0 and 100.";
    public static final String FALLBACK_REPROMPT_DURING_GAME = "Please guess a number between 0 and 100.";
    public static final String FALLBACK_MESSAGE_OUTSIDE_GAME = "The High Low Game skill can't help you with that.  It will come up with a number between 0 and 100 and you try to guess it by saying a number in that range. Would you like to play?";
    public static final String FALLBACK_REPROMPT_OUTSIDE_GAME = "Say yes to start the game or no to quit.";
    public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";


    // Lists of WORD_TYPES and Strings used to construct a MadLib
    // The indices of the two lists align to form one complete MadLib
    public static final ArrayList<ArrayList<MadLib.WORD_TYPE>> WORD_TYPE_LISTS = new ArrayList<>(Arrays.asList(
            // MadLib 0
            new ArrayList<>(Arrays.asList(
                    MadLib.WORD_TYPE.CITY,
                    MadLib.WORD_TYPE.STATE)),
            // MadLib 1
            new ArrayList<>(Arrays.asList(
                    MadLib.WORD_TYPE.FIRST_NAME,
                    MadLib.WORD_TYPE.NUMBER,
                    MadLib.WORD_TYPE.FOOD,
                    MadLib.WORD_TYPE.COLOR,
                    MadLib.WORD_TYPE.SOCIAL_MEDIA,
                    MadLib.WORD_TYPE.ACTOR)),
            // MadLib 2
            new ArrayList<>(Arrays.asList(
                    MadLib.WORD_TYPE.SPORT,
                    MadLib.WORD_TYPE.SPORTS_TEAM,
                    MadLib.WORD_TYPE.WEATHER,
                    MadLib.WORD_TYPE.TV_SERIES,
                    MadLib.WORD_TYPE.MOVIE)),
            // MadLib 3
            new ArrayList<>(Arrays.asList(
                    MadLib.WORD_TYPE.ANIMAL,
                    MadLib.WORD_TYPE.FOOD))));

    public static final ArrayList<String> STORIES_LIST = new ArrayList<>(Arrays.asList(
            // MadLib 0
            "I'm from the city of word0 which is in the state of word1",
            // MadLib 1
            "There once was a boy named word0 who ate word1 pieces of word2. " +
                    "He started to turn word3, but then he posted on word4 and word5 came to save him.",
            // MadLib 2
            "I went to see the word0 game where my favorite team the word1 were playing, " +
                    "but then it started to word2 and the game got cancelled, " +
                    "so I went home to watch word3 and word4.",
            // MadLib 3
            "My favorite animal is word0 and my favorite food is word1."));
}