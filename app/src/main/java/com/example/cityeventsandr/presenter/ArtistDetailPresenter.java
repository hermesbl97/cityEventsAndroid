package com.example.cityeventsandr.presenter;

import com.example.cityeventsandr.contract.ArtistDetailContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.model.ArtistDetailModel;

public class ArtistDetailPresenter implements ArtistDetailContract.Presenter, ArtistDetailContract.Model.OnLoadListener,
        ArtistDetailContract.Model.OnDeleteListener{

    private ArtistDetailContract.Model model;
    private ArtistDetailContract.View view;

    public ArtistDetailPresenter(ArtistDetailContract.View view) {
        this.view = view;
        model = new ArtistDetailModel();
    }

    @Override
    public void loadArtist(long id) {
        model.loadArtistById(id, this);
    }

    @Override
    public void deleteArtist(long id) {
        model.deleteArtist(id, this);
    }

    @Override
    public void onLoadSuccess(Artist artist) {
        view.showArtist(artist);
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Se ha eliminado el artista con éxito");
        view.navigateToArtistList();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }
}
