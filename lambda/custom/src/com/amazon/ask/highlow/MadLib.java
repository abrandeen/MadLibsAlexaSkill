package com.amazon.ask.highlow;

import Java.util;
import Java.lang;

public class MadLib{

    // WHERE SHOULD I PUT THIS DECLARATION??????
    // Represents types of words
    static enum WordType {NONE, CITY, STATE};

    // List of WordTypes needed to complete the MadLib
    // in order from first needed to last
    final ArrayList<WordType> wordsNeeded;

    // Index of the wordType needed next in the wordsNeeded List
    int wordNeededIndex;

    // List contaning the words the user has said to use in the MadLib
    // in order from first needed to last
    ArrayList<String> userWords;

    // String containing the MadLib story with "blanks"
    // represnted by "word#" with # being the index of the needed word
    String story;

    /**
     * Contrustor
     * creates an instance of the MadLib class with the specified fields
     * @param wordsNeeded
     * @param story
     */
    public MadLib(List<WordType> wordsNeeded, String story):
        wordsNeeded(wordsNeeded),
        wordNeededIndex(0),
        story(story) {

        userWords = new ArrayList();
    }

    /**
     * Copy Constructor
     * creates a new instance of the MadLib class with fields that are deep copies
     * of the field of the given MadLib
     * @param original -- the MadLib to be copied
     */
    public MadLib(MadLib original):
        wordsNeeded(original.wordsNeeded),
        wordNeededIndex(original.wordNeededIndex),
        userWords(original.userWords),
        story(original.story){
    }

    /**
     * Inputs userWords into the story if the user has given all required words
     * @return the complete story
     */
    public String createStory(){

        // If not all words have been asked for
        if (wordNeededIndex < wordsNeeded.size()){
            // IS THIS AN OKAY TYPE OF EXCEPTION TO THROW???
            throw new RuntimeException("Tried to create the story when not all words have been collected");
        }

        // Add each of the word's given by the user to the story
        for (int i = 0; i < wordsNeeded.size(); i++){
            String replace = "word" + i;
            story.replaceFirst(replace, userWords.get(i));
        }
        return story;
    }

    /**
     * Returns the type of word needed next to fill the Mad Lib
     * @return element of wordsNeeded array at the index wordNeededIndex
     */
    public WordType nextWordType(){

        // If all words have been asked for
        if (wordNeededIndex >= wordsNeeded.size())
            return WordType.NONE;

        wordsNeeded.get(wordNeededIndex);
    }

    /**
     *
     * @param word -- the word(s) the user just gave for the needed word type
     * @throws RuntimeException
     */
    public static wordGiven(String word) throws RuntimeException{
        // If the user has already given all needed words
        if (wordNeededIndex >= wordsNeeded.size())
            throw new RuntimeExeception("All words have already been given");

        // Add the user's word to the list and increment the index to the next needed word
        userWords.add(word);
        wordNeededIndex++;
    }
}