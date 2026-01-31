package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LocationApiInterface {

    @GET("locations")
    Call<List<Location>> getLocations();

    @POST("locations")
    Call<List<Location>> registerLocation(@Body Location location);
}
