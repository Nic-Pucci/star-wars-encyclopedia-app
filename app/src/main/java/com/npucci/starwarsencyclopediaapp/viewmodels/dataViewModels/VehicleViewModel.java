package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class VehicleViewModel extends DataViewModel<Vehicle> {
    private SWAPIRepository swapiRepository;

    public VehicleViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Vehicle> getData(int id) {
        return swapiRepository.getVehicle(id);
    }
}