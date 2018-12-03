package com.amazon.ask.highlow;
import static com.amazon.ask.highlow.MadLib.TOPIC;
import static com.amazon.ask.highlow.MadLib.WORD_TYPE;
import static com.amazon.ask.highlow.MadLib.getInstance;

import java.util.*;
import java.lang.RuntimeException;



public class MadLibTest{


    public static void main(String[] args){
        ArrayList<WORD_TYPE> lst1 = new ArrayList<WORD_TYPE>();
        lst1.add(WORD_TYPE.STATE);
        MadLib instance1 = MadLib.getInstance();
        instance1.setmWordsNeeded(lst1);


        ArrayList<WORD_TYPE> lst2 = new ArrayList<WORD_TYPE>();
        lst2.add(WORD_TYPE.CITY);
        lst2.add(WORD_TYPE.STATE);
        MadLib instance2 = MadLib.getInstance();
        instance2.setmStory("I am from word0 in word1");
        String story = instance2.getmStory();
        ArrayList<String> words = new ArrayList<String>();
        words.add("nashville");
        words.add("tennessee");
        instance2.setuserWords(words);
        instance2.setmWordsNeeded(lst2);


        nextWordTypeEnumTest1(instance1);
        nextWordTypeEnumTest2(instance2, lst2);
        wordGivenTest1(instance2);
        createStoryTest1(instance2);

    }

    public static void nextWordTypeEnumTest1(MadLib instance){
        instance.setWordNeededIndex(5);

        if (instance.nextWordTypeEnum() == WORD_TYPE.NONE){
            System.out.println("nextWordTypeEnum: Test1 passed");
        }
        else {
            System.out.println("nextWordTypeEnum: Test1 failed");
        }
    }

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

    public static void wordGivenTest1(MadLib instance) {
        instance.setWordNeededIndex(0);
        int index = instance.getWordNeededIndex();
        instance.wordGiven("new york");

        if (instance.getWordNeededIndex() == 1 && instance.getUserWords().contains("new york")) {
            System.out.println("wordGiven(): Test1 passed");
        } else {
            System.out.println("wordGiven(): Test1 failed");
        }
    }

    public static void createStoryTest1 (MadLib instance){
        instance.setWordNeededIndex(2);
        if (instance.createStory().equals("I am from nashville in tennessee")){
            System.out.println("createStory(): Test1 passed");
        } else {
            System.out.println("createStory(): Test1 failed");
        }
    }
}
