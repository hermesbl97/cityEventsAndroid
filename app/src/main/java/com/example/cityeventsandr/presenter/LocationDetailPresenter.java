package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.LocationDetailContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.model.LocationDetailModel;

public class LocationDetailPresenter implements LocationDetailContract.Presenter, LocationDetailContract.Model.OnLoadListener,
    LocationDetailContract.Model.OnDeleteListener{

    private LocationDetailContract.View view;
    private LocationDetailContract.Model model;

    public LocationDetailPresenter(LocationDetailContract.View view) {
        this.model = new LocationDetailModel();
        this.view = view;
    }

    @Override
    public void loadLocation(long id) {
        model.loadLocationById(id, this);
    }

    @Override
    public void deleteLocation(long id) {
        model.deleteLocation(id, this);
    }

    @Override
    public void onLoadSuccess(Location location) {
        view.showLocation(location);
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Se ha eliminado la localización con éxito");
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }
}
