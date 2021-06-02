package com.npucci.starwarsencyclopediaapp.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Person extends SWAPIData {
    private String name = "";

    @SerializedName("birth_year")
    private String birthYear = "";

    @SerializedName("eye_color")
    private String eyeColor = "";

    private String gender = "";

    @SerializedName("hair_color")
    private String hairColor = "";

    private String height = "";
    private String mass = "";

    @SerializedName("skin_color")
    private String skinColor = "";

    private String homeworld = "";

    @SerializedName("films")
    private String[] filmUrls = new String[0];

    @SerializedName("species")
    private String[] speciesUrls = new String[0];

    @SerializedName("starships")
    private String[] starshipUrls = new String[0];

    @SerializedName("vehicles")
    private String[] vehicleUrls = new String[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
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

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public int[] getHomeworldIDs() {
        return extractIDsFromUrls(homeworld);
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

    public String[] getSpeciesUrls() {
        return speciesUrls;
    }

    public void setSpeciesUrls(String[] speciesUrls) {
        this.speciesUrls = speciesUrls;
    }

    public int[] getSpeciesIDs() {
        return extractIDsFromUrls(speciesUrls);
    }

    public String[] getStarshipUrls() {
        return starshipUrls;
    }

    public void setStarshipUrls(String[] starshipUrls) {
        this.starshipUrls = starshipUrls;
    }

    public int[] getStarshipIDs() {
        return extractIDsFromUrls(starshipUrls);
    }

    public String[] getVehicleUrls() {
        return vehicleUrls;
    }

    public void setVehicleUrls(String[] vehicleUrls) {
        this.vehicleUrls = vehicleUrls;
    }

    public int[] getVehicleIDs() {
        return extractIDsFromUrls(vehicleUrls);
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
