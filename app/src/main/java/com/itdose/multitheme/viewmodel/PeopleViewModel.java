package com.itdose.multitheme.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.itdose.multitheme.data.remote.lib.Resource;
import com.itdose.multitheme.data.remote.lib.Status;
import com.itdose.multitheme.data.remote.model.People;
import com.itdose.multitheme.data.remote.model.PeopleResponse;
import com.itdose.multitheme.data.remote.repository.PeopleRepository;

import java.util.List;

import javax.inject.Inject;
public class PeopleViewModel extends ViewModel {

    public LiveData<Resource<PeopleResponse>> resourceLiveData;
    private PeopleRepository repository;

    @Inject
    public PeopleViewModel(PeopleRepository repository) {
        this.repository = repository;
        resourceLiveData = repository.getRandomPeoples();

    }

    public void addPeople() {
        resourceLiveData = repository.getPeoples();
    }
}
