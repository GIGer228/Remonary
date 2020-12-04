package com.example.remonary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder {

    private TextView wordTitle;
    private TextView wordTranslate;
    private TextView wordDescription;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);

        wordTitle = itemView.findViewById(R.id.word_title);
        wordTranslate = itemView.findViewById(R.id.word_translate);
        wordDescription = itemView.findViewById(R.id.word_description);
    }

    public void bind(WordElement word){
        wordTitle.setText(word.getTitle());
        wordTranslate.setText(word.getTranslate());
        wordDescription.setText(word.getDescription());
    }

}