package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Starship;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class StarshipViewModel extends DataViewModel<Starship> {
    private SWAPIRepository swapiRepository;

    public StarshipViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Starship> getData(int id) {
        return swapiRepository.getStarship(id);
    }
}