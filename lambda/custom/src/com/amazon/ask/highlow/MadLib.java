package com.amazon.ask.highlow;

import java.util.EnumMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.RuntimeException;

public class MadLib{

    // Represents types of words
    enum WORD_TYPE {NONE, CITY, STATE};

    // Map of WordTypes to their corresponding Strings
    // @InspiredBy: https://stackoverflow.com/questions/507602/how-can-i-initialise-a-static-map
    static final Map<WORD_TYPE, String> WORD_TYPE_STRING_MAP;
    static {
        EnumMap<WORD_TYPE, String> mMap = new EnumMap(WORD_TYPE.class);
        mMap.put(WORD_TYPE.CITY, "city");
        mMap.put(WORD_TYPE.STATE, "state");
        WORD_TYPE_STRING_MAP = Collections.unmodifiableMap(mMap);
    }
    // @EndInspiredBy

    // List of WordTypes needed to complete the MadLib
    // in order from first needed to last
    private final ArrayList<WORD_TYPE> mWordsNeeded;

    // Index of the wordType needed next in the wordsNeeded List
    private int wordNeededIndex;

    // List contaning the words the user has said to use in the MadLib
    // in order from first needed to last
    private ArrayList<String> userWords;

    // String containing the MadLib story with "blanks"
    // represnted by "word#" with # being the index of the needed word
    private String mStory;

    /**
     * Contrustor
     * creates an instance of the MadLib class with the specified fields
     * @param wordsNeeded
     * @param story
     */
    public MadLib(List<WORD_TYPE> wordsNeeded, String story){

        mWordsNeeded = new ArrayList<>(wordsNeeded);
        wordNeededIndex = 0;
        userWords = new ArrayList();
        mStory = story;
    }

    /**
     * Copy Constructor
     * creates a new instance of the MadLib class with fields that are deep copies
     * of the field of the given MadLib
     * @param original -- the MadLib to be copied
     */
    public MadLib(MadLib original){
        mWordsNeeded = original.mWordsNeeded;
        wordNeededIndex = original.wordNeededIndex;
        userWords = original.userWords;
        mStory = original.mStory;
    }

    /**
     * Inputs userWords into the story if the user has given all required words
     * @return the complete story
     */
    public String createStory(){

        // If not all words have been asked for
        if (wordNeededIndex < mWordsNeeded.size()){
            // IS THIS AN OKAY TYPE OF EXCEPTION TO THROW???
            throw new RuntimeException("Tried to create the story when not all words have been collected");
        }

        // Add each of the word's given by the user to the story
        for (int i = 0; i < mWordsNeeded.size(); i++){
            String replace = "word" + i;
            mStory.replaceFirst(replace, userWords.get(i));
        }
        return mStory;
    }

    /**
     * Returns the type of word needed next to fill the Mad Lib
     * @return element of wordsNeeded array at the index wordNeededIndex
     */
    public WORD_TYPE nextWordTypeEnum(){

        // If all words have been asked for
        if (wordNeededIndex >= mWordsNeeded.size())
            return WORD_TYPE.NONE;

        return mWordsNeeded.get(wordNeededIndex);
    }

    /**
     * Returns the type of word needed next to fill the Mad Lib as a string
     * @return string that corresponds to the element of wordsNeeded array at
     *  the index wordNeededIndex in the WORD_TYPE_STRING_ENUM_MAP
     */
    public String nextWordTypeString(){
        return WORD_TYPE_STRING_MAP.get(nextWordTypeEnum());
    }

    /**
     *
     * @param word -- the word(s) the user just gave for the needed word type
     * @throws RuntimeException
     */
    public void wordGiven(String word) throws RuntimeException{
        // If the user has already given all needed words
        if (wordNeededIndex >= mWordsNeeded.size())
            throw new RuntimeExeception("All words have already been given");

        // Add the user's word to the list and increment the index to the next needed word
        userWords.add(word);
        wordNeededIndex++;
    }
}