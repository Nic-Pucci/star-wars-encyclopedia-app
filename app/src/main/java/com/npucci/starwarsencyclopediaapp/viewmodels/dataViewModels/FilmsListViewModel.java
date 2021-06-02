package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.DataListViewModel;

import java.util.Arrays;
import java.util.List;

public class FilmsListViewModel extends DataListViewModel<DataResults<Film>> {
    private SWAPIRepository swapiRepository;

    public FilmsListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Film>> getDataList() {
        return swapiRepository.getAllFilms();
    }

    @Override
    protected LiveData<DataResults<Film>> getDataList(int[] ids) {
        return swapiRepository.getFilmList(ids);
    }
}