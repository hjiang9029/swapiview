package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Planet extends Actor {

    public Planet(String url) {
        super(url);
    }

    public Planet(String name, ArrayList<String> films) {
        super(name, films);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    void unpackFromURL() {
        String jsonStr = Parser.getInstance().makeServiceCall(this.getUrl());
        if (jsonStr != null) {
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.setName((String) obj.get("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    /**
     * Writes this film into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    /**
     * Creator field for the film class
     */
    public static final Parcelable.Creator<Planet> CREATOR = new Parcelable.Creator<Planet>() {
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the Person from a passed in parcel
     *
     * @param in the parcel to use to recreate the person object
     */
    public Planet(Parcel in) {
        super(in);
    }
}
