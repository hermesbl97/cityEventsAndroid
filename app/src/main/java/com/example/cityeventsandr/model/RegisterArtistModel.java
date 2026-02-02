package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.ArtistApiInterface;
import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.contract.RegisterArtistContract;
import com.example.cityeventsandr.domain.Artist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterArtistModel implements RegisterArtistContract.Model {
    @Override
    public void registerArtist(Artist artist, OnRegisterListener listener) {
        ArtistApiInterface artistApi = CityEventsApi.buildService(ArtistApiInterface.class);
        Call<Artist> registerArtistCall = artistApi.registerArtist(artist);
        registerArtistCall.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                }else if (response.code() == 400) {
                    listener.onRegisterError("Error de validación");
                } else if (response.code() == 404) {
                    listener.onRegisterError("Artista no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }
}
