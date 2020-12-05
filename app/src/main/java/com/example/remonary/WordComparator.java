package com.example.remonary;

import java.util.Comparator;

public class WordComparator implements Comparator<WordElement> {
    @Override
    public int compare(WordElement sourceWord, WordElement targetWord) {
        char[] source = sourceWord.getTitle().toCharArray();
        char[] target = targetWord.getTitle().toCharArray();
        Character sourceLetter;

        int targetHash, sourceHash;
        int i = 0;

        for (Character targetLetter: target){
            sourceLetter = source[i];

            sourceHash = sourceLetter.hashCode();
            targetHash = targetLetter.hashCode();

            if(sourceHash > targetHash)return 1;
            if(sourceHash < targetHash)return -1;
            i++;
        }

        return 0;
    }
}
