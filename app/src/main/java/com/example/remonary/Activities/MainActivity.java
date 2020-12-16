package com.example.remonary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.remonary.DataSet.RemonaryDataBase;
import com.example.remonary.DataSet.WordComparator;
import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RemonaryDataBase memory = new RemonaryDataBase(this);
    private static List<WordElement> userDictionary = new ArrayList<>();                            //all the words added by user
    private static WordComparator wordComparator;                                                   //dictionary sorter

    public static final int RC_ADDNEWWORD = 1030;                                                   //EditWordActivity's request code (add words)
    public static final int RC_TRAINWORD = 1050;

    public static final String KEY_USERDATA = "user_data";                                          //String key for delivering words between Activities
    public static final String KEY_LAUNCHCODE = "launch_code";                                      //String key for EditActivity launch code (add/edit word)
    public static final String KEY_REPEAT = "repeat_word";

    Random random = new Random();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeLine = findViewById(R.id.welcome_line);                                     //little bonus at start

        Button newWordButton = findViewById(R.id.new_word_button);
        Button dictionaryButton = findViewById(R.id.dictionary_button);
        Button trainButton = findViewById(R.id.train_button);
        Button optionsButton = findViewById(R.id.options_button);
        Button exitButton = findViewById(R.id.exit_button);

        wordComparator = new WordComparator();

        userDictionary = memory.getAllWords();
        userDictionary.sort(wordComparator);

        newWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newWordIntent = new Intent(MainActivity.this, WordActivity.class);
                newWordIntent.putExtra(KEY_LAUNCHCODE, 0);                                    //set launch code - add word
                startActivityForResult(newWordIntent, RC_ADDNEWWORD);                               //and launch EditWordActivity
            }
        });

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(MainActivity.this, DictionaryActivity.class);
                dictionaryIntent.putExtra(KEY_USERDATA, (Serializable) userDictionary);             //pack userDictionary
                startActivity(dictionaryIntent);                                                    //and launch DictionaryActivity
            }
        });

        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trainIntent = new Intent(MainActivity.this, WordActivity.class);
                trainIntent.putExtra(KEY_REPEAT, userDictionary.get(random.nextInt(userDictionary.size())));
                trainIntent.putExtra(KEY_LAUNCHCODE, 2);
                startActivityForResult(trainIntent, RC_TRAINWORD);
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

    @Override
    protected void onPause() {
        memory.clearDataBase();
        for(WordElement word : userDictionary)memory.putWordElement(word);
        super.onPause();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras = data.getExtras();
        if(requestCode == RC_ADDNEWWORD && resultCode == Activity.RESULT_OK) {
            WordElement userWord = (WordElement)extras.get(WordActivity.KEY_NEW_WORD);              //get new word from EditWordActivity

            userDictionary.add(userWord);                                                           //add new word to userDictionary
            userDictionary.sort(wordComparator);                                                    //sort new word
        }else super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_TRAINWORD && resultCode == Activity.RESULT_OK){
            WordElement target = (WordElement)extras.get(WordActivity.KEY_REPEAT_SOURCE);
            WordElement repeat = (WordElement)extras.get(WordActivity.KEY_REPEAT_RESULT);

            userDictionary.remove(target);
            userDictionary.add(repeat);
            userDictionary.sort(wordComparator);
        }else super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("NewApi")
    public static void updateDictionary(List<WordElement> newData){                                 //updating userDictionary from other Activities
        userDictionary = newData;                                                                   //only for deleting in DictionaryActivity
        userDictionary.sort(wordComparator);                                                        //do not use it anywhere else pls (:
    }
}