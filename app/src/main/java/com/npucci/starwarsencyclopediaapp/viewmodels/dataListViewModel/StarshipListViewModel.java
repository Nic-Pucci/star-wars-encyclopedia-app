package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.pojos.Starship;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class StarshipListViewModel extends DataListViewModel<DataResults<Starship>> {
    private SWAPIRepository swapiRepository;

    public StarshipListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Starship>> getDataList() {
        return swapiRepository.getAllStarships();
    }

    @Override
    protected LiveData<DataResults<Starship>> getDataList(int[] ids) {
        return swapiRepository.getStarshipList(ids);
    }
}