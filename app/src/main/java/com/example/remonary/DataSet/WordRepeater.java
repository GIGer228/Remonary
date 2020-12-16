package com.example.remonary.DataSet;

public class WordRepeater {

    WordElement badWord;                                                                            //the worst repeats

    long repeatCount;                                                                               //statistics parameter

    public WordRepeater() {
        this.badWord = null;
        this.repeatCount = 0;
    }

    public WordElement getBadWord() {
        return badWord;
    }
    public void setBadWord(WordElement badWord) {
        this.badWord = badWord;
    }
    public long getRepeatCount() {
        return repeatCount;
    }
    public void setRepeatCount(long repeatCount) {
        this.repeatCount = repeatCount;
    }

    public void repeatWord(WordElement target){



    }
}
