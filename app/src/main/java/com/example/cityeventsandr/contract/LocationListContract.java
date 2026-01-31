package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Location;

import java.util.List;

public interface LocationListContract {

    interface Model {

        interface OnLoadListener {
            void onLoadSuccess(List<Location> locations);
            void onLoadError(String message);
        }

        void loadLocations(OnLoadListener listener);
    }

    interface View {
        void showLocations(List<Location> locations);
        void showMessage(String message);
        void showError(String message);
        //TODO validaciones
    }

    interface Presenter {
        void loadLocations();
    }
}
