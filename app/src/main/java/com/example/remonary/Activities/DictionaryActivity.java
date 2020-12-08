package com.example.remonary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;
import com.example.remonary.RecyclerViewTools.WordAdapter;
import com.example.remonary.RecyclerViewTools.WordAdapterCallbacks;

import java.util.List;

public class DictionaryActivity extends AppCompatActivity implements WordAdapterCallbacks {

    private WordAdapter adapter;
    private List<WordElement> dictionary;

    public static final int RC_EDITWORD = 1035;
    public static final String KEY_CLICKWORD = "click_word";

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
        Intent editWordIntent = new Intent(DictionaryActivity.this, WordEditingActivity.class);
        editWordIntent.putExtra(MainActivity.KEY_LAUNCHCODE, 1);
        editWordIntent.putExtra(KEY_CLICKWORD, word);
        startActivityForResult(editWordIntent, RC_EDITWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_EDITWORD && resultCode == Activity.RESULT_OK) {
                                                                                                    //replace old word with user_edit
        }else super.onActivityResult(requestCode, resultCode, data);
    }
}