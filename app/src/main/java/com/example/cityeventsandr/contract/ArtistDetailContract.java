package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Artist;

public interface ArtistDetailContract {

    interface Model {
        interface OnLoadListener {
            void onLoadSuccess(Artist artist);
            void onLoadError(String message);
        }

        void loadArtistById(long id, OnLoadListener listener);

        interface OnDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void deleteArtist(long id, OnDeleteListener listener);
    }

    interface Presenter {
        void loadArtist(long id);
        void deleteArtist(long id);
    }

    interface View {
        void showArtist(Artist artist);
        void showMessage(String message);
        void showError(String message);
        void navigateToArtistList();
    }
}
