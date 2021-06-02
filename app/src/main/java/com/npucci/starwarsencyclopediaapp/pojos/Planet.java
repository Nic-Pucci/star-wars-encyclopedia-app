package com.npucci.starwarsencyclopediaapp.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Planet extends SWAPIData {
    private String name = "";
    private String diameter = "";

    @SerializedName("rotation_period")
    private String rotationPeriod = "";

    @SerializedName("orbital_period")
    private String orbitalPeriod = "";

    private String gravity = "";
    private String population = "";
    private String climate = "";
    private String terrain = "";

    @SerializedName("surface_water")
    private String surfaceWater = "";

    @SerializedName("residents")
    private String[] residentUrls = new String[0];

    @SerializedName("films")
    private String[] filmUrls = new String[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String[] getResidentUrls() {
        return residentUrls;
    }

    public void setResidentUrls(String[] residentUris) {
        this.residentUrls = residentUris;
    }

    public int[] getResidentIDs() {
        return extractIDsFromUrls(residentUrls);
    }

    public String[] getFilmUrls() {
        return filmUrls;
    }

    public void setFilmUrls(String[] filmUris) {
        this.filmUrls = filmUris;
    }

    public int[] getFilmIDs() {
        return extractIDsFromUrls(filmUrls);
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