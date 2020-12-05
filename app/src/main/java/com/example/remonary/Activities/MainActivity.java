package com.example.remonary.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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

    List<WordElement> userDictionary = new ArrayList<>();
    WordComparator wordComparator;

    public static final int RC_ADDNEWWORD = 1030;
    public static final int RC_SEEDICTIONARY = 1090;

    public static final String KEY_USERDATA = "user_data";

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
                startActivityForResult(newWordIntent, RC_ADDNEWWORD);
            }
        });

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(MainActivity.this, DictionaryActivity.class);
                dictionaryIntent.putExtra(KEY_USERDATA, (Serializable) userDictionary);
                startActivityForResult(dictionaryIntent, RC_SEEDICTIONARY);
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_ADDNEWWORD && resultCode == Activity.RESULT_OK) {
            WordElement userWord = (WordElement) data.getExtras().get(WordEditingActivity.KEY_USER_WORD);

            userDictionary.add(userWord);
            userDictionary.sort(wordComparator);
        }else
            if (requestCode == RC_SEEDICTIONARY && resultCode == Activity.RESULT_OK){

            }else super.onActivityResult(requestCode, resultCode, data);
    }
}