package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Person extends Actor {
    // Field to use for debugging
    private static String TAG = Person.class.getSimpleName();
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String species;
    private String vehicles;
    private String starships;
    private String gender;

    public Person(String url) {
        super(url);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }

    public String getStarships() {
        return starships;
    }

    public void setStarships(String starships) {
        this.starships = starships;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    void unpackFromURL() {
        String jsonStr = Parser.getInstance().makeServiceCall(this.getUrl());
        if (jsonStr != null) {
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.setName((String) obj.get("name"));
                this.setBirthYear((String) obj.get("birth_year"));
                this.setEyeColor((String) obj.get("eye_color"));
                this.setGender((String) obj.get("gender"));
                this.setHairColor((String) obj.get("hair_color"));
                this.setHeight((String) obj.get("height"));
                this.setMass((String) obj.get("mass"));
                this.setSkinColor((String) obj.get("skin_color"));
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
        result += "\nBirth Year: " + this.getBirthYear();
        result += "\nEye Color: " + this.getEyeColor();
        result += "\nGender: " + this.getGender();
        result += "\nHair Color: " + this.getHairColor();
        result += "\nHeight: " + this.getHeight();
        result += "\nMass: " + this.getMass();
        result += "\nSkin Color: " + this.getSkinColor();
        return result;
    }


    /**
     * Writes this film into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(birthYear);
        parcel.writeString(eyeColor);
        parcel.writeString(gender);
        parcel.writeString(hairColor);
        parcel.writeString(height);
        parcel.writeString(mass);
        parcel.writeString(skinColor);
    }

    /**
     * Creator field for the film class
     */
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the Person from a passed in parcel
     *
     * @param in the parcel to use to recreate the person object
     */
    public Person(Parcel in) {
        super(in);
        this.setBirthYear(in.readString());
        this.setEyeColor(in.readString());
        this.setGender(in.readString());
        this.setHairColor(in.readString());
        this.setHeight(in.readString());
        this.setMass(in.readString());
        this.setSkinColor(in.readString());
    }
}
