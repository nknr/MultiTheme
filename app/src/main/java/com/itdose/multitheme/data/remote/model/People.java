package com.itdose.multitheme.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class People {
    @SerializedName("gender") public String gender;

    @SerializedName("name") public Name name;

    @SerializedName("location") public Location location;

    @SerializedName("email") public String mail;

    @SerializedName("login") public Login login;

    @SerializedName("phone") public String phone;

    @SerializedName("cell") public String cell;

    @SerializedName("picture") public Picture picture;

}
