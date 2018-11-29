package com.amazon.ask.highlow;
import static com.amazon.ask.highlow.MadLib.TOPIC;
import static com.amazon.ask.highlow.MadLib.WORD_TYPE;

import java.util.*;
import java.lang.RuntimeException;


//CHANGED MWORDSNEEDED FROM NOT FINAL, TO MAKE THIS TEST WORK, NEED TO FIGURE OUT HOW TO KEEP FINAL

public class MadLibTest{

    public MadLib instance = null;

    public void main(String [] args){
        this.nextWordTypeEnumTest1();
        this.nextWordTypeEnumTest2();
        this.wordGivenTest1();

    }

    public void nextWordTypeEnumTest1(){
        instance.setWordNeededIndex(5);
        int index = instance.getWordNeededIndex();
        ArrayList<WORD_TYPE> lst = new ArrayList<WORD_TYPE>();
        lst.add(WORD_TYPE.STATE);
        instance.setmWordsNeeded(lst);
        if (instance.nextWordTypeEnum() == WORD_TYPE.NONE){
            System.out.println("nextWordTypeEnumTest1 passed");
        }
        else {
            System.out.println("nextWordTypeEnumTest1 failed");
        }
    }

    public void nextWordTypeEnumTest2(){
        ArrayList<WORD_TYPE> lst = new ArrayList<WORD_TYPE>();
        lst.add(WORD_TYPE.CITY);
        lst.add(WORD_TYPE.STATE);
        instance.setWordNeededIndex(0);
        int index = instance.getWordNeededIndex();
        instance.setmWordsNeeded(lst);
        if (instance.nextWordTypeEnum() == lst.get(index)){
            System.out.println("nextWordTypeEnumTest2 passed");
        }
        else {
            System.out.println("nextWordTypeEnumTest2 failed");
        }

    }

    public void wordGivenTest1() {
        ArrayList<WORD_TYPE> lst = new ArrayList<WORD_TYPE>();
        lst.add(WORD_TYPE.CITY);
        lst.add(WORD_TYPE.STATE);
        instance.setWordNeededIndex(0);
        int index = instance.getWordNeededIndex();
        instance.setmWordsNeeded(lst);
        instance.wordGiven("new york");

        if (instance.getWordNeededIndex() == 1 && instance.getUserWords().contains("new york")) {
            System.out.println("WordGivenTest1 passed");
        } else {
            System.out.println("WordGivenTest1 failed");
        }
    }

    public void wordGivenTest2(){
        ArrayList<WORD_TYPE> lst = new ArrayList<WORD_TYPE>();
        instance.setWordNeededIndex(5);
        int index = instance.getWordNeededIndex();
        instance.setmWordsNeeded(lst);
        instance.wordGiven("new york");

        //how do you check if a runtime exception is thrown

    }

}
