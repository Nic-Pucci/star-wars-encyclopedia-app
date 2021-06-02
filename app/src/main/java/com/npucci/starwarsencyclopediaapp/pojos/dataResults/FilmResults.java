package com.npucci.starwarsencyclopediaapp.pojos.dataResults;

import com.google.gson.annotations.SerializedName;
import com.npucci.starwarsencyclopediaapp.pojos.Film;

public class FilmResults {
    private int count;

    @SerializedName("results")
    private Film[] films;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Film[] getFilms() {
        return films;
    }

    public void setFilms(Film[] films) {
        this.films = films;
    }
}
