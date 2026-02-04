package com.example.cityeventsandr.api;

import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtistApiInterface {

    @GET("artists")
    Call<List<Artist>> getArtists();

    @GET("artists/{id}")
    Call<Artist> getArtistById(@Path("id") long id);

    @POST("artists")
    Call<Artist> registerArtist(@Body Artist artist);

    @PUT("artists/{id}")
    Call<Artist> modifyArtistById(@Path("id") long id, @Body Artist artist);

    @DELETE("artists/{id}")
    Call<Void> deleteArtist(@Path("id") long id);
}
