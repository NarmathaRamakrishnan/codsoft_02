package com.example.quote_of_the_day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuoteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quote_database";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    public static final String TABLE_QUOTES = "quotes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUOTE = "quote_text";

    // SQL statement to create the quotes table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_QUOTES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_QUOTE
            + " text not null);";

    public QuoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTES);
        onCreate(db);
    }
}

