package jiang.henry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
 * The activity screen for details on a specific film
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        Film currentFilm = (Film) data.getParcelable("film");

        // Setting text views
        TextView name = findViewById(R.id.title);
        name.setText("Title: " + currentFilm.getTitle());

        TextView region = findViewById(R.id.director);
        region.setText("Director: " + currentFilm.getDirector());

        TextView area = findViewById(R.id.producer);
        area.setText("Producer: " + currentFilm.getProducer());

        TextView capital = findViewById(R.id.releaseDate);
        capital.setText("Release Date: " + currentFilm.getReleaseDate());

    }
}
