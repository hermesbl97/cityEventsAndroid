package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.RegisterArtistContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.model.RegisterArtistModel;

import java.time.LocalDate;

public class RegisterArtistPresenter implements RegisterArtistContract.Presenter,
        RegisterArtistContract.Model.OnRegisterListener, RegisterArtistContract.Model.OnModifyListener {

    private RegisterArtistContract.Model model;
    private RegisterArtistContract.View view;
    private Artist currentArtist; // si es null reguistramos, sino editamos

    public RegisterArtistPresenter(RegisterArtistContract.View view, Artist artist) {
        this.view = view;
        model = new RegisterArtistModel();
        this.currentArtist = artist;
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

        if (currentArtist == null) {
            model.registerArtist(artist, this);
        } else {
            artist.setId(currentArtist.getId());
            model.modifyArtist(artist, this);
        }
    }

    @Override
    public void modifyArtist(long id, String name, String surname, String genre, String type,
                             LocalDate birthDate, int followers, float height, boolean active) {
        registerArtist(name, surname, genre, type, birthDate, followers, height, active);
    }

    @Override
    public void onRegisterSuccess(Artist artist) {
        view.showMessage("Se ha registrado correctamente el artista");
        view.navigateToArtistList();
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }

    @Override
    public void onModifySuccess(Artist artist) {
        view.showMessage("Artista modificado correctamente");
        view.navigateToArtistList();
    }

    @Override
    public void onModifyError(String message) {
        view.showError(message);
    }

}
