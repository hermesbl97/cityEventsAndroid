package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.ArtistListContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.model.ArtistListModel;

import java.util.List;

public class ArtistListPresenter implements ArtistListContract.Presenter, ArtistListContract.Model.OnLoadListener {

    private ArtistListContract.Model model;
    private ArtistListContract.View view;

    public ArtistListPresenter(ArtistListContract.View view) {
        this.view = view;
        model = new ArtistListModel();
    }

    @Override
    public void loadArtists() {
        model.loadArtists(this);
    }

    @Override
    public void onLoadSuccess(List<Artist> artists) {
        view.showArtists(artists);
        view.showMessage("Se han cargado los artistas con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }
}
