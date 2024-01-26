package com.example.quote_of_the_day;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private QuoteDbHelper dbHelper;

    private String[] quotes = {
            "The only way to do great work is to love what you do.",
            "Innovation distinguishes between a leader and a follower.",
            "Your time is limited, don't waste it living someone else's life.",
            "A man growing old becomes a child again.",
            "We tried not to age, but time had its rage",
            "If you want something you have never had, you must be willing to do something you have never done.",
            "Life begins at the end of your comfort zone.",
            "Two things define you: Your patience when you have nothing and your attitude when you have everything.",
            "The greatest pleasure in life is doing what people say you cannot do.",
            "Never give up on a dream just because of the time it will take to accomplish it. The time will pass anyway.",
            "You don’t have to be great to start, but you have to start to be great.",
            "A river cuts through rock, not because of its power, but because of its persistence.",
            "If it is important to you, you will find a way. If not, you’ll find an excuse.",
            "If people are not laughing at your goals, your goals are too small.",
            "Great minds discuss ideas. Average minds discuss events. Small minds discuss people.",
            "Hard work beats talent when talent doesn’t work hard.",
            "I am not what happened to me, I am what I choose to become.",
            "An entire sea of water can’t sink a ship unless it gets inside the ship. Similarly, the negativity of the world can’t put you down unless you allow it to get inside you.",
            "It does not matter how slowly you go as long as you do not stop.",
            "If you want to live a happy life, tie it to a goal, not to people or things."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new QuoteDbHelper(this);
        quoteTextView = findViewById(R.id.quoteTextView);
        updateQuote();

        ImageView shareIcon = findViewById(R.id.shareIcon);
        ImageView savedFilesIcon = findViewById(R.id.savedFilesIcon);
        ImageView saveIcon = findViewById(R.id.saveIcon);


        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentQuote = quoteTextView.getText().toString();


                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);


                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        savedFilesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement view saved files functionality
                Intent intent = new Intent(MainActivity.this, SavedQuotesActivity.class);
                startActivity(intent);
            }
        });

        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentQuote = quoteTextView.getText().toString();
                saveQuote(currentQuote);
                Toast.makeText(MainActivity.this, "Quote Saved!", Toast.LENGTH_SHORT).show();
                saveIcon.setImageResource(R.drawable.baseline_bookmark_added_24);
                saveIcon.setEnabled(false);
            }
        });
    }

    private void updateQuote() {
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        String randomQuote = quotes[randomIndex];
        quoteTextView.setText(randomQuote);
    }
    private void saveQuote(String quote) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QuoteDbHelper.COLUMN_QUOTE, quote);

        db.insert(QuoteDbHelper.TABLE_QUOTES, null, values);

    }
}
