package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.ArtistApiInterface;
import com.example.cityeventsandr.api.LocationApiInterface;
import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.api.EventApiInterface;
import com.example.cityeventsandr.contract.RegisterEventContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterEventModel implements RegisterEventContract.Model {

    @Override
    public void registerEvent(Event event, OnRegisterListener listener) {
        EventApiInterface api = CityEventsApi.buildService(EventApiInterface.class);
        Call<Event> postEventCall = api.registerEvent(event);
        postEventCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onRegisterError("Error de validación");
                } else if (response.code() == 404) {
                    listener.onRegisterError("Evento no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void loadLocations(OnLoadLocationsListener listener) {
        LocationApiInterface api = CityEventsApi.buildService(LocationApiInterface.class);
        Call<List<Location>> call = api.getLocations();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    listener.onLoadSuccess(response.body());
                } else {
                    listener.onLoadError("Error al cargar las localizaciones");
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void loadArtists(OnLoadArtistsListener listener) {
        ArtistApiInterface api = CityEventsApi.buildService(ArtistApiInterface.class);
        Call<List<Artist>> call = api.getArtists();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (response.isSuccessful()) {
                    listener.onLoadSuccess(response.body());
                } else {
                    listener.onLoadError("Error al cargar los artistas");
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });
    }
}
