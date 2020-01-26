package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO: Parceable packing and unpacking & Description
public class Starship extends Actor {
    // Field to use for debugging
    private static String TAG = Starship.class.getSimpleName();
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelClass() {
        return modelClass;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(String creditCost) {
        this.creditCost = creditCost;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCrewCount() {
        return crewCount;
    }

    public void setCrewCount(String crewCount) {
        this.crewCount = crewCount;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getMaxAtmosphereSpeed() {
        return maxAtmosphereSpeed;
    }

    public void setMaxAtmosphereSpeed(String maxAtmosphereSpeed) {
        this.maxAtmosphereSpeed = maxAtmosphereSpeed;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public String getMGLT() {
        return MGLT;
    }

    public void setMGLT(String MGLT) {
        this.MGLT = MGLT;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    @Override
    void unpackFromURL() {
        String jsonStr = Parser.getInstance().makeServiceCall(this.getUrl());
        if (jsonStr != null) {
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.setName((String) obj.get("name"));
                this.setModel((String) obj.get("model"));
                this.setModelClass((String) obj.get("starship_class"));
                this.setManufacturer((String) obj.get("manufacturer"));
                this.setCreditCost((String) obj.get("cost_in_credits"));
                this.setLength((String) obj.get("length"));
                this.setCrewCount((String) obj.get("crew"));
                this.setPassengers((String) obj.get("passengers"));
                this.setMaxAtmosphereSpeed((String) obj.get("max_atmosphering_speed"));
                this.setHyperdriveRating((String) obj.get("hyperdrive_rating"));
                this.setMGLT((String) obj.get("MGLT"));
                this.setCargoCapacity((String) obj.get("cargo_capacity"));
                this.setConsumables((String) obj.get("consumables"));
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred when parsing from json response");
            }
        } else {
            Log.e(TAG, "An error occurred when unpacking from URL");
        }
    }

    @Override
    String printDescription() {
        return null;
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
