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

    public static final String KEY_NEW_WORD = "new_word";
    public static final String KEY_USER_EDIT = "user_edit";
    public static final String KEY_EDIT_SOURCE = "edit_source";

    private int launchCode;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_editing);

        final TextView activityMessage = findViewById(R.id.activity_message);

        final EditText wordTitle = findViewById(R.id.new_word_text);
        final EditText wordTranslate = findViewById(R.id.new_translation_text);
        final EditText wordDescription = findViewById(R.id.new_description_text);

        Button confirmButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        launchCode = getIntent().getExtras().getInt("launch_code");

        switch(launchCode){
            case 0:
                activityMessage.setText(getString(R.string.add_word_line));
                confirmButton.setText(getString(R.string.add_button_text));

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement newWord = new WordElement((long) random.nextLong()*34/random.nextLong());

                            newWord.setTitle(wordTitle.getText().toString());
                            newWord.setTranslate(wordTranslate.getText().toString());
                            newWord.setDescription(wordDescription.getText().toString());

                            resultIntent.putExtra(KEY_NEW_WORD, newWord);
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
                activityMessage.setText(getString(R.string.edit_word_line));
                confirmButton.setText(getString(R.string.edit_button_text));

                final WordElement clickWord = (WordElement) getIntent().getExtras().get(DictionaryActivity.KEY_CLICKWORD);

                wordTitle.setText(clickWord.getTitle());
                wordTranslate.setText(clickWord.getTranslate());
                wordDescription.setText((clickWord.getDescription()));

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement userEdit = new WordElement((long) random.nextLong()*34/random.nextLong());

                            userEdit.setTitle(wordTitle.getText().toString());
                            userEdit.setTranslate(wordTranslate.getText().toString());
                            userEdit.setDescription(wordDescription.getText().toString());

                            resultIntent.putExtra(KEY_EDIT_SOURCE, clickWord);
                            resultIntent.putExtra(KEY_USER_EDIT, userEdit);
                            setResult(Activity.RESULT_OK, resultIntent);

                            Toast.makeText(WordEditingActivity.this, "Wha.. New word?", Toast.LENGTH_SHORT).show();
                            finish();
                        }else Toast.makeText(WordEditingActivity.this, "Nope, fill two first lines", Toast.LENGTH_SHORT).show();
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    }
                });

                break;
        }
    }
}