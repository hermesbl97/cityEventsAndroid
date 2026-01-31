package com.example.cityeventsandr.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.contract.RegisterEventContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.presenter.RegisterEventPresenter;
import com.example.cityeventsandr.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterEventView extends AppCompatActivity implements RegisterEventContract.View {

    private RegisterEventContract.Presenter presenter;
    private Spinner spinnerLocation;
    private Spinner spinnerArtist;
    private List<Location> locationList;
    private List<Artist> artistList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);

        spinnerLocation = findViewById(R.id.spinnerLocation);
        spinnerArtist = findViewById(R.id.spinnerArtist);

        presenter = new RegisterEventPresenter(this);
        presenter.loadLocations();  //cargamos las localizaciones
        presenter.loadArtists();
    }

    public void registerEvent(View view) {
        String name = ((EditText) findViewById(R.id.event_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.event_description)).getText().toString();
        LocalDate eventDate = DateUtil.parseDate(((EditText) findViewById(R.id.event_date)).getText().toString());
        int capacity =Integer.parseInt(((EditText) findViewById(R.id.event_capacity)).getText().toString());
        String category = ((EditText) findViewById(R.id.event_category)).getText().toString();
        float price = Float.parseFloat(((EditText) findViewById(R.id.event_price)).getText().toString());

        long locationId = getSelectedLocationId();
        long artistId = getSelectedArtistId();

        List<Long> artistsIds = new ArrayList<>();
        artistsIds.add(artistId); //añadimos el artista seleccionado a la lista.

        presenter.registerEvent(name,description,eventDate,capacity,category,price, locationId, artistsIds);
    }
    public void showLocations(List<Location> locations) {
        locationList = locations;

        List<String> names = new ArrayList<>();
        for (Location loc : locations) {
            names.add(loc.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapter);
    }

    @Override
    public void showArtists(List<Artist> artists) {
         artistList = artists;

        List<String> names = new ArrayList<>();
        for (Artist art : artists) {
            names.add(art.getName() + " " + art.getSurname());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtist.setAdapter(adapter);
    }

    // Method para obtener el Id de la localización seleccionada
    private long getSelectedLocationId() {
        int positionLocationSelected = spinnerLocation.getSelectedItemPosition();
        return locationList.get(positionLocationSelected).getId();
    }

    // Method para obtener el Id de la localización seleccionada
    private long getSelectedArtistId() {
        int positionArtistSelected = spinnerArtist.getSelectedItemPosition();
        return artistList.get(positionArtistSelected).getId();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        Snackbar.make(this.getCurrentFocus(), message,Snackbar.LENGTH_LONG).show();
    }
}