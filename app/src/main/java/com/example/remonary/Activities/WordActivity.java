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

import static android.widget.Toast.LENGTH_SHORT;

public class WordActivity extends AppCompatActivity {

    public static final String KEY_NEW_WORD = "new_word";                                           //String key for delivering new word to MainActivity(userDictionary)
    public static final String KEY_USER_EDIT = "user_edit";                                         //String key for delivering edited word to DictionaryActivity
    public static final String KEY_EDIT_SOURCE = "edit_source";                                     //String key for delivering link to clicked word to DictionaryActivity
    public static final String KEY_REPEAT_SOURCE = "repeat_source";
    public static final String KEY_REPEAT_RESULT = "repeat_result";

    private int launchCode;                                                                         //variable for processing launch source Activity

    private static Random random = new Random();                                                           //new word id generator

    static TextView activityMessage;
    static EditText wordTitle;
    static EditText wordTranslate;
    static EditText wordDescription;

    static Button confirmButton;
    static Button closeButton;

    WordRepeater wordRepeater = new WordRepeater();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        activityMessage = findViewById(R.id.activity_message);                                      //WordActivity welcome line

        wordTitle = findViewById(R.id.new_word_text);
        wordTranslate = findViewById(R.id.new_translation_text);
        wordDescription = findViewById(R.id.new_description_text);

        confirmButton = findViewById(R.id.add_word_button);
        closeButton = findViewById(R.id.close_button);

        launchCode = getIntent().getExtras().getInt("launch_code");                             //get launch Activity source

