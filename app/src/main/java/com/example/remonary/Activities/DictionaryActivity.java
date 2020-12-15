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

import java.util.List;

public class DictionaryActivity extends AppCompatActivity implements WordAdapterCallbacks {

    private WordAdapter adapter;                                                                    //RecyclerView tool
    private List<WordElement> dictionary;                                                           //copy of userDictionary

    public static final int RC_EDITWORD = 1035;                                                     //EditWordActivity's request code(edit word by click)
    public static final String KEY_CLICKWORD = "click_word";                                        //String key for delivering clicked word

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        EditText emptyListMessage = findViewById(R.id.empty_message);

        dictionary = (List<WordElement>) getIntent().getExtras().get(MainActivity.KEY_USERDATA);    //get userDictionary from Intent

        emptyListMessage.setVisibility(dictionary.size() == 0 ? View.VISIBLE : View.GONE);          //if dictionary is empty
                                                                                                    //it'll say about it
        adapter = new WordAdapter(this);

        ItemTouchHelper.SimpleCallback callback =                                                   //swipeHelper's options
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                return false;
            }                                  //just leave it empty

            @SuppressLint("NewApi")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {      //processing swiping words
                int position = viewHolder.getAdapterPosition();                                     //get word position in RecyclerView
                dictionary.remove(position);                                                        //remove swiped word
                adapter.notifyItemRemoved(position);                                                //notify RecyclerView adapter
                MainActivity.updateDictionary(dictionary);                                          //bring changes to MainActivity
            }
        };

        ItemTouchHelper swipeHelper = new ItemTouchHelper(callback);                                //it'll delete words by swipe

        RecyclerView wordRecycler = findViewById(R.id.dictionary_recycler);

        wordRecycler.setLayoutManager(new LinearLayoutManager(this));                        //RecyclerView tools
        wordRecycler.setAdapter(adapter);                                                           //RecyclerView tools

        swipeHelper.attachToRecyclerView(wordRecycler);

        adapter.setItems(dictionary);                                                               //attach userDictionary
    }

    @Override
    public void onWordClick(WordElement word) {                                                     //processing clicking words
        Intent editWordIntent = new Intent(DictionaryActivity.this, WordActivity.class);
        editWordIntent.putExtra(MainActivity.KEY_LAUNCHCODE, 1);                               //set launch code - edit word
        editWordIntent.putExtra(KEY_CLICKWORD, word);                                               //pack clicked word
        startActivityForResult(editWordIntent, RC_EDITWORD);                                        //and launch WordActivity
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_EDITWORD && resultCode == Activity.RESULT_OK) {                        //replace old word with user_edit
            WordElement editSrc = (WordElement)data.getExtras().get(WordActivity.KEY_EDIT_SOURCE);  //get clicked word back
            WordElement userEdit = (WordElement)data.getExtras().get(WordActivity.KEY_USER_EDIT);   //get edited clicked word

            dictionary.remove(editSrc);                                                             //replace old word
            dictionary.add(userEdit);                                                               //with edited by user
            adapter.notifyDataSetChanged();                                                         //notify tools
            MainActivity.updateDictionary(dictionary);                                              //bring changes to MainActivity
        }else super.onActivityResult(requestCode, resultCode, data);
    }
}