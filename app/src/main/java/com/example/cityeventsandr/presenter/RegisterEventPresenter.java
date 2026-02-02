package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.RegisterEventContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.model.RegisterEventModel;

import java.time.LocalDate;
import java.util.List;

public class RegisterEventPresenter implements RegisterEventContract.Presenter,
    RegisterEventContract.Model.OnRegisterListener {

    private RegisterEventContract.Model model;
    private RegisterEventContract.View view;

    public RegisterEventPresenter(RegisterEventContract.View view) {
        model = new RegisterEventModel();
        this.view = view;
    }

    @Override
    public void registerEvent(String name, String description, LocalDate eventDate,
                              int capacity, String category, float price, long locationId,List<Long> artistsId) {

//        if (name.isEmpty()) {
//            view.showValidationError("El campo nombre es obligatorio");
//        }

        Event event = Event.builder()
                .name(name)
                .description(description)
                .eventDate(eventDate)
                .capacity(capacity)
                .category(category)
                .price(price)
                .locationId(locationId)
                .artistsIds(artistsId)
                .build();

        model.registerEvent(event, this);
    }

    @Override
    public void loadLocations() {
        model.loadLocations(new RegisterEventContract.Model.OnLoadLocationsListener() {
            @Override
            public void onLoadSuccess(List<Location> locations) {
                view.showLocations(locations);
            }

            @Override
            public void onLoadError(String message) {
                view.showError(message);
                }
        });
    }

    @Override
    public void loadArtists() {
        model.loadArtists(new RegisterEventContract.Model.OnLoadArtistsListener() {

            @Override
            public void onLoadSuccess(List<Artist> artists) {
                view.showArtists(artists);
            }

            @Override
            public void onLoadError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void onRegisterSuccess(Event event) {
        view.showMessage("Evento registrado correctamente");
        view.navigateToEventList();
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }


}
