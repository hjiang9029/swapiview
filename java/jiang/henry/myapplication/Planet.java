package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class representing a planet in the Star Wars franchise
 */
public class Planet extends Actor {

    // Field to use for debugging
    private static String TAG = Planet.class.getSimpleName();

    // strings that describe this planet
    private String diameter;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String surfaceWater;

    public Planet(String url) {
        super(url);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    @Override
    public void unpackFromURL() {
        String jsonStr = Parser.getInstance().makeServiceCall(this.getUrl());
        if (jsonStr != null) {
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.setName((String) obj.get("name"));
                this.setDiameter((String) obj.get("diameter"));
                this.setRotationPeriod((String) obj.get("rotation_period"));
                this.setOrbitalPeriod((String) obj.get("orbital_period"));
                this.setGravity((String) obj.get("gravity"));
                this.setPopulation((String) obj.get("population"));
                this.setClimate((String) obj.get("climate"));
                this.setTerrain((String) obj.get("terrain"));
                this.setSurfaceWater((String) obj.get("surface_water"));
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred when parsing from json response");
            }
        } else {
            Log.e(TAG, "An error occurred when unpacking from URL");
        }
    }

    @Override
    String printDescription() {
        String result = "";
        result += "\nName: " + this.getName();
        result += "\nDiameter: " + this.getDiameter() + "km";
        result += "\nRotation Period: " + this.getRotationPeriod() + "hr(s)";
        result += "\nOrbital Period: " + this.getOrbitalPeriod() + " days";
        result += "\nGravity: " + this.getGravity();
        result += "\nPopulation: " + this.getPopulation();
        result += "\nClimate: " + this.getClimate();
        result += "\nTerrain: " + this.getTerrain();
        result += "\n% surface covered in water: " + this.getSurfaceWater() + "%";
        return result;
    }

    /**
     * Writes this planet into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(diameter);
        parcel.writeString(rotationPeriod);
        parcel.writeString(orbitalPeriod);
        parcel.writeString(gravity);
        parcel.writeString(population);
        parcel.writeString(climate);
        parcel.writeString(terrain);
        parcel.writeString(surfaceWater);
    }

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
        this.setDiameter(in.readString());
        this.setRotationPeriod(in.readString());
        this.setOrbitalPeriod(in.readString());
        this.setGravity(in.readString());
        this.setPopulation(in.readString());
        this.setClimate(in.readString());
        this.setTerrain(in.readString());
        this.setSurfaceWater(in.readString());
    }
}
