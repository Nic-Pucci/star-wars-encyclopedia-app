package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class PlanetListViewModel extends DataListViewModel<DataResults<Planet>> {
    private SWAPIRepository swapiRepository;

    public PlanetListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Planet>> getDataList() {
        return swapiRepository.getAllPlanets();
    }

    @Override
    protected LiveData<DataResults<Planet>> getDataList(int[] ids) {
        return swapiRepository.getPlanetList(ids);
    }
}