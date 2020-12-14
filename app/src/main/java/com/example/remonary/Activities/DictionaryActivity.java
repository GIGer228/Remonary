package com.example.remonary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;
import com.example.remonary.RecyclerViewTools.WordAdapter;
import com.example.remonary.RecyclerViewTools.WordAdapterCallbacks;

import java.io.Serializable;
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

        EditText emptyListMessage = findViewById(R.id.empty_message);

        dictionary = (List<WordElement>) getIntent().getExtras().get(MainActivity.KEY_USERDATA);

        emptyListMessage.setVisibility(dictionary.size() == 0 ? View.VISIBLE : View.GONE);

        adapter = new WordAdapter(this);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("NewApi")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                dictionary.remove(position);
                adapter.notifyItemRemoved(position);
                MainActivity.updateDictionary(dictionary);
            }
        };

        ItemTouchHelper swipeHelper = new ItemTouchHelper(callback);

        RecyclerView wordRecycler = findViewById(R.id.dictionary_recycler);

        wordRecycler.setLayoutManager(new LinearLayoutManager(this));
        wordRecycler.setAdapter(adapter);

        swipeHelper.attachToRecyclerView(wordRecycler);

        adapter.setItems(dictionary);
    }

    @Override
    public void onWordClick(WordElement word) {
        Intent editWordIntent = new Intent(DictionaryActivity.this, WordEditingActivity.class);
        editWordIntent.putExtra(MainActivity.KEY_LAUNCHCODE, 1);
        editWordIntent.putExtra(KEY_CLICKWORD, word);
        startActivityForResult(editWordIntent, RC_EDITWORD);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_EDITWORD && resultCode == Activity.RESULT_OK) {                        //replace old word with user_edit
            WordElement editSource = (WordElement) data.getExtras().get(WordEditingActivity.KEY_EDIT_SOURCE);
            WordElement userEdit = (WordElement) data.getExtras().get(WordEditingActivity.KEY_USER_EDIT);

            dictionary.remove(editSource);
            dictionary.add(userEdit);
            adapter.notifyDataSetChanged();
            MainActivity.updateDictionary(dictionary);
        }else super.onActivityResult(requestCode, resultCode, data);
    }
}