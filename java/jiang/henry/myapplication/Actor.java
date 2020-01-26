package jiang.henry.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Represents an 'Actor'. An actor is an entity in the Star Wars franchise, meaning an actor
 * can be a planet, person, vehicle, starship, or species. Really, it is just anything but a film
 * in Swapi.
 */
public abstract class Actor extends SWObject {

    // The name of this actor
    private String name;


    public Actor(String url) {
        super(url);
        this.name = "TEMP";
    }

    public Actor(String name, String created, String edited, String url) {
        super(created, edited, url);
        this.name = name;
        //films = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Writes this film into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(name);
    }

    /**
     * Constructor from a parcel. Recreates the film from a passed in parcel
     *
     * @param in the parcel to use to recreate the film object
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
     */
    abstract void unpackFromURL();

    abstract String printDescription();
}
