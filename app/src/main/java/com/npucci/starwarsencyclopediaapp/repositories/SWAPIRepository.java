package com.npucci.starwarsencyclopediaapp.repositories;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.core.util.Supplier;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.pojos.SWAPIData;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.FilmResults;
import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.pojos.Species;
import com.npucci.starwarsencyclopediaapp.pojos.Starship;
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import timber.log.Timber;

public class SWAPIRepository {
    private static final String BASE_URL = "https://swapi.dev/api/";
    private static final int NUMBER_OF_THREADS = 4;
    private static final int TIMEOUT_SECONDS = 60;

    private static SWAPIRepository instance;

    private final SWAPIServices swapiServices;
    public final ExecutorService exportWriteExecutor;

    public static SWAPIRepository getInstance(){
        synchronized (SWAPIRepository.class){
            if(instance == null) {
                instance = new SWAPIRepository();
            }

            return instance;
        }
    }

    private SWAPIRepository(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        swapiServices = retrofit.create(SWAPIServices.class);

        exportWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    public LiveData<DataResults<Film>> getAllFilms(){
        return getDataListAsync(swapiServices::getAllFilms);
    }

    public LiveData<DataResults<Person>> getAllCharacters(){
        return getDataListAsync(swapiServices::getAllPeople);
    }

    public LiveData<DataResults<Starship>> getAllStarships(){
        return getDataListAsync(swapiServices::getAllStarships);
    }

    public LiveData<DataResults<Species>> getAllSpecies(){
        return getDataListAsync(swapiServices::getAllSpecies);
    }

    public LiveData<DataResults<Planet>> getAllPlanets(){
        return getDataListAsync(swapiServices::getAllPlanets);
    }

    public LiveData<DataResults<Vehicle>> getAllVehicles(){
        return getDataListAsync(swapiServices::getAllVehicles);
    }

    public LiveData<DataResults<Film>> getFilmList(int[] filmIDs){
        return getDataListAsync(filmIDs, (Integer filmID) -> swapiServices.getFilm(filmID));
    }

    public LiveData<DataResults<Species>> getSpeciesList(int[] speciesIDs){
        return getDataListAsync(speciesIDs, (Integer speciesID) -> swapiServices.getSpecies(speciesID));
    }

    public LiveData<DataResults<Planet>> getPlanetList(int[] planetIDs){
        return getDataListAsync(planetIDs, (Integer planetID) -> swapiServices.getPlanet(planetID));
    }

    public LiveData<DataResults<Vehicle>> getVehicleList(int[] vehicleIDs){
        return getDataListAsync(vehicleIDs, (Integer vehicleID) -> swapiServices.getVehicle(vehicleID));
    }

    public LiveData<DataResults<Person>> getCharacterList(int[] peopleIDs){
        return getDataListAsync(peopleIDs, (Integer personID) -> swapiServices.getPerson(personID));
    }

    public LiveData<DataResults<Starship>> getStarshipList(int[] shipIDs){
        return getDataListAsync(shipIDs, (Integer shipID) -> swapiServices.getStarship(shipID));
    }

    public LiveData<Person> getCharacter(int id){
        return getDataAsync(() -> swapiServices.getPerson(id));
    }

    public LiveData<Film> getFilm(int id){
        return getDataAsync(() -> swapiServices.getFilm(id));
    }

    public LiveData<Planet> getPlanet(int id){
        return getDataAsync(() -> swapiServices.getPlanet(id));
    }

    public LiveData<Species> getSpecies(int id){
        return getDataAsync(() -> swapiServices.getSpecies(id));
    }

    public LiveData<Starship> getStarship(int id){
        return getDataAsync(() -> swapiServices.getStarship(id));
    }

    public LiveData<Vehicle> getVehicle(int id){
        return getDataAsync(() -> swapiServices.getVehicle(id));
    }

    private <T extends SWAPIData> LiveData<T> getDataAsync(@NonNull Supplier<Call<T>> callSupplier){
        MutableLiveData<T> dataLive = new MutableLiveData();

        callSupplier.get().enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T data = response.body();
                dataLive.postValue(data);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Timber.e(t);
            }
        });

        return dataLive;
    }

    private <T extends SWAPIData> LiveData<DataResults<T>> getDataListAsync(@NonNull Supplier<Call<DataResults<T>>> callSupplier){
        MutableLiveData<DataResults<T>> dataListLive = new MutableLiveData();

        callSupplier.get().enqueue(new Callback<DataResults<T>>() {
            @Override
            public void onResponse(Call<DataResults<T>> call, Response<DataResults<T>> response) {
                DataResults<T> dataResults = response.body();

                List<T> dataList = dataResults.getResults();
                //Collections.sort(dataList, ((item1, item2) -> item1.getSortValue().compareToIgnoreCase(item2.getSortValue())));

                dataResults.setResults(dataList);
                dataListLive.postValue(dataResults);
            }

            @Override
            public void onFailure(Call<DataResults<T>> call, Throwable t) {
                Timber.e(t);
            }
        });

        return dataListLive;
    }

    private <T extends SWAPIData> LiveData<DataResults<T>> getDataListAsync(int[] dataIDs, @NonNull Function<Integer, Call<T>> callSupplier){
        MutableLiveData<DataResults<T>> dataListLiveData = new MutableLiveData();
        exportWriteExecutor.execute(() -> {
            List<T> dataList = new ArrayList<>();

            for(int dataID : dataIDs) {
                Call<T> personCallSync = callSupplier.apply(dataID);

                try{
                    Response<T> response = personCallSync.execute();
                    T data = response.body();
                    dataList.add(data);
                }
                catch (IOException e) {
                    Timber.e(e);
                }
            }

            Collections.sort(dataList, ((item1, item2) -> item1.getSortValue().compareToIgnoreCase(item2.getSortValue())));

            DataResults<T> dataResults = new DataResults<T>();
            dataResults.setResults(dataList);

            int count = dataList.size();
            dataResults.setCount(count);

            dataListLiveData.postValue(dataResults);
        });

        return dataListLiveData;
    }

    private interface SWAPIServices {
        @GET("films/")
        Call<DataResults<Film>> getAllFilms();

        @GET("films/{id}")
        Call<Film> getFilm(@Path("id") int id);

        @GET("people/")
        Call<DataResults<Person>> getAllPeople();

        @GET("people/{id}")
        Call<Person> getPerson(@Path("id") int id);

        @GET("planets/")
        Call<DataResults<Planet>> getAllPlanets();

        @GET("planets/{id}")
        Call<Planet> getPlanet(@Path("id") int id);

        @GET("species/")
        Call<DataResults<Species>> getAllSpecies();

        @GET("species/{id}")
        Call<Species> getSpecies(@Path("id") int id);

        @GET("starships/")
        Call<DataResults<Starship>> getAllStarships();

        @GET("starships/{id}")
        Call<Starship> getStarship(@Path("id") int id);

        @GET("vehicles/")
        Call<DataResults<Vehicle>> getAllVehicles();

        @GET("vehicles/{id}")
        Call<Vehicle> getVehicle(@Path("id") int id);
    }
}
