package com.example.cityeventsandr.presenter;

import android.widget.Toast;

import com.example.cityeventsandr.contract.LocationListContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.model.LocationListModel;

import java.util.List;

public class LocationListPresenter implements LocationListContract.Presenter, LocationListContract.Model.OnLoadListener {

    private LocationListContract.Model model;
    private LocationListContract.View view;

    public LocationListPresenter(LocationListContract.View view) {
        this.model = new LocationListModel();
        this.view = view;
    }

    @Override
    public void loadLocations() {
        model.loadLocations(this);
    }

    @Override
    public void onLoadSuccess(List<Location> locations) {
        view.showLocations(locations);
        view.showMessage("Se han cargado las localizaciones con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }
}
