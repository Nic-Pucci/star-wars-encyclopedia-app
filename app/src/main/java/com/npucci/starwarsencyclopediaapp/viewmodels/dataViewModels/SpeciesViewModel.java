package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Species;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class SpeciesViewModel extends DataViewModel<Species> {
    private SWAPIRepository swapiRepository;

    public SpeciesViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Species> getData(int id) {
        return swapiRepository.getSpecies(id);
    }
}