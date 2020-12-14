package com.example.remonary.RecyclerViewTools;

import androidx.recyclerview.widget.DiffUtil;

import com.example.remonary.DataSet.WordElement;

import java.util.List;

public class WordDiffCallback extends DiffUtil.Callback {

    private List<WordElement> oldWords, newWords;

    public WordDiffCallback(List<WordElement> oldWords, List<WordElement> newWords) {
        this.oldWords = oldWords;
        this.newWords = newWords;
    }

    @Override
    public int getOldListSize() {
        return oldWords.size();
    }

    @Override
    public int getNewListSize() {
        return newWords.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWords.get(oldItemPosition) == newWords.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWords.get(oldItemPosition).equals(newWords.get(newItemPosition));
    }
}
