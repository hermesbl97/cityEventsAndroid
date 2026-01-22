package com.example.cityeventsandr.contract;

import com.example.cityeventsandr.domain.Event;

import java.util.List;

public interface EventListContract {

    interface Model {
        interface OnLoadListener {
            void onLoadSuccess(List<Event> events);
            void onLoadError(String message);
        }
        void loadEvents(OnLoadListener listener);
    }

    interface  View {
        void showEvents(List<Event> events);
        void showMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void loadEvents();
    }
}
