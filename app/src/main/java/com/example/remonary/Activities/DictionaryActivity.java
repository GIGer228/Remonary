package com.example.remonary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;
import com.example.remonary.RecyclerViewTools.WordAdapter;
import com.example.remonary.RecyclerViewTools.WordAdapterCallbacks;

import java.util.List;

public class DictionaryActivity extends AppCompatActivity implements WordAdapterCallbacks {

    private WordAdapter adapter;
    private List<WordElement> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        dictionary = (List<WordElement>) getIntent().getExtras().get(MainActivity.KEY_USERDATA);

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