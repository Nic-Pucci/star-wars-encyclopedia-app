package com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.npucci.starwarsencyclopediaapp.pojos.SWAPIData;

import timber.log.Timber;

public abstract class DataViewModel<T extends SWAPIData> extends ViewModel {
    private MediatorLiveData<T> dataLive = new MediatorLiveData();
    private MutableLiveData<String> errorLive = new MutableLiveData();

    public DataViewModel(){
        super();
    }

    public LiveData<T> updateDataLive(int id){
        LiveData<T> getData = getData(id);
        dataLive.addSource(getData, data -> {
            dataLive.removeSource(getData);

            if(data == null) {
                Timber.e("API returned a null data!");
                errorLive.setValue("Error: Could Not Retrieve Data!");
                return;
            }

            dataLive.setValue(data);
        });

        return dataLive;
    }

    protected abstract LiveData<T> getData(int id);

    public LiveData<String> getErrorLive(){
        return errorLive;
    }
}