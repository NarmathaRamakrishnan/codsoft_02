package com.example.quote_of_the_day;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SavedQuotesActivity extends AppCompatActivity {

    private ListView savedQuotesListView;
    private QuoteDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        dbHelper = new QuoteDbHelper(this);
        savedQuotesListView = findViewById(R.id.savedQuotesListView);

        displaySavedQuotes();
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displaySavedQuotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                QuoteDbHelper.COLUMN_ID,
                QuoteDbHelper.COLUMN_QUOTE
        };


        Cursor cursor = db.query(
                QuoteDbHelper.TABLE_QUOTES,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<String> savedQuotesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String quote = cursor.getString(cursor.getColumnIndexOrThrow(QuoteDbHelper.COLUMN_QUOTE));
            savedQuotesList.add(quote);
        }

        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_saved_quote, R.id.textViewSavedQuote, savedQuotesList);
        savedQuotesListView.setAdapter(adapter);
    }
}
