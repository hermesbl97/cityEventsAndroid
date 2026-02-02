package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Artist;

import java.util.List;

public interface ArtistListContract {

    interface Model {

        interface OnLoadListener {
            void onLoadSuccess(List<Artist> artists);
            void onLoadError(String message);
        }

        void loadArtists(OnLoadListener listener);
    }

    interface  Presenter {
        void loadArtists();
    }

    interface View {
        void showArtists(List<Artist> artists);
        void showMessage(String message);
        void showError(String message);
    }
}
