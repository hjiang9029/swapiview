package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Represents a 'film' in the Star Wars series
 */
public class Film extends SWObject {

    // A string that represents the title
    private String title;

    // A string that represents the director
    private String director;

    // A string that represents the producer(s)
    private String producer;

    // A string that represents the release date of the film
    private String releaseDate;

    private ArrayList<Person> characters;
    private ArrayList<Planet> planets;
    private ArrayList<Starship> starships;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Species> species;


    /**
     * Constructs a film object without any 'extra' metadata contained in all SWObjects
     *
     * @param title String representing the title of the film
     * @param director String representing the director of the film
     * @param producer String representing the producer(s) of the film
     * @param releaseDate String representing the release date of the film
     */
    public Film(String title, String director, String producer, String releaseDate) {
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
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
     * @param created String representing the date created for this object in the API
     * @param edited String representing the date edited for this object in the API
     * @param url String representing the url for the object in the API
     */
    public Film(String title, String director, String producer, String releaseDate, String created,
                String edited, String url) {
        super(created, edited, url);
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        characters = new ArrayList<>();
        planets = new ArrayList<>();
        starships = new ArrayList<>();
        vehicles = new ArrayList<>();
        species = new ArrayList<>();
    }

    /**
     * Gets the title of the film
     * @return a string representing the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this film object
     * @param title a string representing the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the director of this film object
     * @return a string representing the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of this film object
     * @param director a string representing the new director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the producer(s) of this film object
     * @return a string representing the producer(s)
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Sets the producer(s) of this film object
     * @param producer a string representing the new producer(s)
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Gets the release date for this film object
     * @return a string representing the release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date for this film object
     * @param releaseDate a string representing the new release date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    public Planet getPlanet(int index) {
        return planets.get(index);
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

    public void loadAllData() {
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
    }

    @Override
    /**
     * toString method for a film. Only has the film name
     */
    public String toString() {
        return getTitle();
    }

    /**
     * Writes this film into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(title);
        parcel.writeString(director);
        parcel.writeString(producer);
        parcel.writeString(releaseDate);
        parcel.writeList(characters);
        parcel.writeList(planets);
        parcel.writeList(starships);
        parcel.writeList(vehicles);
        parcel.writeList(species);
    }

    /**
     * Creator field for the film class
     */
    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the film from a passed in parcel
     *
     * @param in the parcel to use to recreate the film object
     */
    private Film(Parcel in) {
        super(in);
        this.title = in.readString();
        this.director = in.readString();
        this.producer = in.readString();
        this.releaseDate = in.readString();
        this.characters = in.readArrayList(Person.class.getClassLoader());
        this.planets = in.readArrayList(Planet.class.getClassLoader());
        this.starships = in.readArrayList(Starship.class.getClassLoader());
        this.vehicles = in.readArrayList(Vehicle.class.getClassLoader());
        this.species = in.readArrayList(Species.class.getClassLoader());
    }
}
