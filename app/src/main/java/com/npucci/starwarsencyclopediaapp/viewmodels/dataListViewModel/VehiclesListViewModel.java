package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class VehiclesListViewModel extends DataListViewModel<DataResults<Vehicle>> {
    private SWAPIRepository swapiRepository;

    public VehiclesListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Vehicle>> getDataList() {
        return swapiRepository.getAllVehicles();
    }

    @Override
    protected LiveData<DataResults<Vehicle>> getDataList(int[] ids) {
        return swapiRepository.getVehicleList(ids);
    }
}