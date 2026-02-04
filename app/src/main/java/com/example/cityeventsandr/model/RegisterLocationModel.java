package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.api.LocationApiInterface;
import com.example.cityeventsandr.contract.RegisterLocationContract;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterLocationModel implements RegisterLocationContract.Model {
    @Override
    public void registerLocation(Location location, OnRegisterListener listener) {
        LocationApiInterface api = CityEventsApi.buildService(LocationApiInterface.class);
        Call<Location> registerLocationCall = api.registerLocation(location);
        registerLocationCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                }  else if (response.code() == 400) {
                    listener.onRegisterError("Error de validación");
                } else if (response.code() == 404) {
                    listener.onRegisterError("Localización no encontrada");
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void modifyLocation(Location location, OnModifyListener listener) {
        LocationApiInterface locationApi = CityEventsApi.buildService(LocationApiInterface.class);
        Call<Location> modifyLocationCall = locationApi.modifyLocation(location.getId(), location);
        modifyLocationCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.code() == 200) {
                    listener.onModifySuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onModifyError("Bad Request");
                } else if (response.code() == 404) {
                    listener.onModifyError("No se ha encontrado la localización");
                } else if (response.code() == 500) {
                    listener.onModifyError("Internal Server error");
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                listener.onModifyError("No se ha podido conectar con el servidor");
            }
        });
    }
}
