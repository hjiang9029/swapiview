package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Starship extends Actor {
    private String model;
    private String modelClass;
    private String manufacturer;
    private String creditCost;
    private String length;
    private String crewCount;
    private String passengers;
    private String maxAtmosphereSpeed;
    private String hyperdriveRating;
    private String MGLT;
    private String cargoCapacity;
    private String consumables;

    public Starship(String url) {
        super(url);
    }

    public Starship(String name, ArrayList<String> films) {
        super(name, films);
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
    public static final Parcelable.Creator<Starship> CREATOR = new Parcelable.Creator<Starship>() {
        public Starship createFromParcel(Parcel in) {
            return new Starship(in);
        }

        public Starship[] newArray(int size) {
            return new Starship[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the Person from a passed in parcel
     *
     * @param in the parcel to use to recreate the person object
     */
    public Starship(Parcel in) {
        super(in);
    }
}
