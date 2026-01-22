package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.EventListContract;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.model.EventListModel;

import java.util.List;

public class EventListPresenter implements EventListContract.Presenter, EventListContract.Model.OnLoadListener {

    private EventListContract.Model model;
    private EventListContract.View view;

    public EventListPresenter(EventListContract.View view) {
        this.model = new EventListModel();
        this.view = view;
    }

    @Override
    public void loadEvents() {
        model.loadEvents(this);
    }

    @Override
    public void onLoadSuccess(List<Event> events) {
        view.showEvents(events);
        view.showMessage("Se han cargado los eventos con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }
}
