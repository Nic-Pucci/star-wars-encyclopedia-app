package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class PlanetViewModel extends DataViewModel<Planet> {
    private SWAPIRepository swapiRepository;

    public PlanetViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Planet> getData(int id) {
        return swapiRepository.getPlanet(id);
    }
}