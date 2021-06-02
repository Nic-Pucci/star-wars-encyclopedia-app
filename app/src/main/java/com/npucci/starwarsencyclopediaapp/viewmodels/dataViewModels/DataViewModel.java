package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.npucci.starwarsencyclopediaapp.pojos.SWAPIData;

import timber.log.Timber;

public abstract class DataViewModel<T extends SWAPIData> extends ViewModel {
    private MediatorLiveData<T> dataLive = new MediatorLiveData();

    public DataViewModel(){
        super();
    }

    public LiveData<T> getDataLive(int id){
        LiveData<T> getData = getData(id);
        dataLive.addSource(getData, data -> {
            dataLive.removeSource(getData);

            if(data == null) {
                Timber.e("API returned a null data!");
                return;
            }

            dataLive.setValue(data);
        });

        return dataLive;
    }

    protected abstract LiveData<T> getData(int id);

//    private void getCharacterListData(int[] characterIDs){
//        LiveData<List<Person>> getCharacterListData = swapiRepository.getCharacterList(characterIDs);
//        characterIDsListLive.addSource(getCharacterListData, (List<Person> characters) -> {
//            characterIDsListLive.removeSource(getCharacterListData);
//
//            if(characters == null){
//                return;
//            }
//
//            Collections.sort(characters, (item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
//            characterIDsListLive.setValue(characters);
//        });
//    }

//    private void getStarshipListData(int[] starshipIDs){
//        LiveData<List<Starship>> getStarshipListData = swapiRepository.getShipList(starshipIDs);
//        starshipListLive.addSource(getStarshipListData, (List<Starship> starships) -> {
//            starshipListLive.removeSource(getStarshipListData);
//
//            if(starships == null){
//                return;
//            }
//
//            Collections.sort(starships, (item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
//            starshipListLive.setValue(starships);
//        });
//    }
//
//    private void getSpeciesListData(int[] speciesIDs){
//        LiveData<List<Species>> getSpeciesListData = swapiRepository.getSpeciesList(speciesIDs);
//        speciesListLive.addSource(getSpeciesListData, (List<Species> species) -> {
//            speciesListLive.removeSource(getSpeciesListData);
//
//            if(species == null){
//                return;
//            }
//
//            Collections.sort(species, (item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
//            speciesListLive.setValue(species);
//        });
//    }
//
//    private void getPlanetListData(int[] planetIDs){
//        LiveData<List<Planet>> getPlanetListData = swapiRepository.getPlanetList(planetIDs);
//        planetListLive.addSource(getPlanetListData, (List<Planet> planets) -> {
//            planetListLive.removeSource(getPlanetListData);
//
//            if(planets == null){
//                return;
//            }
//
//            Collections.sort(planets, (item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
//            planetListLive.setValue(planets);
//        });
//    }
//
//    private void getVehicleListData(int[] vehicleIDs){
//        LiveData<List<Vehicle>> getVehicleListData = swapiRepository.getVehicleList(vehicleIDs);
//        vehicleListLive.addSource(getVehicleListData, (List<Vehicle> vehicles) -> {
//            vehicleListLive.removeSource(getVehicleListData);
//
//            if(vehicles == null){
//                return;
//            }
//
//            Collections.sort(vehicles, (item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
//            vehicleListLive.setValue(vehicles);
//        });
//    }

}