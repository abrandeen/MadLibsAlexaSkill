package com.amazon.ask.highlow;

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
            new ArrayList<>(Arrays.asList(MadLib.WORD_TYPE.CITY, MadLib.WORD_TYPE.STATE))));

    public static final ArrayList<String> STORIES_LIST = new ArrayList<>(Arrays.asList(
            "I'm from the city of word0 which is in the state of word1"));
}