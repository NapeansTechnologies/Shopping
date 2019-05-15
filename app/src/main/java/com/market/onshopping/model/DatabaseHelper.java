package com.market.onshopping.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note, String image, String title, String itemcount, String child,
                           String amount, String adultAmount, String childamount, String datee) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Note.COLUMN_NOTE, note);
        values.put(Note.COLUMN_IMAGE, image);
        values.put(Note.COLUMN_TITLE, title);
        values.put(Note.COLUMN_ITEMCOUNT, itemcount);
        values.put(Note.COLUMN_CHILD, child);
        values.put(Note.COLUMN_AMOUNT, amount);
        values.put(Note.COLUMN_DISCOUNT, adultAmount);
        values.put(Note.COLUMN_CHILD_AMOUNT, childamount);
        values.put(Note.COLUMN_DATE, datee);

        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_IMAGE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_ITEMCOUNT)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_CHILD)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_AMOUNT)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_DISCOUNT)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_CHILD_AMOUNT)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
                note.setImage(cursor.getString(cursor.getColumnIndex(Note.COLUMN_IMAGE)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE)));
                note.setAmount(cursor.getString(cursor.getColumnIndex(Note.COLUMN_AMOUNT)));
                note.setItemCount(cursor.getString(cursor.getColumnIndex(Note.COLUMN_ITEMCOUNT)));
                note.setChild(cursor.getString(cursor.getColumnIndex(Note.COLUMN_CHILD)));
                note.setAdult_amount(cursor.getString(cursor.getColumnIndex(Note.COLUMN_DISCOUNT)));
                note.setChild_amount(cursor.getString(cursor.getColumnIndex(Note.COLUMN_CHILD_AMOUNT)));
                note.setDatee(cursor.getString(cursor.getColumnIndex(Note.COLUMN_DATE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

                Log.i("dpnotes", note.getImage());
                Log.i("dpnotes", note.getNote());
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public void delete_byID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_NOTE + "=" + id, null);
    }

    public int updateNote(String amount, int id, String itemCount, String child) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_AMOUNT, amount);
        values.put(Note.COLUMN_ITEMCOUNT, itemCount);
        values.put(Note.COLUMN_CHILD, child);

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_NOTE + " =? ",
                new String[]{String.valueOf(id)});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
