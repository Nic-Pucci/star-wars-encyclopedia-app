package com.npucci.starwarsencyclopediaapp.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Film extends SWAPIData {
    private String title = "";

    @SerializedName("episode_id")
    private String episodeID = "";

    @SerializedName("opening_crawl")
    private String openingCrawl = "";

    private String director = "";
    private String producer = "";

    @SerializedName("release_date")
    private String releaseDate  = "";

    @SerializedName("species")
    private String[] speciesUrls = new String[0];

    @SerializedName("starships")
    private String[] starshipUrls = new String[0];

    @SerializedName("vehicles")
    private String[] vehicleUrls = new String[0];

    @SerializedName("characters")
    private String[] characterUrls = new String[0];

    @SerializedName("planets")
    private String[] planetUrls = new String[0];

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisodeID() {
        return episodeID;
    }

    public void setEpisodeID(String episodeID) {
        this.episodeID = episodeID;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public String[] getCharacterUrls() {
        return characterUrls;
    }

    public void setCharacterUrls(String[] characterUrls) {
        this.characterUrls = characterUrls;
    }

    public int[] getCharacterIDs() {
        return extractIDsFromUrls(characterUrls);
    }

    public String[] getPlanetUrls() {
        return planetUrls;
    }

    public void setPlanetUrls(String[] planetUrls) {
        this.planetUrls = planetUrls;
    }

    public int[] getPlanetIDs() {
        return extractIDsFromUrls(planetUrls);
    }

    @NonNull
    public String getDisplayName() {
        return "Star Wars: " + getTitle() + " (Episode " + getEpisodeID() + ")";
    }

    @Override
    public String getSortValue() {
        return episodeID;
    }
}
