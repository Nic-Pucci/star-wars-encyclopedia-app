package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.npucci.starwarsencyclopediaapp.pojos.SWAPIData;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;

import java.util.List;

import timber.log.Timber;

public abstract class DataListViewModel<T extends DataResults> extends ViewModel {
    private MediatorLiveData<T> dataLive = new MediatorLiveData();

    public DataListViewModel(){
        super();
    }

    public LiveData<T> getDataListLive(){
        return getDataListLive(null);
    }

    public LiveData<T> getDataListLive(int[] ids){
        LiveData<T> getData = getData(ids);
        dataLive.addSource(getData, data -> {
            dataLive.removeSource(getData);

            if(data == null) {
                Timber.e("API returned a null data list!");
                return;
            }

            dataLive.setValue(data);
        });

        return dataLive;
    }

    private LiveData<T> getData(int[] ids){
        if(ids == null || ids.length == 0) {
            return getDataList();
        }
        else {
            return getDataList(ids);
        }
    }

    protected abstract LiveData<T> getDataList();

    protected abstract LiveData<T> getDataList(int[] ids);
}