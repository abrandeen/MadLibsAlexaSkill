package com.amazon.ask.highlow;

import javax.script.SimpleBindings;
import java.lang.RuntimeException;
import java.util.*;


public class MadLib{

    private static MadLib instance = null;

    // Represents topic of the MadLib
    public enum TOPIC {VANDERBILT, SPORTS, MEDIA, NATURE, KIDS, RANDOM, TRAVEL};

    // Map of String from the custom Topic slot values as Strings to their corresponding TOPIC enum values
    // @InspiredBy: https://stackoverflow.com/questions/507602/how-can-i-initialise-a-static-map
    public static final Map<String, TOPIC> TOPIC_STRING_MAP;
    static {
        Map<String, TOPIC> mMap = new HashMap<>();
        mMap.put("vanderbilt", TOPIC.VANDERBILT);
        mMap.put("Vanderbilt", TOPIC.VANDERBILT);
        mMap.put("sports", TOPIC.SPORTS);
        mMap.put("media", TOPIC.MEDIA);
        mMap.put("nature", TOPIC.NATURE);
        mMap.put("kids", TOPIC.KIDS);
        mMap.put("random", TOPIC.RANDOM);
        mMap.put("travel", TOPIC.TRAVEL);
        TOPIC_STRING_MAP = Collections.unmodifiableMap(mMap);
    }
    // @EndInspiredBy

    // Represents types of words
    public enum WORD_TYPE {NONE, CITY, STATE, ACTOR, NUMBER, FOOD, ANIMAL,
        COLOR, MOVIE, SOCIAL_MEDIA, SPORT, SPORTS_TEAM, TV_SERIES, FIRST_NAME,
        WEATHER, PROFESSOR};

    // Map of WORD_TYPEs to their corresponding Strings
    // @InspiredBy: https://stackoverflow.com/questions/507602/how-can-i-initialise-a-static-map
    public static final Map<WORD_TYPE, String> WORD_TYPE_STRING_MAP;
    static {
        EnumMap<WORD_TYPE, String> mMap = new EnumMap<WORD_TYPE,String>(WORD_TYPE.class);
        mMap.put(WORD_TYPE.CITY, "city");
        mMap.put(WORD_TYPE.STATE, "state");
        mMap.put(WORD_TYPE.ACTOR, "actor");
        mMap.put(WORD_TYPE.NUMBER, "number");
        mMap.put(WORD_TYPE.FOOD, "food");
        mMap.put(WORD_TYPE.ANIMAL, "animal");
        mMap.put(WORD_TYPE.COLOR, "color");
        mMap.put(WORD_TYPE.MOVIE, "movie");
        mMap.put(WORD_TYPE.SOCIAL_MEDIA , "social media platform");
        mMap.put(WORD_TYPE.SPORT, "sport");
        mMap.put(WORD_TYPE.SPORTS_TEAM, "sports team");
        mMap.put(WORD_TYPE.TV_SERIES, "tv series");
        mMap.put(WORD_TYPE.FIRST_NAME, "first name");
        mMap.put(WORD_TYPE.WEATHER, "weather condition");
        mMap.put(WORD_TYPE.PROFESSOR, "Vanderbilt computer science professor");
        WORD_TYPE_STRING_MAP = Collections.unmodifiableMap(mMap);
    }
    // @EndInspiredBy

    // List of WordTypes needed to complete the MadLib
    // in order from first needed to last
    private ArrayList<WORD_TYPE> mWordsNeeded;

    // Index of the wordType needed next in the wordsNeeded List
    private int wordNeededIndex;

    // List containing the words the user has said to use in the MadLib
    // in order from first needed to last
    private ArrayList<String> userWords;

    // String containing the MadLib story with "blanks"
    // represented by "word#" with # being the index of the needed word
    private String mStory;

    /**
     * Constructor
     * creates an instance of the MadLib class with the specified fields
     * @param wordsNeeded
     * @param story
     */
    private MadLib(ArrayList<WORD_TYPE> wordsNeeded, String story){
        mWordsNeeded = new ArrayList<WORD_TYPE>(wordsNeeded);
        wordNeededIndex = 0;
        userWords = new ArrayList<String>();
        mStory = story;
    }

    /**
     *
     * @return the static instance of the MadLib singleton
     */
    public static MadLib getInstance(){
        if (instance == null)
            return newMadLibRandom();

        return instance;
    }

    /**
     * Changes MadLib instance to a new random MadLib
     * @return the new random MadLib
     */
    public static MadLib newMadLibRandom(){
        int index = new Random().nextInt(Constants.STORIES_LIST.size());
        instance = new MadLib
                (Constants.WORD_TYPE_LISTS.get(index), Constants.STORIES_LIST.get(index));
        return instance;
    }

    /**
     * Changes MadLib instance to a new MadLib of the given topic
     * @return the new MadLib
     */
    public static MadLib newMadLibTopic(TOPIC topic){
        if (topic == TOPIC.RANDOM){
            return newMadLibRandom();
        }

        // Start looking for a MadLib with the specified topic at random startIndex
        int numberOfStories = Constants.STORIES_LIST.size();
        int startIndex = new Random().nextInt(numberOfStories);

        // Return the first MadLib with the given topic
        for (int i = 0; i < numberOfStories; i++){
            int index = (startIndex + i) % numberOfStories;
            if (Constants.TOPICS_LIST.get(index) == topic){
                instance = new MadLib
                        (Constants.WORD_TYPE_LISTS.get(index), Constants.STORIES_LIST.get(index));
                return instance;
            }
        }

        // No MadLib exists with the current topic
        throw new IllegalStateException("No MadLib exists with the given topic");
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
            mStory = mStory.replaceAll(replace, userWords.get(i));
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
     * Handles the user adding the needed word to the MadLib
     * @param word -- the word(s) the user just gave for the needed word type
     * @throws RuntimeException
     */
    public void wordGiven(String word) throws RuntimeException{
        // If the user has already given all needed words
        if (wordNeededIndex >= mWordsNeeded.size())
            throw new RuntimeException("All words have already been given");

        // Add the user's word to the list and increment the index to the next needed word
        userWords.add(word);
        wordNeededIndex++;
    }

    //Get and Set methods used for testing

    public void setWordNeededIndex(int index){
        this.wordNeededIndex = index;
    }

    public int getWordNeededIndex(){
        return this.wordNeededIndex;
    }

    public void setmWordsNeeded(ArrayList<WORD_TYPE> lst){
        this.mWordsNeeded = lst;

    }

    public ArrayList<WORD_TYPE> getmWordNeeded(){
        return this.mWordsNeeded;
    }

    public ArrayList<String> getUserWords() {
        return this.userWords;
    }

    public void setmStory(String story){
        this.mStory = story;
    }
    public String getmStory(){
        return this.mStory;
    }

    public void setuserWords(ArrayList<String> lst){
        this.userWords = lst;
    }
    public ArrayList<String> getuserWords(){
        return this.userWords;
    }
}