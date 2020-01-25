package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;


public abstract class SWObject implements Parcelable {

    private String createdDate;
    private String editedDate;
    private String url;

    public SWObject(String createdDate, String editedDate, String url) {
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.url = url;
    }

    public SWObject() {
        this.createdDate = "N/A";
        this.editedDate = "N/A";
        this.url = "N/A";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdDate);
        parcel.writeString(editedDate);
        parcel.writeString(url);
    }

    protected SWObject(Parcel in) {
        this.createdDate = in.readString();
        this.editedDate = in.readString();
        this.url = in.readString();
    }
}
