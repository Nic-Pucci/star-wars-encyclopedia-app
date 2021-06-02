package com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;

import timber.log.Timber;

public abstract class DataListViewModel<T extends DataResults> extends ViewModel {
    private MediatorLiveData<T> dataResultsLive = new MediatorLiveData();

    public DataListViewModel(){
        super();
    }

    public LiveData<T> updateDataResultsLive(){
        return updateDataResultsLive(null);
    }

    public LiveData<T> updateDataResultsLive(int[] ids){
        LiveData<T> fullDataList = getData(ids);
        dataResultsLive.addSource(fullDataList, data -> {
            dataResultsLive.removeSource(fullDataList);

            if(data == null) {
                Timber.e("API returned a null data list!");
                return;
            }

            dataResultsLive.setValue(data);
        });

        return dataResultsLive;
    }

    public LiveData<T> getDataResultsLive(){
        return dataResultsLive;
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