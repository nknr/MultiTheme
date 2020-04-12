package com.itdose.multitheme.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class PeopleResponse {
    @SerializedName("results")
    private List<People> peopleList;

    public List<People> getPeopleList() {
        return peopleList == null ? Collections.emptyList() : peopleList;
    }
}
