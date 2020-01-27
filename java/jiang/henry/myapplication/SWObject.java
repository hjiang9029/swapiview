package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents any object in Swapi
 * Every object contains a created date, edited date and a url
 */
public abstract class SWObject implements Parcelable {

    private String createdDate;
    private String editedDate;
    private String url;

    /**
     * Creates an SWObject with its metadata
     *
     * @param createdDate a string representing the date the object was created in Swapi
     * @param editedDate a string representing the date the object was edited in Swapi
     * @param url a string representing the url of the object in Swapi
     */
    public SWObject(String createdDate, String editedDate, String url) {
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.url = url;
    }

    /**
     * Creates a SWObject with only its URL. It is expected that this constructor
     * will be primarily called by Actors which will then call their unpack method.
     *
     * @param url a string representing the url of the object in Swapi
     */
    public SWObject(String url) {
        this.url = url;
        this.createdDate = "N/A";
        this.editedDate = "N/A";
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getEditedDate() {
        return editedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setEditedDate(String editedDate) {
        this.editedDate = editedDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Default constructor for a SWObject
     */
    public SWObject() {
        this.createdDate = "N/A";
        this.editedDate = "N/A";
        this.url = "N/A";
    }

    // Unused
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes this SWObject to a parcel
     * @param parcel the parcel to write to
     * @param i an int representing any flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdDate);
        parcel.writeString(editedDate);
        parcel.writeString(url);
    }

    /**
     * Creates an SWObject based on the passed in parcel
     * @param in the parcel to use
     */
    protected SWObject(Parcel in) {
        this.createdDate = in.readString();
        this.editedDate = in.readString();
        this.url = in.readString();
    }
}
