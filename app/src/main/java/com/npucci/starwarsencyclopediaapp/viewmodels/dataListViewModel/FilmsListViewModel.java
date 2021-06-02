package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.pojos.SWAPIData;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.DataListViewModel;

import java.util.List;
import java.util.stream.Collectors;

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