package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;

import java.time.LocalDate;
import java.util.List;

public interface RegisterLocationContract {

    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Location location);
            void onRegisterError(String message);
        }

        void registerLocation(Location location, OnRegisterListener listener);
    }

    interface Presenter {
        void registerLocation(String name, String description, String category, String streetLocated,
                              int postalCode, LocalDate registerDate, boolean disabledAccess,
                              double latitude, double longitude);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void navigateToLocationList();
    }
}
