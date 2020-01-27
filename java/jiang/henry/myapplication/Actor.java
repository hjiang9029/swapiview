package jiang.henry.myapplication;


import android.os.Parcel;

/**
 * Represents an 'Actor'. An actor is an entity in the Star Wars franchise, meaning an actor
 * can be a planet, person, vehicle, starship, or species. Really, it is just anything but a film
 * in Swapi.
 */
public abstract class Actor extends SWObject {

    // The name of this actor
    private String name;


    /**
     * Creates an actor using only the URL associated this actor.
     * Grants a temporary name to specify that this actor has not yet been unpacked.
     *
     * @param url a string representing the url for this actor
     */
    public Actor(String url) {
        super(url);
        this.name = "TEMP";
    }

    /**
     * a more 'full' constructor for an actor. requires swapi metadata as well as the name for
     * this actor.
     *
     * @param name the string representing the name this actor has
     * @param created the string representing the date the swapi object was created
     * @param edited the string representing the date the swapi object was edited
     * @param url the string representing the url for this actor
     */
    public Actor(String name, String created, String edited, String url) {
        super(created, edited, url);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * writes this actor to a parcel
     * @param parcel the parcel to write to
     * @param i an int representing any flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(name);
    }

    /**
     * creates an actor from a parcel
     * @param in the parcel to read from
     */
    protected Actor(Parcel in) {
        super(in);
        this.name = in.readString();
    }


    @Override
    public String toString() {
        return name;
    }

    /**
     * 'Unpacks' the information about the actor from their URL.
     * Essentially sets all of this actors fields
     * Used so the initial load on the start page will not be as heavy.
     */
    abstract void unpackFromURL();

    /**
     * Return a description as a string about this actor
     * @return the description as a string
     */
    abstract String printDescription();
}
