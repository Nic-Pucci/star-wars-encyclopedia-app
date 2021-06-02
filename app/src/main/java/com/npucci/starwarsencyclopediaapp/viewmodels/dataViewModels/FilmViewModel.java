package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class FilmViewModel extends DataViewModel<Film> {
    private SWAPIRepository swapiRepository;

    public FilmViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Film> getData(int id) {
        return swapiRepository.getFilm(id);
    }
}