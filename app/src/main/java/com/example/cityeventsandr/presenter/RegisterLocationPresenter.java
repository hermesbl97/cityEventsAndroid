package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.RegisterLocationContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.model.RegisterLocationModel;

import java.time.LocalDate;

public class RegisterLocationPresenter implements RegisterLocationContract.Presenter, RegisterLocationContract.Model.OnRegisterListener {

    private RegisterLocationContract.Model model;
    private RegisterLocationContract.View view;

    public RegisterLocationPresenter(RegisterLocationContract.View view) {
        model = new RegisterLocationModel();
        this.view = view;
    }

    @Override
    public void registerLocation(String name, String description, String category, String streetLocated,
                                 int postalCode, LocalDate registerDate, boolean disabledAccess) {
        Location location = Location.builder()
                .name(name)
                .description(description)
                .category(category)
                .streetLocated(streetLocated)
                .postalCode(postalCode)
                .registerDate(registerDate)
                .disabledAccess(disabledAccess)
                .build();

        model.registerLocation(location, this);
    }

    @Override
    public void onRegisterSuccess(Location location) {
        view.showMessage("Se ha registrado la localización correctamente");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }
}
