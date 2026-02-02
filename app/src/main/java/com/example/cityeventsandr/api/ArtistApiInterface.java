package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ArtistApiInterface {

    @GET("artists")
    Call<List<Artist>> getArtists();

    @POST("artists")
    Call<Artist> registerArtist(@Body Artist artist);
}
