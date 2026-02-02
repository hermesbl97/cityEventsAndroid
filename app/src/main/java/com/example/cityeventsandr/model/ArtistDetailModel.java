package com.example.cityeventsandr.model;


import com.example.cityeventsandr.api.ArtistApiInterface;
import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.contract.ArtistDetailContract;
import com.example.cityeventsandr.domain.Artist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDetailModel implements ArtistDetailContract.Model {
    @Override
    public void loadArtistById(long id, OnLoadListener listener) {
        ArtistApiInterface artistApi = CityEventsApi.buildService(ArtistApiInterface.class);
        Call<Artist> getArtistCallById = artistApi.getArtistById(id);
        getArtistCallById.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if (response.code() == 200) {
                    listener.onLoadSuccess(response.body());
                }else if (response.code() == 404) {
                    listener.onLoadError("No se ha encontrado el artista");
                }
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                listener.onLoadError("No se pudo conectar con el servidor");
            }
        });
    }

    @Override
    public void deleteArtist(long id, OnDeleteListener listener) {
        ArtistApiInterface artistApi = CityEventsApi.buildService(ArtistApiInterface.class);
        Call<Void> deleteArtistCall = artistApi.deleteArtist(id);
        deleteArtistCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    listener.onDeleteSuccess();
                } else if (response.code() == 404) {
                    listener.onDeleteError("No se ha encontrado la localización");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteError("No se pudo conectar con el servidor");
            }
        });
    }

}
