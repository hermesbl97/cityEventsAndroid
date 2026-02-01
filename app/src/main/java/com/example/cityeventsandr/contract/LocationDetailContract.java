package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Location;

public interface LocationDetailContract {

    interface Model {
        interface OnLoadListener {
            void onLoadSuccess(Location location);
            void onLoadError(String message);
        }

        void loadLocationById(long id, OnLoadListener listener);
    }

    interface Presenter {
        void loadLocation(long id);
    }

    interface View {
        void showLocation(Location location);
        void showMessage(String message);
        void showError(String message);
    }
}
