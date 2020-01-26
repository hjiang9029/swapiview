package jiang.henry.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * The activity screen for details on a specific film
 */
public class DetailActivity extends AppCompatActivity {

    // Used to display API call progress
    private ProgressDialog pDialog;

    private Film filmToLoad;

    private HashMap<String, ArrayList<Actor>> filmActorMap;

    private List<String> headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        filmToLoad = (Film) data.getParcelable("film");

        new Subquery(this).execute();


    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class Subquery extends AsyncTask<Void, Void, Void> {

        private Context context;

        public Subquery(Context context) {
            this.context = context;
        }

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

            // TODO: Get rid of those textviews
            // Setting text views
            TextView name = findViewById(R.id.title);
            name.setText("Title: " + filmToLoad.getTitle());

            TextView director = findViewById(R.id.director);
            director.setText("Director: " + filmToLoad.getDirector());

            TextView producer = findViewById(R.id.producer);
            producer.setText("Producer: " + filmToLoad.getProducer());

            TextView releaseDate = findViewById(R.id.releaseDate);
            releaseDate.setText("Release Date: " + filmToLoad.getReleaseDate());

            prepData();

            ExpandableListView expandListView = (ExpandableListView) findViewById(R.id.expLV);
            ExpandableListAdapter adapter = new ActorListAdapter(context, headers, filmActorMap);

            expandListView.setAdapter(adapter);

            expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

             @Override
             public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                 Intent intent = new Intent(context, ActorDetailActivity.class);
                 Actor a = filmActorMap.get(headers.get(groupPosition)).get(childPosition);
                 intent.putExtra("Actor", filmActorMap.get(headers.get(groupPosition)).get(childPosition));
                 startActivity(intent);
                 return false;
                }
            });
        }

        private void prepData() {
            filmActorMap = new HashMap<>();
            headers = new ArrayList<>();

            headers.add("Characters");
            headers.add("Planets");
            headers.add("Species");
            headers.add("Starships");
            headers.add("Vehicles");

            ArrayList<Actor> characters = new ArrayList<>();
            characters.addAll(filmToLoad.getCharacters());
            ArrayList<Actor> planets = new ArrayList<>();
            planets.addAll(filmToLoad.getPlanets());
            ArrayList<Actor> species = new ArrayList<>();
            species.addAll(filmToLoad.getSpecies());
            ArrayList<Actor> starships = new ArrayList<>();
            starships.addAll(filmToLoad.getStarships());
            ArrayList<Actor> vehicles = new ArrayList<>();
            vehicles.addAll(filmToLoad.getVehicles());

            filmActorMap.put(headers.get(0), characters);
            filmActorMap.put(headers.get(1), planets);
            filmActorMap.put(headers.get(2), species);
            filmActorMap.put(headers.get(3), starships);
            filmActorMap.put(headers.get(4), vehicles);
        }
    }
}
