package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiInterface {

    @GET("locations")
    Call<List<Location>> getLocations();
}
