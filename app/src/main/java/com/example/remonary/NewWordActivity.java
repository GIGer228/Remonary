package com.example.remonary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        EditText newWord = findViewById(R.id.new_word_text);
        EditText newTranslate = findViewById(R.id.new_translation_text);
        EditText newDescription = findViewById(R.id.new_description_text);

        Button addButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewWordActivity.this, "Hmm... How interesting", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewWordActivity.this, "Not funny. *gets anger*", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}