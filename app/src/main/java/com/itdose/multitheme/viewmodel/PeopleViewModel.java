package com.itdose.multitheme.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.itdose.multitheme.data.remote.lib.Resource;
import com.itdose.multitheme.data.remote.model.PeopleResponse;
import com.itdose.multitheme.data.remote.repository.PeopleRepository;

import javax.inject.Inject;
public class PeopleViewModel extends ViewModel {

    public MutableLiveData<Resource<PeopleResponse>> resourceLiveData = new MutableLiveData<>() ;
    private PeopleRepository repository;

    @Inject
    public PeopleViewModel(PeopleRepository repository) {
        this.repository = repository;
        loadPeople(true);
    }

    public void loadPeople(boolean isRandom){
        resourceLiveData = repository.getPeoples(isRandom);
    }
}
