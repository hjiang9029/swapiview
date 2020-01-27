package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Starship extends Actor {
    // Field to use for debugging
    private static final String TAG = Starship.class.getSimpleName();

    // strings that describe a starship
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
    public void unpackFromURL() {
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
                this.setCreatedDate((String) obj.get("created"));
                this.setEditedDate((String) obj.get("edited"));
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
        result += "\nModel: " + this.getModel();
        result += "\nStarship class: " + this.getModelClass();
        result += "\nManufacturer: " + this.getManufacturer();
        result += "\nCredit Cost: " + this.getCreditCost();
        result += "\nLength (m): " + this.getLength();
        result += "\n# of Crew to operate: " + this.getCrewCount();
        result += "\n# of Passengers: " + this.getPassengers();
        result += "\nMax Atmosphere Speed: " + this.getMaxAtmosphereSpeed();
        result += "\nHyperdrive Rating: " + this.getHyperdriveRating();
        result += "\nMaximum number of Megalights per hour: " + this.getMGLT();
        result += "\nCargo Capacity (kg): " + this.getCargoCapacity();
        result += "\nConsumables: " + this.getConsumables();
        return result;
    }

    /**
     * Writes this starship into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(model);
        parcel.writeString(modelClass);
        parcel.writeString(manufacturer);
        parcel.writeString(creditCost);
        parcel.writeString(length);
        parcel.writeString(crewCount);
        parcel.writeString(passengers);
        parcel.writeString(maxAtmosphereSpeed);
        parcel.writeString(hyperdriveRating);
        parcel.writeString(MGLT);
        parcel.writeString(cargoCapacity);
        parcel.writeString(consumables);
    }

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
        this.setModel(in.readString());
        this.setModelClass(in.readString());
        this.setManufacturer(in.readString());
        this.setCreditCost(in.readString());
        this.setLength(in.readString());
        this.setCrewCount(in.readString());
        this.setPassengers(in.readString());
        this.setMaxAtmosphereSpeed(in.readString());
        this.setHyperdriveRating(in.readString());
        this.setMGLT(in.readString());
        this.setCargoCapacity(in.readString());
        this.setConsumables(in.readString());
    }
}
