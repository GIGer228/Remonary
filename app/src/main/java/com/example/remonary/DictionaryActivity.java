package com.example.remonary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class DictionaryActivity extends AppCompatActivity implements WordAdapterCallbacks{

    private WordAdapter adapter;
    private List<WordElement> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        adapter = new WordAdapter(this);

        RecyclerView wordRecycler = findViewById(R.id.dictionary_recycler);

        wordRecycler.setLayoutManager(new LinearLayoutManager(this));
        wordRecycler.setAdapter(adapter);

        adapter.setItems(dictionary);
    }

    @Override
    public void onWordClick(WordElement word) {

    }
}