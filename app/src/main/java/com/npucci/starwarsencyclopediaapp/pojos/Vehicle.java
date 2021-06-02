package com.npucci.starwarsencyclopediaapp.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle extends SWAPIData {
    private String name = "";
    private String model = "";

    @SerializedName("vehicle_class")
    private String vehicleClass = "";

    private String manufacturer = "";
    private String length = "";

    @SerializedName("cost_in_credits")
    private String costInCredits = "";

    private String crew = "";
    private String passengers = "";

    @SerializedName("max_atmosphering_speed")
    private String maxAtmospheringSpeed = "";

    @SerializedName("cargo_capacity")
    private String cargoCapacity = "";

    private String consumables = "";

    @SerializedName("films")
    private String[] filmUrls = new String[0];

    @SerializedName("pilots")
    private String[] pilotUrls = new String[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
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

    public String[] getFilmUrls() {
        return filmUrls;
    }

    public void setFilmUrls(String[] filmUrls) {
        this.filmUrls = filmUrls;
    }

    public int[] getFilmIDs() {
        return extractIDsFromUrls(filmUrls);
    }

    public String[] getPilotUrls() {
        return pilotUrls;
    }

    public void setPilotUrls(String[] pilotUrls) {
        this.pilotUrls = pilotUrls;
    }

    public int[] getPilotIDs() {
        return extractIDsFromUrls(pilotUrls);
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public String getSortValue() {
        return getName();
    }
}