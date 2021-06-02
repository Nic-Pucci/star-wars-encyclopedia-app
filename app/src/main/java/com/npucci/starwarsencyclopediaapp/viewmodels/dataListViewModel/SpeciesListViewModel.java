package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Species;
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class SpeciesListViewModel extends DataListViewModel<DataResults<Species>> {
    private SWAPIRepository swapiRepository;

    public SpeciesListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Species>> getDataList() {
        return swapiRepository.getAllSpecies();
    }

    @Override
    protected LiveData<DataResults<Species>> getDataList(int[] ids) {
        return swapiRepository.getSpeciesList(ids);
    }
}