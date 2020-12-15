package com.example.remonary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.remonary.DataSet.WordComparator;
import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<WordElement> userDictionary = new ArrayList<>();//all the words added by user
    private static WordComparator wordComparator;//dictionary sorter

    public static final int RC_ADDNEWWORD = 1030;//EditWordActivity's request code (add words)
    public static final int RC_SEEDICTIONARY = 1090;//DictionaryActivity's request code (show words)

    public static final String KEY_USERDATA = "user_data";//String key for delivering words between Activities
    public static final String KEY_LAUNCHCODE = "launch_code";//String key for EditActivity launch code (add/edit word)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeLine = findViewById(R.id.welcome_line);

        Button newWordButton = findViewById(R.id.new_word_button);
        Button dictionaryButton = findViewById(R.id.dictionary_button);
        Button trainButton = findViewById(R.id.train_button);
        Button optionsButton = findViewById(R.id.options_button);
        Button exitButton = findViewById(R.id.exit_button);

        wordComparator = new WordComparator();

        newWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newWordIntent = new Intent(MainActivity.this, WordEditingActivity.class);
                newWordIntent.putExtra(KEY_LAUNCHCODE, 0);//EditWordActivity's add word case
                startActivityForResult(newWordIntent, RC_ADDNEWWORD);//launch EditWordActivity
            }
        });

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(MainActivity.this, DictionaryActivity.class);
                dictionaryIntent.putExtra(KEY_USERDATA, (Serializable) userDictionary);//pack userDictionary
                startActivityForResult(dictionaryIntent, RC_SEEDICTIONARY);//launch DictionaryActivity with userDictionary
            }
        });

        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_ADDNEWWORD && resultCode == Activity.RESULT_OK) {
            WordElement userWord = (WordElement) data.getExtras().get(WordEditingActivity.KEY_NEW_WORD);//get new word from EditWordActivity

            userDictionary.add(userWord);//add new word to userDictionary
            userDictionary.sort(wordComparator);//sort new word
        }else
            if (requestCode == RC_SEEDICTIONARY && resultCode == Activity.RESULT_OK){
                List<WordElement> editedDictionary = (List<WordElement>) data.getExtras().get(KEY_USERDATA);//get edited words from DictionaryActivity
                userDictionary = editedDictionary;//set new Dictionary
                userDictionary.sort(wordComparator);////sort new Dictionary
            }else super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("NewApi")
    public static void updateDictionary(List<WordElement> newData){//updating userDictionary from other Activities
        userDictionary = newData;//only for deleting in DictionaryActivity
        userDictionary.sort(wordComparator);//do not use it anywhere
    }
}
