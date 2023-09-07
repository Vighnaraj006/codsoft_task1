package com.example.quotec;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LikedQuotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_quotes);

        ListView listView = findViewById(R.id.listV);

        ArrayList<String> likedQuotes = getIntent().getStringArrayListExtra("likedQuotes");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item,
                R.id.textViewQuote,
                likedQuotes
        );

        listView.setAdapter(adapter);
    }
}
