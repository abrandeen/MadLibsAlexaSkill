package com.amazon.ask.highlow;
import static com.amazon.ask.highlow.MadLib.TOPIC;
import static com.amazon.ask.highlow.MadLib.WORD_TYPE;
import static com.amazon.ask.highlow.MadLib.getInstance;

import java.util.*;
import java.lang.RuntimeException;

//look at topic for sports


public class MadLibTest{


    public static void main(String[] args){

        //initializing the mWordsNeeded for instance1
        ArrayList<WORD_TYPE> lst1 = new ArrayList<WORD_TYPE>();
        lst1.add(WORD_TYPE.STATE);
        MadLib instance1 = MadLib.getInstance();
        instance1.setmWordsNeeded(lst1);

        //initializing the mWordsNeeded for instance2
        ArrayList<WORD_TYPE> lst2 = new ArrayList<WORD_TYPE>();
        lst2.add(WORD_TYPE.CITY);
        lst2.add(WORD_TYPE.STATE);
        MadLib instance2 = MadLib.getInstance();
        instance2.setmStory("I am from word0 in word1");

        //initializing the userWords for instance2
        ArrayList<String> words = new ArrayList<String>();
        words.add("nashville");
        words.add("tennessee");
        instance2.setuserWords(words);
        instance2.setmWordsNeeded(lst2);

        //run tests
        nextWordTypeEnumTest1(instance1);
        nextWordTypeEnumTest2(instance2, lst2);
        wordGivenTest1(instance2);
        createStoryTest1(instance2);
    }

    /**
     * Tests nextWordTypeEnum() for the case when wordNeededIndex is equal to or
     * greater than the size of mWordsNeeded
     */
    public static void nextWordTypeEnumTest1(MadLib instance){
        instance.setWordNeededIndex(5);

        if (instance.nextWordTypeEnum() == WORD_TYPE.NONE){
            System.out.println("nextWordTypeEnum: Test1 passed");
        }
        else {
            System.out.println("nextWordTypeEnum: Test1 failed");
        }
    }

    /**
     * Tests nextWordTypeEnum() for the case when wordNeededIndex less than
     * mWordsNeeded to see if it returns the type of word needed next to fill
     * the Mad Lib
     */
    public static void nextWordTypeEnumTest2(MadLib instance, ArrayList<WORD_TYPE> lst){

        instance.setWordNeededIndex(0);
        int index = instance.getWordNeededIndex();

        if (instance.nextWordTypeEnum() == lst.get(index)){
            System.out.println("nextWordTypeEnum(): Test2 passed");
        }
        else {
            System.out.println("nextWordTypeEnum(): Test2 failed");
        }

    }

    /**
     * Tests wordGiven() to see if a word is correctly added to
     * userWords and that wordNeededIndex is incremented
     */
    public static void wordGivenTest1(MadLib instance) {
        instance.setWordNeededIndex(0);
        int index = instance.getWordNeededIndex();
        instance.wordGiven("new york");

        if (instance.getWordNeededIndex() == 1 && instance.getuserWords().contains("new york")) {
            System.out.println("wordGiven(): Test1 passed");
        } else {
            System.out.println("wordGiven(): Test1 failed");
        }
    }

    /**
     * Tests createStory() to see if it correctly returns a
     * MadLib by replacing "word" + i (ex. word0) with a user's
     * word
     */
    public static void createStoryTest1 (MadLib instance){
        instance.setWordNeededIndex(2);
        if (instance.createStory().equals("I am from nashville in tennessee")){
            System.out.println("createStory(): Test1 passed");
        } else {
            System.out.println("createStory(): Test1 failed");
        }
    }
}
