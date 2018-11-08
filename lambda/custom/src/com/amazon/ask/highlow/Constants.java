package com.amazon.ask.highlow;

public class Constants {
    public static final String FALLBACK_MESSAGE_DURING_GAME = "The High Low Game skill can't help you with that.  Try guessing a number between 0 and 100.";
    public static final String FALLBACK_REPROMPT_DURING_GAME = "Please guess a number between 0 and 100.";
    public static final String FALLBACK_MESSAGE_OUTSIDE_GAME = "The High Low Game skill can't help you with that.  It will come up with a number between 0 and 100 and you try to guess it by saying a number in that range. Would you like to play?";
    public static final String FALLBACK_REPROMPT_OUTSIDE_GAME = "Say yes to start the game or no to quit.";
    public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";


    // MadLibs
    public static final MadLib CITY_STATE_MAD_LIB = new MadLib(
            new ArrayList<WORD_TYPE>(Arrays.asList({MadLib.WORD_TYPE.CITY, MadLib.WORD_TYPE.STATE})),
            "I'm from the city of word0 which is in the state of word1");

    public static final List<MadLib> MAD_LIB_LIST = new ArrayList<MadLib>(Arrays.asList(
            {CITY_STATE_MAD_LIB}));
}