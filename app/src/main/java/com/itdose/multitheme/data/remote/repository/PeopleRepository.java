package com.itdose.multitheme.data.remote.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.itdose.multitheme.core.constant.ConstantVariable;
import com.itdose.multitheme.data.remote.RestApi;
import com.itdose.multitheme.data.remote.lib.NetworkBoundResource;
import com.itdose.multitheme.data.remote.lib.Resource;
import com.itdose.multitheme.data.remote.model.PeopleResponse;

import javax.inject.Inject;

import retrofit2.Call;

public class PeopleRepository {

    private RestApi restApi;

    @Inject
    public PeopleRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    public MutableLiveData<Resource<PeopleResponse>> getPeoples(boolean isRandom){
        return new NetworkBoundResource<PeopleResponse>() {
            @NonNull
            @Override
            protected Call<PeopleResponse> createCall() {
                return restApi.fetchPeople(isRandom?ConstantVariable.RANDOM_USER_URL:ConstantVariable.BASE_URL);
            }
        }.getAsLiveData();
    }

    public MutableLiveData<Resource<PeopleResponse>> getPeoples(){
        return new NetworkBoundResource<PeopleResponse>() {
            @NonNull
            @Override
            protected Call<PeopleResponse> createCall() {
                return restApi.fetchPeople(ConstantVariable.BASE_URL);
            }
        }.getAsLiveData();
    }


}
