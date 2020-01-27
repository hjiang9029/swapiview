package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a person or character in the Star Wars franchise
 */
public class Person extends Actor {

    // Field to use for debugging
    private static String TAG = Person.class.getSimpleName();

    // strings that describe this person
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;

    /**
     * Constructs a person using only their swapi url
     * Expected that unpackFromURL will be called in the future
     * @param url a string representing the url to call for unpacking
     */
    public Person(String url) {
        super(url);
    }

    /**
     * complete constructor for a person, requires all 'relevant' fields to not be null
     * @param name a string representing the name
     * @param created a string representing the date this swapi object was created
     * @param edited a string representing the date this swapi object was edited
     * @param url a string representing the url of this swapi object
     * @param height a string representing the height of this person
     * @param mass a string representing the mass of this person
     * @param hairColor a string representing the hair colour of this person
     * @param skinColor a string representing the skin color of this person
     * @param eyeColor a string representing the eye color of this person
     * @param birthYear a string representing the birth year of this person
     * @param gender a string representing the gender of this person
     */
    public Person(String name, String created, String edited, String url, String height,
                  String mass, String hairColor, String skinColor, String eyeColor,
                  String birthYear, String gender) {
        super(name, created, edited, url);
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void unpackFromURL() {
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
    public String printDescription() {
        String result = "";
        result += "\nName: " + this.getName();
        result += "\nBirth Year: " + this.getBirthYear();
        result += "\nEye Color: " + this.getEyeColor();
        result += "\nGender: " + this.getGender();
        result += "\nHair Color: " + this.getHairColor();
        result += "\nHeight: " + this.getHeight() + "cm";
        result += "\nMass: " + this.getMass() + "kg";
        result += "\nSkin Color: " + this.getSkinColor();
        return result;
    }


    /**
     * Writes this person into a parcel object. Used when transferring between activities.
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
