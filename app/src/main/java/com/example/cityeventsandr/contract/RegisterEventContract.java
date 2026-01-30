package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;

import java.time.LocalDate;
import java.util.List;

public interface RegisterEventContract {

    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Event event);
            void onRegisterError(String message);
        }
        void registerEvent(Event event, OnRegisterListener listener);

        interface OnLoadLocationsListener {
            void onLoadSuccess(List<Location> locations);
            void onLoadError(String message);
        }

        void loadLocations(OnLoadLocationsListener listener);

        interface OnLoadArtistsListener {
            void onLoadSuccess(List<Artist> artists);
            void onLoadError(String message);
        }
        void loadArtists(OnLoadArtistsListener listener);

    }

    interface  View {
        void showMessage(String message);
        void showError(String message);
        void showLocations(List<Location> locations);
        void showArtists(List<Artist> artists);
    }

    interface Presenter {
        void registerEvent(String name, String description, LocalDate eventDate,
                           int capacity, String category, float price, long locationId, List<Long> artistId);

        void loadLocations();
        void loadArtists();
    }
}
