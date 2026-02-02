package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Artist;

import java.time.LocalDate;

public interface RegisterArtistContract {

    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Artist artist);
            void onRegisterError(String message);
        }

        void registerArtist(Artist artist, OnRegisterListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void navigateToLocationList();
    }

    interface Presenter {
        void registerArtist(String name, String surname, String genre, String type,
                            LocalDate birthDate, int followers, float height, boolean active);

    }
}
