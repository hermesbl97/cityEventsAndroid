package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.RegisterArtistContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.model.RegisterArtistModel;

import java.time.LocalDate;

public class RegisterArtistPresenter implements RegisterArtistContract.Presenter, RegisterArtistContract.Model.OnRegisterListener {

    private RegisterArtistContract.Model model;
    private RegisterArtistContract.View view;

    public RegisterArtistPresenter(RegisterArtistContract.View view) {
        this.view = view;
        model = new RegisterArtistModel();
    }

    @Override
    public void registerArtist(String name, String surname, String genre, String type, LocalDate birthDate,
                               int followers, float height, boolean active) {

        Artist artist = Artist.builder()
                .name(name)
                .surname(surname)
                .genre(genre)
                .type(type)
                .birthDate(birthDate)
                .followers(followers)
                .height(height)
                .active(active)
                .build();

        model.registerArtist(artist,this);
    }

    @Override
    public void onRegisterSuccess(Artist artist) {
        view.showMessage("Se ha registrado correctamente el artista");
        view.navigateToLocationList();
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }
}
