package com.example.remonary.DataSet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RemonaryDataBase extends SQLiteOpenHelper {
    public RemonaryDataBase(@Nullable Context context) {
        super(context, "RemonaryDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE WordElement(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "language TEXT, " +
                "translate TEXT, " +
                "description TEXT, " +
                "weight REAL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putWordElement(WordElement word){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", word.getTitle());
        contentValues.put("language", word.getLanguageMark());
        contentValues.put("translate", word.getTranslate());
        contentValues.put("description", word.getDescription());
        contentValues.put("weight", word.getWeight());

        database.insert("WordElement", null, contentValues);
        database.close();
    }

    public void deleteWordElement(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM WordElement WHERE title = ?",
                new String[]{title});
        db.close();
    }

    private static WordElement readWordElement(Cursor cursor){
        return new WordElement(
                cursor.getLong(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("language")),
                cursor.getString(cursor.getColumnIndex("translate")),
                cursor.getString(cursor.getColumnIndex("description")),
                cursor.getDouble(cursor.getColumnIndex("weight")));
    }

    private static List<WordElement> readWordsFromCursor(Cursor cursor){
        List<WordElement> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                WordElement word = readWordElement(cursor);
                list.add(word);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public List<WordElement> getAllWords(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM WordElement", null);
        return readWordsFromCursor(cursor);
    }

    public WordElement getWord(long id){
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM WordElement WHERE _id = ?",
                new String[]{String.valueOf(id)});

        return readWordsFromCursor(cursor).get(0);
    }

    public void clearDataBase(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("WordElement", null, null);
        db.close();
    }

}