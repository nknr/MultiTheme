package com.itdose.multitheme.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.itdose.multitheme.data.remote.lib.Resource;
import com.itdose.multitheme.data.remote.model.PeopleResponse;
import com.itdose.multitheme.data.remote.repository.PeopleRepository;

public class PeopleViewModel extends ViewModel {

    public MutableLiveData<Resource<PeopleResponse>> resourceLiveData = new MutableLiveData<>() ;
    private PeopleRepository repository;

    @ViewModelInject
    public PeopleViewModel(PeopleRepository repository) {
        this.repository = repository;
        loadPeople(true);
    }

    public void loadPeople(boolean isRandom){
        resourceLiveData = repository.getPeoples(isRandom);
    }
}
