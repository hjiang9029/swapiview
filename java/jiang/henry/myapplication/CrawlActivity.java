package jiang.henry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * An activity specifically for the opening crawls of each film
 * Displays only the opening crawl of the film
 */
public class CrawlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawl);

        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        String data = getIntent().getStringExtra("crawl");

        TextView tv = findViewById(R.id.openingCrawl);
        tv.setText(data);
    }
}
