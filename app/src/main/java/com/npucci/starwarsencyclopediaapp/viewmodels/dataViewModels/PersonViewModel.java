package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;

import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

public class PersonViewModel extends DataViewModel<Person> {
    private SWAPIRepository swapiRepository;

    public PersonViewModel(){
        super();

        swapiRepository = SWAPIRepository.getInstance();
    }

    @Override
    protected LiveData<Person> getData(int id) {
        return swapiRepository.getCharacter(id);
    }
}