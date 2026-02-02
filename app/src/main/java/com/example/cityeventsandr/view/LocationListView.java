package com.example.cityeventsandr.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.adapter.LocationAdapter;
import com.example.cityeventsandr.contract.LocationListContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.presenter.LocationListPresenter;

import java.util.ArrayList;
import java.util.List;

public class LocationListView extends AppCompatActivity implements LocationListContract.View {

    private LocationAdapter locationAdapter;
    private List<Location> locationList;
    private LocationListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list_view);

        presenter = new LocationListPresenter(this);
        locationList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.location_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        locationAdapter = new LocationAdapter(this, locationList);
        recyclerView.setAdapter(locationAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadLocations();
    }

    @Override
    public void showLocations(List<Location> locations) {
        locationList.clear();
        locationList.addAll(locations);
        locationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_event) { //si se selecciona la opción de registrar evento
            Intent intent = new Intent(this, RegisterEventView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_getall_locations) {
            Intent intent = new Intent(this, LocationListView.class); // si seleccionan el boton de buscar todas las localizaciones, nos mostrará todas las activities.
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_getall_artists) {
            Intent intent = new Intent(this, ArtistListView.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

}