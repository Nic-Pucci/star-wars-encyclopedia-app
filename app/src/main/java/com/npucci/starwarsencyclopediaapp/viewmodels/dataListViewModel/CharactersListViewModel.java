package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.DataListViewModel;

import java.util.Arrays;
import java.util.List;

public class CharactersListViewModel extends DataListViewModel<DataResults<Person>> {
    private SWAPIRepository swapiRepository;

    public CharactersListViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<DataResults<Person>> getDataList() {
        return swapiRepository.getAllCharacters();
    }

    @Override
    protected LiveData<DataResults<Person>> getDataList(int[] ids) {
        return swapiRepository.getCharacterList(ids);
    }
}