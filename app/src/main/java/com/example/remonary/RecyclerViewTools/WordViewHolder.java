package com.example.remonary.RecyclerViewTools;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remonary.R;
import com.example.remonary.DataSet.WordElement;

public class WordViewHolder extends RecyclerView.ViewHolder {

    private TextView wordTitle;
    private TextView languageMark;
    private TextView wordTranslate;
    private TextView wordDescription;

    public WordViewHolder(@NonNull View itemView, @NonNull final ViewHolderAction onItemClick) {
        super(itemView);

        wordTitle = itemView.findViewById(R.id.word_title);
        languageMark = itemView.findViewById(R.id.word_language);
        wordTranslate = itemView.findViewById(R.id.word_translate);
        wordDescription = itemView.findViewById(R.id.word_description);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    onItemClick.action(position);
                }
            }
        });
    }

    public void bind(WordElement word){
        wordTitle.setText(word.getTitle());
        languageMark.setText(word.getLanguageMark());
        wordTranslate.setText(word.getTranslate());
        wordDescription.setText(word.getDescription());
    }

}