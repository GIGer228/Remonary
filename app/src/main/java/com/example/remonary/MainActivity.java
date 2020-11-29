package com.example.remonary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    TreeMap<String, WordElement> userDictionary = new TreeMap<>();

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

        newWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newWordIntent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivity(newWordIntent);
            }
        });

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}