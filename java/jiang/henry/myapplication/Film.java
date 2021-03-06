package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Represents a 'film' in the Star Wars series
 */
public class Film extends SWObject {

    // Field to use for debugging
    private String TAG = Film.class.getSimpleName();

    // A string that represents the title
    private String title;

    // A string that represents the director
    private String director;

    // A string that represents the producer(s)
    private String producer;

    // A string that represents the release date of the film
    private String releaseDate;

    // a string that represents the opening crawl
    private String openingCrawl;

    // a list of characters this film contains
    private ArrayList<Person> characters;

    // a list of planets this film contains
    private ArrayList<Planet> planets;

    // a list of starships this film contains
    private ArrayList<Starship> starships;

    // a list of vehicles this film contains
    private ArrayList<Vehicle> vehicles;

    // a list of species this film contains
    private ArrayList<Species> species;

    /**
     * Constructs a film object without any 'extra' metadata contained in all SWObjects
     *
     * @param title String representing the title of the film
     * @param director String representing the director of the film
     * @param producer String representing the producer(s) of the film
     * @param releaseDate String representing the release date of the film
     * @param openingCrawl String representing the oepning crawl of the film
     */
    public Film(String title, String director, String producer, String releaseDate, String openingCrawl) {
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.openingCrawl = openingCrawl;
        characters = new ArrayList<>();
        planets = new ArrayList<>();
        starships = new ArrayList<>();
        vehicles = new ArrayList<>();
        species = new ArrayList<>();
    }

    /**
     * Constructs a film object 'fully' with 'extra' metadata contained in all SWObjects
     *
     * @param title String representing the title of the film
     * @param director String representing the director of the film
     * @param producer String representing the producer(s) of the film
     * @param releaseDate String representing the release date of the film
     * @param openingCrawl String representing the oepning crawl of the film
     * @param created String representing the date created for this object in the API
     * @param edited String representing the date edited for this object in the API
     * @param url String representing the url for the object in the API
     */
    public Film(String title, String director, String producer, String releaseDate, String openingCrawl,
                String created, String edited, String url) {
        super(created, edited, url);
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.openingCrawl = openingCrawl;
        characters = new ArrayList<>();
        planets = new ArrayList<>();
        starships = new ArrayList<>();
        vehicles = new ArrayList<>();
        species = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    public void addPerson(Person p) {
        characters.add(p);
    }

    public void addSpecies(Species s) {
        species.add(s);
    }

    public void addStarship(Starship s) {
        starships.add(s);
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public ArrayList<Person> getCharacters() {
        return characters;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public ArrayList<Starship> getStarships() {
        return starships;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Species> getSpecies() {
        return species;
    }

    /**
     * Unpacks all the data from the list of actors this Film has from their URLs
     */
    public void loadAllData() {
        try {
            for (Planet planet : planets) {
                planet.unpackFromURL();
            }
            for (Person person : characters) {
                person.unpackFromURL();
            }
            for (Species currentSpecies : species) {
                currentSpecies.unpackFromURL();
            }
            for (Starship starship : starships) {
                starship.unpackFromURL();
            }
            for (Vehicle vehicle : vehicles) {
                vehicle.unpackFromURL();
            }
        } catch (Exception e) {
            Log.e(TAG, "An exception occurred when unpacking through URL.");
        }
    }

    /**
     * toString method for a film. Will only return its name
     * @return a string representing this films' name
     */
    @Override
    public String toString() {
        return getTitle();
    }

    /**
     * writes this film to a parcel
     * @param parcel the parcel to write to
     * @param i an int representing any flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(title);
        parcel.writeString(director);
        parcel.writeString(producer);
        parcel.writeString(releaseDate);
        parcel.writeString(openingCrawl);
        parcel.writeList(characters);
        parcel.writeList(planets);
        parcel.writeList(starships);
        parcel.writeList(vehicles);
        parcel.writeList(species);
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    /**
     * creates a film from a passed in parcel
     * @param in the parcel to read from
     */
    private Film(Parcel in) {
        super(in);
        this.title = in.readString();
        this.director = in.readString();
        this.producer = in.readString();
        this.releaseDate = in.readString();
        this.openingCrawl = in.readString();
        this.characters = in.readArrayList(Person.class.getClassLoader());
        this.planets = in.readArrayList(Planet.class.getClassLoader());
        this.starships = in.readArrayList(Starship.class.getClassLoader());
        this.vehicles = in.readArrayList(Vehicle.class.getClassLoader());
        this.species = in.readArrayList(Species.class.getClassLoader());
    }
}
