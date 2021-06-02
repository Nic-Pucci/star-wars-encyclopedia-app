package com.npucci.starwarsencyclopediaapp.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Species extends SWAPIData {
    private String name = "";
    private String classification = "";
    private String designation = "";

    @SerializedName("average_height")
    private String averageHeight = "";

    @SerializedName("average_lifespan")
    private String averageLifespan = "";

    @SerializedName("eye_colors")
    private String eyeColors = "";

    @SerializedName("hair_colors")
    private String hairColors = "";

    @SerializedName("skin_colors")
    private String skinColors = "";

    private String language = "";
    private String homeworld = "";

    @SerializedName("people")
    private String[] peopleUrls = new String[0];

    @SerializedName("films")
    private String[] filmUrls = new String[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(String averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(String eyeColors) {
        this.eyeColors = eyeColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(String hairColors) {
        this.hairColors = hairColors;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(String skinColors) {
        this.skinColors = skinColors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String[] getPeopleUrls() {
        return peopleUrls;
    }

    public void setPeopleUrls(String[] peopleUrls) {
        this.peopleUrls = peopleUrls;
    }

    public int[] getPeopleIDs() {
        return extractIDsFromUrls(peopleUrls);
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

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public String getSortValue() {
        return getName();
    }
}
