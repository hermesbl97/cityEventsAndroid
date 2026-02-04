package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.RegisterLocationContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.model.RegisterLocationModel;

import java.time.LocalDate;

public class RegisterLocationPresenter implements RegisterLocationContract.Presenter,
        RegisterLocationContract.Model.OnRegisterListener, RegisterLocationContract.Model.OnModifyListener {

    private RegisterLocationContract.Model model;
    private RegisterLocationContract.View view;
    private Location currentLocation; // si es null reguistramos, sino editamos

    public RegisterLocationPresenter(RegisterLocationContract.View view, Location location) {
        model = new RegisterLocationModel();
        this.view = view;
        this.currentLocation = location;
    }

    @Override
    public void registerLocation(String name, String description, String category, String streetLocated,
                                 int postalCode, LocalDate registerDate, boolean disabledAccess,
                                 double latitude, double longitude) {
        Location location = Location.builder()
                .name(name)
                .description(description)
                .category(category)
                .streetLocated(streetLocated)
                .postalCode(postalCode)
                .registerDate(registerDate)
                .disabledAccess(disabledAccess)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        if (currentLocation == null) {
            model.registerLocation(location, this);
        } else {
            location.setId(currentLocation.getId());
            model.modifyLocation(location, this);
        }
    }

    @Override
    public void modifyLocation(String name, String description, String category, String streetLocated,
                               int postalCode, LocalDate registerDate, boolean disabledAccess,
                               double latitude, double longitude) {

        registerLocation(name, description, category, streetLocated, postalCode, registerDate,
                disabledAccess, latitude, longitude);
    }

    @Override
    public void onRegisterSuccess(Location location) {
        view.showMessage("Se ha registrado la localización correctamente");
        view.navigateToLocationList();
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }

    @Override
    public void onModifySuccess(Location location) {
        view.showMessage("Localización modificada correctamente");
        view.navigateToLocationList();
    }

    @Override
    public void onModifyError(String message) {
        view.showError(message);
    }
}
