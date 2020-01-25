package jiang.henry.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * The activity screen for details on a specific film
 */
public class DetailActivity extends AppCompatActivity {

    // Used to display API call progress
    private ProgressDialog pDialog;

    private Film filmToLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        filmToLoad = (Film) data.getParcelable("film");

        new Subquery().execute();


    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class Subquery extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DetailActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            filmToLoad.loadAllData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            
            // Setting text views
            TextView name = findViewById(R.id.title);
            name.setText("Title: " + filmToLoad.getTitle());

            TextView director = findViewById(R.id.director);
            director.setText("Director: " + filmToLoad.getDirector());

            TextView producer = findViewById(R.id.producer);
            producer.setText("Producer: " + filmToLoad.getProducer());

            TextView releaseDate = findViewById(R.id.releaseDate);
            releaseDate.setText("Release Date: " + filmToLoad.getPlanet(0));
        }
    }
}
