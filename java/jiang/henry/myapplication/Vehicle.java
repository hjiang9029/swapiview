package jiang.henry.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Vehicle extends Actor {

    // Field to use for debugging
    private static final String TAG = Vehicle.class.getSimpleName();

    // strings that describe a vehicle
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String length;
    private String creditCost;
    private String crewCount;
    private String passengerCount;
    private String maxAtmosphereSpeed;
    private String cargoCapacity;
    private String consumables;

    public Vehicle(String url) {
        super(url);
    }

    public Vehicle(String url, String model, String vehicleClass, String manufacturer, String length,
                   String creditCost, String crewCount, String passengerCount, String maxAtmosphereSpeed,
                   String cargoCapacity, String consumables) {
        super(url);
        this.model = model;
        this.vehicleClass = vehicleClass;
        this.manufacturer = manufacturer;
        this.length = length;
        this.creditCost = creditCost;
        this.crewCount = crewCount;
        this.passengerCount = passengerCount;
        this.maxAtmosphereSpeed = maxAtmosphereSpeed;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(String creditCost) {
        this.creditCost = creditCost;
    }

    public String getCrewCount() {
        return crewCount;
    }

    public void setCrewCount(String crewCount) {
        this.crewCount = crewCount;
    }

    public String getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(String passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getMaxAtmosphereSpeed() {
        return maxAtmosphereSpeed;
    }

    public void setMaxAtmosphereSpeed(String maxAtmosphereSpeed) {
        this.maxAtmosphereSpeed = maxAtmosphereSpeed;
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
                this.setVehicleClass((String) obj.get("vehicle_class"));
                this.setManufacturer((String) obj.get("manufacturer"));
                this.setLength((String) obj.get("length"));
                this.setCreditCost((String) obj.get("cost_in_credits"));
                this.setCrewCount((String) obj.get("crew"));
                this.setPassengerCount((String) obj.get("passengers"));
                this.setMaxAtmosphereSpeed((String) obj.get("max_atmosphering_speed"));
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
        result += "\nStarship class: " + this.getVehicleClass();
        result += "\nManufacturer: " + this.getManufacturer();
        result += "\nLength (m): " + this.getLength();
        result += "\nCredit Cost: " + this.getCreditCost();
        result += "\n# of Crew to operate: " + this.getCrewCount();
        result += "\n# of Passengers: " + this.getPassengerCount();
        result += "\nMax Atmosphere Speed: " + this.getMaxAtmosphereSpeed();
        result += "\nCargo Capacity (kg): " + this.getCargoCapacity();
        result += "\nConsumables: " + this.getConsumables();
        return result;
    }


    /**
     * Writes this vehicle into a parcel object. Used when transferring between activities.
     * @param parcel the parcel to write to
     * @param i an int representing flags
     */
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(model);
        parcel.writeString(vehicleClass);
        parcel.writeString(manufacturer);
        parcel.writeString(length);
        parcel.writeString(creditCost);
        parcel.writeString(crewCount);
        parcel.writeString(passengerCount);
        parcel.writeString(maxAtmosphereSpeed);
        parcel.writeString(cargoCapacity);
        parcel.writeString(consumables);
    }

    public static final Parcelable.Creator<Vehicle> CREATOR = new Parcelable.Creator<Vehicle>() {
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    /**
     * Constructor from a parcel. Recreates the Person from a passed in parcel
     *
     * @param in the parcel to use to recreate the person object
     */
    public Vehicle(Parcel in) {
        super(in);
        this.setModel(in.readString());
        this.setVehicleClass(in.readString());
        this.setManufacturer(in.readString());
        this.setLength(in.readString());
        this.setCreditCost(in.readString());
        this.setCrewCount(in.readString());
        this.setPassengerCount(in.readString());
        this.setMaxAtmosphereSpeed(in.readString());
        this.setCargoCapacity(in.readString());
        this.setConsumables(in.readString());
    }
}
