package com.amazon.ask.highlow;

import java.util.ArrayList;
import java.util.Arrays;
import static com.amazon.ask.highlow.MadLib.WORD_TYPE;
import static com.amazon.ask.highlow.MadLib.TOPIC;

public class Constants {
    public static final String FALLBACK_MESSAGE_DURING_GAME = "Please give the requested word type.";
    public static final String FALLBACK_REPROMPT_DURING_GAME = "Please give the requested word type.";
    public static final String FALLBACK_MESSAGE_OUTSIDE_GAME = "Please give the requested word type. Would you like to play?";
    public static final String FALLBACK_REPROMPT_OUTSIDE_GAME = "Say sure to start the game or no to quit.";
    public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";


    // Lists of WORD_TYPEs used to construct each MadLib
    // The indices of this list correspond to the indices of the STORIES_LIST and the TOPICS_LIST
    // so that when the same index is passed the MadLib constructor it forms one complete MadLib
    public static final ArrayList<ArrayList<WORD_TYPE>> WORD_TYPE_LISTS = new ArrayList<>(Arrays.asList(
            // MadLib 0
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.CITY,
                    WORD_TYPE.STATE)),
            // MadLib 1
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.NUMBER,
                    WORD_TYPE.FOOD,
                    WORD_TYPE.COLOR,
                    WORD_TYPE.SOCIAL_MEDIA,
                    WORD_TYPE.ACTOR)),
            // MadLib 2
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.SPORT,
                    WORD_TYPE.SPORTS_TEAM,
                    WORD_TYPE.WEATHER,
                    WORD_TYPE.TV_SERIES,
                    WORD_TYPE.MOVIE)),
            // MadLib 3
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.ANIMAL,
                    WORD_TYPE.FOOD)),
            // MadLib 4
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.PROFESSOR,
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.NUMBER,
                    WORD_TYPE.SOCIAL_MEDIA,
                    WORD_TYPE.ACTOR,
                    WORD_TYPE.MOVIE,
                    WORD_TYPE.FOOD,
                    WORD_TYPE.ANIMAL,
                    WORD_TYPE.CITY,
                    WORD_TYPE.STATE)),
            // MadLib 5
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.CITY,
                    WORD_TYPE.ANIMAL,
                    WORD_TYPE.COLOR,
                    WORD_TYPE.ACTOR,
                    WORD_TYPE.WEATHER,
                    WORD_TYPE.MOVIE)),
            // MadLib 6
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.CITY,
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.FOOD,
                    WORD_TYPE.MOVIE)),
            // MadLib 7
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.COLOR,
                    WORD_TYPE.COLOR,
                    WORD_TYPE.SPORT,
                    WORD_TYPE.FOOD,
                    WORD_TYPE.WEATHER)),
            // MadLib 8
            new ArrayList<>(Arrays.asList(
                    WORD_TYPE.FIRST_NAME,
                    WORD_TYPE.CITY,
                    WORD_TYPE.CITY,
                    WORD_TYPE.CITY,
                    WORD_TYPE.ACTOR,
                    WORD_TYPE.NUMBER,
                    WORD_TYPE.FOOD,
                    WORD_TYPE.WEATHER,
                    WORD_TYPE.ANIMAL))
    ));

    // List of Strings used to construct each MadLib
    // The indices of this list correspond to the indices of the WORD_TYPES_LISTS and the TOPICS_LIST
    // so that when the same index is passed the MadLib constructor it forms one complete MadLib
    public static final ArrayList<String> STORIES_LIST = new ArrayList<>(Arrays.asList(
            // MadLib 0 - Travel
            "I'm from the city of word0 which is in the state of word1.",
            // MadLib 1 - Media
            "There once was a boy named word0 who ate word1 pieces of word2. " +
                    "He started to turn word3, but then he posted on word4 and word5 came to save him.",
            // MadLib 2 - Sports
            "I went to see the word0 game where my favorite team the word1 were playing, " +
                    "but then it started to word2 and the game got cancelled, " +
                    "so I went home to watch word3 and word4.",
            // MadLib 3 - Kids
            "My favorite animal is word0 and my favorite food is word1.",
            // MadLib 4 - Vanderbilt
            "word0 assigned a gnarly programming assignment that would probably take word1 word2 hours. " +
                    "word1 tried to find answers for it on word3. " +
                    "But then word1 saw that word4 from word5 had provided the solution on Github. " +
                    "To celebrate the victory, word1 went to Rand to get word6 but he found a word7 in his word6. " +
                    "Luckily, word1 is going home to word8 in word9 soon.",
            // MadLib 5 - Nature
            "word0 went hiking in word1 hoping to see some word2. " +
                    "word0 arrived in the forrest to see some word3 leaves, " +
                    "but instead of seeing a word2 word0 saw word4. " +
                    "Then, it started to word5, so word0 and word4 went to go see word6.",
            // MadLib 6 - Kids
            "There once was a girl named word0 who lived in word1. word0 had a little brother named word2 " +
                    "who loved word3, so when their mom asked them what vegetable they wanted for " +
                    "dinner word2 answered word3. But word0 hated word3! So word0 and word2 compromised " +
                    "that after dinner they'd watch word0's favorite movie, word4.",
            // MadLib 7 - Kids
            "word0 was walking into her first day of second grade in her new word1 top and word2 pants. " +
                    "She also had her first word3 practice after school, so it was a very big day. After " +
                    "school, her dad made her a word4 for a snack. It started to word5 so it was a beautiful " +
                    "day to be outside for word3 practice.",
            // MadLib 8 - Travel
            "word0 was flying from word1 to word2. On his layover in word3, he saw word4 ordering word5 word6 " +
                    "from Starbucks. Turns out word4 was on his flight! When the plane got delayed due to the " +
                    "word7, word0 got the courage to introduce himself to word4 and they got to talking about " +
                    "their love of word8."
             ));

    // Lists of TOPICs used to construct each MadLib
    // The indices of this list correspond to the indices of the WORD_TYPES_LISTS and the STORIES_LIST
    // so that when the same index is passed the MadLib constructor it forms one complete MadLib
    public static final ArrayList<TOPIC> TOPICS_LIST = new ArrayList<>(Arrays.asList(
            // MadLib 0
            TOPIC.TRAVEL,
            // MadLib 1
            TOPIC.MEDIA,
            // MadLib 2
            TOPIC.SPORTS,
            // MadLib 3
            TOPIC.KIDS,
            // MadLib 4
            TOPIC.VANDERBILT,
            // MadLib 5
            TOPIC.NATURE,
            // MadLib 6
            TOPIC.KIDS,
            // MadLib 7
            TOPIC.KIDS,
            // MadLib 8
            TOPIC.TRAVEL
    ));
}