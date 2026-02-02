package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.api.LocationApiInterface;
import com.example.cityeventsandr.contract.LocationDetailContract;
import com.example.cityeventsandr.domain.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailModel implements LocationDetailContract.Model {
    @Override
    public void loadLocationById(long id, OnLoadListener listener) {
        LocationApiInterface locationApi = CityEventsApi.buildService(LocationApiInterface.class);
        Call<Location> getLocationByIdCall = locationApi.getLocationById(id);
        getLocationByIdCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.code() == 200) {
                    listener.onLoadSuccess(response.body());
                } else if (response.code() == 404) {
                    listener.onLoadError("No se ha encontrado la localización");
                } else {
                    listener.onLoadError("Error al cargar los datos");
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                listener.onLoadError("No se pudo conectar con el servidor");
            }
        });
    }

    @Override
    public void deleteLocation(long id, OnDeleteListener listener) {
        LocationApiInterface locationApi = CityEventsApi.buildService(LocationApiInterface.class);
        Call<Void> deleteLocationCall = locationApi.deleteLocation(id);
        deleteLocationCall.enqueue(new Callback<Void>() {
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
