package com.example.cityeventsandr.model;

import com.example.cityeventsandr.api.CityEventsApi;
import com.example.cityeventsandr.api.EventApiInterface;
import com.example.cityeventsandr.contract.EventListContract;
import com.example.cityeventsandr.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListModel implements EventListContract.Model {
    @Override
    public void loadEvents(OnLoadListener listener) {
        EventApiInterface eventAPi = CityEventsApi.buildService(EventApiInterface.class);
        Call<List<Event>> getEventsCall = eventAPi.getEvents(); //recogemos el resultado de la llamda
        getEventsCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.code() == 200) {
                    List<Event> events = response.body();
                    listener.onLoadSuccess(events); //el listener es el presenter porque se ha pasado así mismo en la llamda. Y el model le facilita los eventos
                } else if (response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                android.util.Log.e("API_ERROR", "Causa del fallo: " + t.getMessage());
                t.printStackTrace();

                listener.onLoadError("No se ha podido conectar: " + t.getMessage());
            }
        });
    }
}
