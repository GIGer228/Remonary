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

    public static final String KEY_USER_WORD = "user_word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        final EditText newWord = findViewById(R.id.new_word_text);
        final EditText newTranslate = findViewById(R.id.new_translation_text);
        final EditText newDescription = findViewById(R.id.new_description_text);

        Button addButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        final boolean hasTitle = !newWord.getText().toString().equals("");
        final boolean hasTranslate = !newTranslate.getText().toString().equals("");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasTitle && hasTranslate){
                Intent resultIntent = new Intent();
                WordElement userWord = new WordElement();

                userWord.setTitle(newWord.getText().toString());
                userWord.setTranslate(newTranslate.getText().toString());
                userWord.setDescription(newDescription.getText().toString());

                resultIntent.putExtra(KEY_USER_WORD, userWord);
                setResult(Activity.RESULT_OK, resultIntent);

                Toast.makeText(NewWordActivity.this, "Hmm... How interesting", Toast.LENGTH_SHORT).show();
                finish();
                }else Toast.makeText(NewWordActivity.this, "Nope, fill two first lines", Toast.LENGTH_SHORT).show();
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