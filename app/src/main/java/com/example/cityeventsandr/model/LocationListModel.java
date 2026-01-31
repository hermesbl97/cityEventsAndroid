package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.api.LocationApiInterface;
import com.example.cityeventsandr.contract.LocationListContract;
import com.example.cityeventsandr.domain.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListModel implements LocationListContract.Model {
    @Override
    public void loadLocations(OnLoadListener listener) {
        LocationApiInterface locationApi = CityEventsApi.buildService(LocationApiInterface.class);
        Call<List<Location>> getLocationsCall = locationApi.getLocations();
        getLocationsCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.code() == 200) {
                    List<Location> locations = response.body();
                    listener.onLoadSuccess(locations);
                } else if (response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                android.util.Log.e("API_ERROR", "Causa del fallo: " + t.getMessage());
                t.printStackTrace();

                listener.onLoadError("No se ha podido conectar: " + t.getMessage());
            }
        });
    }
}
