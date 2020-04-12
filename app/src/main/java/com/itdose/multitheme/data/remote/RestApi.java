package com.itdose.multitheme.data.remote;


import com.itdose.multitheme.data.remote.model.PeopleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestApi {

    @GET
    Call<PeopleResponse> fetchPeople(@Url String url);

}
