package com.example.remonary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewWordActivity extends AppCompatActivity {

    final String KEY_USER_WORDELEMENT = "user_word_element";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        final EditText newWord = findViewById(R.id.new_word_text);
        final EditText newTranslate = findViewById(R.id.new_translation_text);
        final EditText newDescription = findViewById(R.id.new_description_text);

        Button addButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordElement userWordElement = new WordElement();

                userWordElement.setTitle(newWord.getText().toString());
                userWordElement.setTranslate(newTranslate.getText().toString());
                userWordElement.setDescription(newDescription.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_USER_WORDELEMENT, userWordElement);
                setResult(Activity.RESULT_OK);

                Toast.makeText(NewWordActivity.this, "Hmm... How interesting", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);

                Toast.makeText(NewWordActivity.this, "Not funny. *gets anger*", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}