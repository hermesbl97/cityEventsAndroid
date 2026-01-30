package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventApiInterface {
    @GET("events")
    Call<List<Event>> getEvents();

    @POST("events")
    Call<Event> registerEvent(@Body Event event);

    @GET("events/{id}")
    Call<Event> getEventById(@Path("id") long id);

}
