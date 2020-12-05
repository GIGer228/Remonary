package com.example.remonary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remonary.DataSet.WordElement;
import com.example.remonary.R;

import java.util.Random;
public class WordEditingActivity extends AppCompatActivity {

    public static final String KEY_USER_WORD = "user_word";
    public static final String KEY_USER_EDIT = "user_edit";

    private int launchCode;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_editing);

        final TextView activityMessage = findViewById(R.id.activity_message);

        final EditText newWord = findViewById(R.id.new_word_text);
        final EditText newTranslate = findViewById(R.id.new_translation_text);
        final EditText newDescription = findViewById(R.id.new_description_text);

        Button addButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        launchCode = getIntent().getExtras().getInt("launch_code");

        switch(launchCode){
            case 0:
                activityMessage.setText(getString(R.string.add_word_line));
                addButton.setText(getString(R.string.add_button_text));

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !newWord.getText().toString().equals("");
                        boolean hasTranslate = !newTranslate.getText().toString().equals("");

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement userWord = new WordElement((long) random.nextLong()*34/random.nextLong());

                            userWord.setTitle(newWord.getText().toString());
                            userWord.setTranslate(newTranslate.getText().toString());
                            userWord.setDescription(newDescription.getText().toString());

                            resultIntent.putExtra(KEY_USER_WORD, userWord);
                            setResult(Activity.RESULT_OK, resultIntent);

                            Toast.makeText(WordEditingActivity.this, "Hmm... How interesting", Toast.LENGTH_SHORT).show();
                            finish();
                        }else Toast.makeText(WordEditingActivity.this, "Nope, fill two first lines", Toast.LENGTH_SHORT).show();
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);

                        Toast.makeText(WordEditingActivity.this, "Not funny. *gets anger*", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            case 1:

                break;
        }
    }
}