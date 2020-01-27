package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Species extends Actor {

    // Field to use for debugging
    private static String TAG = Species.class.getSimpleName();

    // strings that describe this species
    private String classification;
    private String designation;
    private String averageHeight;
    private String averageLife;
    private String eyeColor;
    private String hairColor;
    private String skinColor;
    private String language;

    public Species(String url) {
        super(url);
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    public String getAverageLife() {
        return averageLife;
    }

    public void setAverageLife(String averageLife) {
        this.averageLife = averageLife;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void unpackFromURL() {
        String jsonStr = Parser.getInstance().makeServiceCall(this.getUrl());
        if (jsonStr != null) {
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.setName((String) obj.get("name"));
                this.setClassification((String) obj.get("classification"));
                this.setDesignation((String) obj.get("designation"));
                this.setAverageHeight((String) obj.get("average_height"));
                this.setAverageLife((String) obj.get("average_lifespan"));
                this.setEyeColor((String) obj.get("eye_colors"));
                this.setHairColor((String) obj.get("hair_colors"));
                this.setSkinColor((String) obj.get("skin_colors"));
                this.setLanguage((String) obj.get("language"));
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
        result += "\nClassification: " + this.getClassification();
        result += "\nDesignation: " + this.getDesignation();
        result += "\nAverage Height: " + this.getAverageHeight() + "cm";
        result += "\nAverage Lifespan: " + this.getAverageLife() + " years";
        result += "\nEye colour(s): " + this.getEyeColor();
        result += "\nHair colour(s): " + this.getHairColor();
        result += "\nSkin colour(s): " + this.getSkinColor();
        result += "\nLanguages: " + this.getLanguage();
        return result;
    }

    /**
     * Writes this species into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(classification);
        parcel.writeString(designation);
        parcel.writeString(averageHeight);
        parcel.writeString(averageLife);
        parcel.writeString(eyeColor);
        parcel.writeString(hairColor);
        parcel.writeString(skinColor);
        parcel.writeString(language);
    }

    public static final Parcelable.Creator<Species> CREATOR = new Parcelable.Creator<Species>() {
        public Species createFromParcel(Parcel in) {
            return new Species(in);
        }

        public Species[] newArray(int size) {
            return new Species[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the Person from a passed in parcel
     *
     * @param in the parcel to use to recreate the person object
     */
    public Species(Parcel in) {
        super(in);
        this.setClassification(in.readString());
        this.setDesignation(in.readString());
        this.setAverageHeight(in.readString());
        this.setAverageLife(in.readString());
        this.setEyeColor(in.readString());
        this.setHairColor(in.readString());
        this.setSkinColor(in.readString());
        this.setLanguage(in.readString());
    }
}
