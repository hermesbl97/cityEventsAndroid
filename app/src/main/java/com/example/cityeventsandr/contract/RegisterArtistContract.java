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

        interface OnModifyListener {
            void onModifySuccess(Artist artist);
            void onModifyError(String message);
        }

        void modifyArtist(Artist artist, OnModifyListener listener);
    }


    interface View {
        void showMessage(String message);
        void showError(String message);
        void navigateToArtistList();
    }

    interface Presenter {
        void registerArtist(String name, String surname, String genre, String type, LocalDate birthDate,
                            int followers, float height, boolean active);

        void modifyArtist(long id, String name, String surname, String genre, String type, LocalDate birthDate,
                          int followers, float height, boolean active);
    }
}
