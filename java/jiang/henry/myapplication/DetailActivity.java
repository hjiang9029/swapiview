package jiang.henry.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * The activity screen for details on a specific film
 * This class is also responsible for any API calls that it needs to call to populate
 * the activity.
 */
public class DetailActivity extends AppCompatActivity {

    // static variable for checking if the selected item in the list view is the opening crawl
    private static String OPENING_CRAWL = "Opening Crawl";

    // Used to display API call progress
    private ProgressDialog pDialog;

    // the film this activity will be displaying
    private Film filmToLoad;

    // a map used to populate the expandable list view
    private HashMap<String, ArrayList<Actor>> filmActorMap;

    // contains the subheaders for the expandable list view
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
            pDialog.setMessage("Loading film...");
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

            // List view set-up
            ListView lv = findViewById(R.id.listDetails);
            final ArrayList<String> detailsList = new ArrayList<>();

            detailsList.add("Title: " + filmToLoad.getTitle());
            detailsList.add("Director: " + filmToLoad.getDirector());
            detailsList.add("Producer: " + filmToLoad.getProducer());
            detailsList.add("Release Date: " + filmToLoad.getReleaseDate());

            detailsList.add(OPENING_CRAWL);

            ArrayAdapter<String> strAdapter = new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_list_item_1, detailsList);
            lv.setAdapter(strAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (detailsList.get(i).equals(OPENING_CRAWL)) {
                        Intent intent = new Intent(DetailActivity.this, CrawlActivity.class);
                        intent.putExtra("crawl", filmToLoad.getOpeningCrawl());
                        startActivity(intent);
                    }
                }
            });

            // Expandable list view set-up
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

        /**
         * Creates, adds data to the list and map used to populate the expandable list view
         */
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
