package jiang.henry.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Displays the main activity which will contain the initial list of films.
 * Will also be responsible for the first API call to populate said list.
 */
public class MainActivity extends AppCompatActivity {

    // Field to use for debugging
    private String TAG = MainActivity.class.getSimpleName();

    // Used to display API call progress
    private ProgressDialog pDialog;

    // List view to populate with films
    private ListView lv;

    private static final String SERVICE_URL = "https://swapi.co/api/";

    // Creates a list of SWObjects, which will store all films
    public static ArrayList<SWObject> swList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets list view reference to corresponding list in layouts
        lv = (ListView) findViewById(R.id.filmList);
        // Call API
        new ServiceCall().execute();

        // Set listeners to move to next activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("film", swList.get(i));
                startActivity(intent);
            }
        });
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class ServiceCall extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Parser parse = Parser.getInstance();

            // Making a request to url and getting (hopefully) a response in String
            // Call for all films
            String jsonStr = parse.makeServiceCall(SERVICE_URL + "films/");

            if (jsonStr != null) {
                try {

                    // Getting JSON Object
                    JSONObject filmJsonObject = new JSONObject(jsonStr);

                    // Array of films
                    JSONArray results = filmJsonObject.getJSONArray("results");

                    for (int j = 0; j < results.length(); j++) {
                        JSONObject film = results.getJSONObject(j);

                        // data setting
                        String title = (String) film.get("title");
                        String director = (String) film.get("director");
                        String producer = (String) film.get("producer");
                        String releaseDate = (String) film.get("release_date");
                        String createdDate = (String) film.get("created");
                        String editedDate = (String) film.get("edited");
                        String url = (String) film.get("url");
                        String openingCrawl = (String) film.get("opening_crawl");

                        // Getting json arrays in response
                        JSONArray jsonPlanets = film.getJSONArray("planets");
                        JSONArray jsonPeople = film.getJSONArray("characters");
                        JSONArray jsonSpecies = film.getJSONArray("species");
                        JSONArray jsonStarships = film.getJSONArray("starships");
                        JSONArray jsonVehicles = film.getJSONArray("vehicles");

                        // add new film to the list
                        Film newMovie = new Film(title, director, producer, releaseDate, openingCrawl, createdDate, editedDate, url);

                        // Creating new Actors using only their URL. Will be unpacked at a later time
                        for (int m = 0; m < jsonPlanets.length(); m++) {
                            Planet currentPlanet = new Planet(jsonPlanets.getString(m));
                            newMovie.addPlanet(currentPlanet);
                        }
                        for (int m = 0; m < jsonPeople.length(); m++) {
                            Person currentPerson = new Person(jsonPeople.getString(m));
                            newMovie.addPerson(currentPerson);
                        }
                        for (int m = 0; m < jsonSpecies.length(); m++) {
                            Species currentSpecies = new Species(jsonSpecies.getString(m));
                            newMovie.addSpecies(currentSpecies);
                        }
                        for (int m = 0; m < jsonStarships.length(); m++) {
                            Starship currentShip = new Starship(jsonStarships.getString(m));
                            newMovie.addStarship(currentShip);
                        }
                        for (int m = 0; m < jsonVehicles.length(); m++) {
                            Vehicle currentVehicle = new Vehicle(jsonVehicles.getString(m));
                            newMovie.addVehicle(currentVehicle);
                        }

                        swList.add(newMovie);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                // Could not get a string from the parser/call
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            ArrayAdapter<SWObject> strAdapter = new ArrayAdapter<SWObject>(MainActivity.this, android.R.layout.simple_list_item_1, swList);
            // Attach the adapter to a ListView
            lv.setAdapter(strAdapter);
        }
    }
}

