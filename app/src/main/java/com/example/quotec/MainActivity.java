package com.example.quotec;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String[] questions = {
            "\"Be yourself everyone \n else is already taken.\" - Oscar Wilde",
            "\"The only way to do great work \n is to love what you do.\" - Steve Jobs",
            "\"In the middle of every \n difficulty lies opportunity.\" - Albert Einstein",
            "\"The best way to predict the \n future is to create it.\" - Peter Drucker",
            "Dream big and \n dare to fail. - Norman Vaughan",
            "Life is short, and it is up to\n you to make it sweet. - Sarah Louise ",
            "\"Happiness depends\n upon ourselves.\" - Aristotle",
            "\"The journey of a thousand\n miles begins with one step.\" - Lao Tzu",
            "\"You miss 100% of the \n shots you don't take.\" - Wayne Gretzky",
            "\"The power of imagination\n makes us infinite.\" - John Muir",
            "\"Live life to the fullest.\" - Ernest Hemingway",
            "\"Success is not in what\n you have, but who you are.\" - Bo Bennett",
            "\"Simplicity is the \n ultimate sophistication.\" - Leonardo da Vinci",
            "\"Believe you can and\n  you're halfway there.\" - Theodore Roosevelt",
            "\"Happiness is a choice.\" - Aeschylus",
            "\"Seize the day.\" - Horace",
            "\"Live in the moment.\" - Emily Dickinson"
    };

    ImageView share;
    ImageView next;
    ImageView like;
    ImageView refresh;
    TextView quote;
    private ArrayList<String> likedQuotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share = findViewById(R.id.imageView);
        next = findViewById(R.id.imageView2);
        like = findViewById(R.id.imageView3);
        quote = findViewById(R.id.textView);
        refresh = findViewById(R.id.imageView5);
        loadLikedQuotesFromSharedPreferences();

        quote.setText(getRandomQuestion());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quote.setText(getRandomQuestion());
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeQuote(quote.getText().toString());
            }
        });

        like.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showLikedQuotes();
                return true;
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quote.setText(getRandomQuestion());
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharetext = quote.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharetext);
                startActivity(sendIntent);
            }
        });
    }

    private String getRandomQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(questions.length);
        return questions[randomIndex];
    }

    private void likeQuote(String quoteText) {
        if (!likedQuotes.contains(quoteText)) {
            likedQuotes.add(quoteText);
            saveLikedQuotesToSharedPreferences();
            Toast.makeText(this, "Quote Liked!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Quote already liked!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLikedQuotes() {
        Intent intent = new Intent(MainActivity.this, LikedQuotesActivity.class);
        intent.putStringArrayListExtra("likedQuotes", likedQuotes);
        startActivity(intent);
    }

    private void saveLikedQuotesToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> likedQuotesSet = new HashSet<>(likedQuotes);
        editor.putStringSet("likedQuotes", likedQuotesSet);
        editor.apply();
    }

    private void loadLikedQuotesFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Set<String> likedQuotesSet = sharedPreferences.getStringSet("likedQuotes", new HashSet<>());
        likedQuotes = new ArrayList<>(likedQuotesSet);
    }
}