        switch(launchCode){
            case 0:                                                                                 //launchCode from MainActivity(add new word)
                activityMessage.setText(getString(R.string.add_word_line));
                confirmButton.setText(getString(R.string.add_button_text));

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");              //incorrect WordElement condition
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");      //same as above, but not

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();
                            WordElement newWord = new WordElement();
                            newWord.setId((long)random.nextLong()*34/random.nextLong());            //create new word

                            newWord.setTitle(wordTitle.getText().toString());                       //get user data
                            newWord.setTranslate(wordTranslate.getText().toString());               //and set it
                            newWord.setDescription(wordDescription.getText().toString());           //in new WordElement

                            resultIntent.putExtra(KEY_NEW_WORD, newWord);                           //pack new word
                            setResult(Activity.RESULT_OK, resultIntent);

                            Toast.makeText(WordActivity.this, "Hmm... How interesting", LENGTH_SHORT).show();
                            finish();                                                               //human imitation. Just kidding
                        }else Toast.makeText(WordActivity.this, "Nope, fill two first lines", LENGTH_SHORT).show();
                    }
                });                                                                                 //another human imitation

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);
                        Toast.makeText(WordActivity.this, "Not funny. *gets anger*", LENGTH_SHORT).show();
                        finish();                                                                   //just another human imitation UwU
                    }
                });
                break;
            case 1:                                                                                 //launchCode from DictionaryActivity(edit existing word)
                activityMessage.setText(getString(R.string.edit_word_line));
                confirmButton.setText(getString(R.string.edit_button_text));

                final WordElement clickWord= (WordElement)getIntent()                               //get clicked word from DictionaryActivity
                        .getExtras().get(DictionaryActivity.KEY_CLICKWORD);                         //(edit source/link)

                wordTitle.setText(clickWord.getTitle());                                            //get click word data
                wordTranslate.setText(clickWord.getTranslate());                                    //set it to EditText fields
                wordDescription.setText((clickWord.getDescription()));                              //and so display to user

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean hasTitle = !wordTitle.getText().toString().equals("");              //incorrect WordElement condition
                        boolean hasTranslate = !wordTranslate.getText().toString().equals("");      //same as above, but second

                        if(hasTitle && hasTranslate){
                            Intent resultIntent = new Intent();

                            WordElement userEdit = new WordElement();                               //create new word
                            userEdit.setId((long)random.nextLong()*34/random.nextLong());           //set random Id(make it better)

                            userEdit.setTitle(wordTitle.getText().toString());                      //get edited data
                            userEdit.setTranslate(wordTranslate.getText().toString());              //and set it
                            userEdit.setDescription(wordDescription.getText().toString());          //to new WordElement

                            resultIntent.putExtra(KEY_EDIT_SOURCE, clickWord);                      //pack clicked word
                            resultIntent.putExtra(KEY_USER_EDIT, userEdit);                         //and edited clicked word
                            setResult(Activity.RESULT_OK, resultIntent);                            //and deliver it back

                            Toast.makeText(WordActivity.this, "Wha.. New word?", LENGTH_SHORT).show();
                            finish();                                                               //human imitation. Just kidding
                        }else Toast.makeText(WordActivity.this, "Nope, fill two first lines", LENGTH_SHORT).show();
                                                                                                    //just another way to imitate human
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);                                        //do nothing...
                        finish();                                                                   //just close it
                    }
                });                                                                                 //one more line
                break;
            case 2:
                WordElement target = (WordElement)getIntent().getExtras().get(MainActivity.KEY_REPEAT);
                wordRepeater.trainWord(target);
                break;
        }
    }

    class WordRepeater{

        WordElement repeatWord;
        WordElement badWord;                                                                        //the worst repeats

        long repeatCount;                                                                           //statistics parameter
        long goods;
        long bads;

        public WordRepeater() {
            this.badWord = null;
            this.repeatCount = 0;
        }

        public void trainWord(final WordElement target){
            this.repeatWord = target;

            final String title = target.getTitle();
            final String translate = target.getTranslate();
            String description = target.getDescription();

            double weight = target.getWeight();
            final double reward = weight / 10;

            wordDescription.setText(description);
            wordDescription.setVisibility(View.INVISIBLE);
            confirmButton.setText("Answer");
            closeButton.setText("Keep silent");

            switch(random.nextInt(2) + 1){
                case 1:
                    activityMessage.setText(R.string.title_quest);
                    wordTitle.setText("");
                    wordTranslate.setText(translate);

                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean answer = wordTitle.getText().toString().toLowerCase().equals(title.toLowerCase());
                            if(answer){
                                target.setWeight(target.getWeight() + reward);
                                goods++;
                                Toast.makeText(WordActivity.this, "Congrats! You're right", Toast.LENGTH_SHORT).show();
                                wordTitle.setText(title);
                                closeButton.setText("Go back and learn more");
                            }else{
                                target.setWeight(target.getWeight() - reward);
                                bads++;
                                Toast.makeText(WordActivity.this, "Nope. Learn better", Toast.LENGTH_SHORT).show();
                                wordTitle.setText(title);
                                closeButton.setText("Go back and learn harder");
                            }
                        }
                    });
                    break;
                case 2:
                    activityMessage.setText(R.string.translate_wuest);
                    wordTitle.setText(title);
                    wordTranslate.setText("");

                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean answer = wordTranslate.getText().toString().toLowerCase().equals(translate.toLowerCase());
                            if(answer){
                                target.setWeight(target.getWeight() + reward);
                                goods++;
                                Toast.makeText(WordActivity.this, "Congrats! You're right", Toast.LENGTH_SHORT).show();
                                wordTranslate.setText(translate);
                                closeButton.setText("Go back and learn more");
                            }else{
                                target.setWeight(target.getWeight() - reward);
                                bads++;
                                Toast.makeText(WordActivity.this, "Nope. Learn better", Toast.LENGTH_SHORT).show();
                                wordTranslate.setText(translate);
                                closeButton.setText("Go back and learn harder");
                            }
                        }
                    });
                    break;
            }

            repeatCount++;
            wordDescription.setVisibility(View.VISIBLE);
            if(description.equals(""))wordDescription.setText("You've not added any description");

            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();

                    resultIntent.putExtra(KEY_REPEAT_SOURCE, repeatWord);
                    resultIntent.putExtra(KEY_REPEAT_RESULT, target);
                    setResult(Activity.RESULT_OK, resultIntent);

                    finish();
                }
            });
        }

        public WordElement getBadWord() {
            return badWord;
        }
        public void setBadWord(WordElement badWord) {
            this.badWord = badWord;
        }
        public long getRepeatCount() {
            return repeatCount;
        }
        public void setRepeatCount(long repeatCount) {
            this.repeatCount = repeatCount;
        }
    }
}