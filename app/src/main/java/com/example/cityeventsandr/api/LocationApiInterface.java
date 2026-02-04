package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LocationApiInterface {

    @GET("locations")
    Call<List<Location>> getLocations();

    @GET("locations/{id}")
    Call<Location> getLocationById(@Path("id") long id);

    @POST("locations")
    Call<Location> registerLocation(@Body Location location);

    @PUT("locations/{id}")
    Call<Location> modifyLocation(@Path("id") long id, @Body Location Location);

    @DELETE("locations/{id}")
    Call<Void> deleteLocation(@Path("id") long id);
}
