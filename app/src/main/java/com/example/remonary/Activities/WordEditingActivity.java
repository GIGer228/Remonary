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

    public static final String KEY_NEW_WORD = "new_word";//String key for delivering new word to MainActivity(userDictionary)
    public static final String KEY_USER_EDIT = "user_edit";//String key for delivering edited word to DictionaryActivity
    public static final String KEY_EDIT_SOURCE = "edit_source";//String key for delivering link to clicked word to DictionaryActivity

    private int launchCode;//variable for processing launch source Activity

    private Random random = new Random();//new word id generator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_editing);

        final TextView activityMessage = findViewById(R.id.activity_message);//EditingWordActivity welcome line

        final EditText wordTitle = findViewById(R.id.new_word_text);
        final EditText wordTranslate = findViewById(R.id.new_translation_text);
        final EditText wordDescription = findViewById(R.id.new_description_text);

        Button confirmButton = findViewById(R.id.add_word_button);
        Button closeButton = findViewById(R.id.close_button);

        launchCode = getIntent().getExtras().getInt("launch_code");//get launch Activity source

        switch(launchCode){
            case 0://launchCode from MainActivity(add new word)
                activityMessage.setText(getString(R.string.add_word_line));
                confirmButton.setText(getString(R.string.add_button_text));

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");//incorrect WordElement condition
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");//same as above, but not

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement newWord = new WordElement((long) random.nextLong()*34/random.nextLong());//create new word

                            newWord.setTitle(wordTitle.getText().toString());//get user data
                            newWord.setTranslate(wordTranslate.getText().toString());//and set it
                            newWord.setDescription(wordDescription.getText().toString());//in new WordElement

                            resultIntent.putExtra(KEY_NEW_WORD, newWord);//pack new word
                            setResult(Activity.RESULT_OK, resultIntent);

                            Toast.makeText(WordEditingActivity.this, "Hmm... How interesting", Toast.LENGTH_SHORT).show();//human imitation. Just kidding
                            finish();
                        }else Toast.makeText(WordEditingActivity.this, "Nope, fill two first lines", Toast.LENGTH_SHORT).show();//another human imitation
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);

                        Toast.makeText(WordEditingActivity.this, "Not funny. *gets anger*", Toast.LENGTH_SHORT).show();//just another human imitation UwU
                        finish();
                    }
                });
                break;
            case 1://launchCode from DictionaryActivity (edit existing word)
                activityMessage.setText(getString(R.string.edit_word_line));
                confirmButton.setText(getString(R.string.edit_button_text));

                final WordElement clickWord = (WordElement) getIntent().getExtras().get(DictionaryActivity.KEY_CLICKWORD);//get clicked word from DictionaryActivity (edit source)

                wordTitle.setText(clickWord.getTitle());//get click word data
                wordTranslate.setText(clickWord.getTranslate());//set it to EditText fields
                wordDescription.setText((clickWord.getDescription()));//and display to user

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");//incorrect WordElement condition
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");//same as above, but not

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement userEdit = new WordElement((long) random.nextLong()*34/random.nextLong());//create new word

                            userEdit.setTitle(wordTitle.getText().toString());//get user data
                            userEdit.setTranslate(wordTranslate.getText().toString());//and set it
                            userEdit.setDescription(wordDescription.getText().toString());//to new WordElement

                            resultIntent.putExtra(KEY_EDIT_SOURCE, clickWord);//return clicked word
                            resultIntent.putExtra(KEY_USER_EDIT, userEdit);//pack edited clicked word
                            setResult(Activity.RESULT_OK, resultIntent);

                            Toast.makeText(WordEditingActivity.this, "Wha.. New word?", Toast.LENGTH_SHORT).show();//human imitation. Just kidding
                            finish();
                        }else Toast.makeText(WordEditingActivity.this, "Nope, fill two first lines", Toast.LENGTH_SHORT).show();//just another human imitation
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);//do nothing...
                        finish();
                    }
                });

                break;
        }
    }
}
