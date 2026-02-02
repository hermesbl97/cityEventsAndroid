package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.ArtistApiInterface;
import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.contract.ArtistListContract;
import com.example.cityeventsandr.domain.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistListModel implements ArtistListContract.Model {
    @Override
    public void loadArtists(OnLoadListener listener) {
        ArtistApiInterface artistApi = CityEventsApi.buildService(ArtistApiInterface.class);
        Call<List<Artist>> getArtistsCall = artistApi.getArtists();
        getArtistsCall.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (response.code() == 200) {
                    List<Artist> artists = response.body();
                    listener.onLoadSuccess(artists);
                } else if (response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                listener.onLoadError("No se pudo conectar con el servidor");
            }
        });
    }
}
