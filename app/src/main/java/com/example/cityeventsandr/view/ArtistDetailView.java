package com.example.cityeventsandr.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.adapter.ArtistDetailAdapter;
import com.example.cityeventsandr.contract.ArtistDetailContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.presenter.ArtistDetailPresenter;

public class ArtistDetailView extends AppCompatActivity implements ArtistDetailContract.View {

   private RecyclerView recyclerView;
   private ArtistDetailAdapter adapter;
   private ArtistDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail_view);

        recyclerView = findViewById(R.id.artist_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ArtistDetailPresenter(this);

        long artistId = getIntent().getLongExtra("artist_id", -1);
        presenter.loadArtist(artistId);
    }

    @Override
    public void showArtist(Artist artist) {
        adapter = new ArtistDetailAdapter(artist);
        recyclerView.setAdapter(adapter);
    }

    public void deleteArtist(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Está seguro de querer eliminar el artista?")
                .setPositiveButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long artistId = getIntent().getLongExtra("artist_id", -1);
                                presenter.deleteArtist(artistId);
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
        alert.create().show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToArtistList() {
        Intent intent = new Intent(this, ArtistListView.class);
        startActivity(intent);
        finish();
    }
}