package com.example.cityeventsandr.view;//package com.example.cityeventsandr.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.adapter.LocationDetailAdapter;
import com.example.cityeventsandr.contract.LocationDetailContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.presenter.LocationDetailPresenter;

public class LocationDetailView extends AppCompatActivity implements LocationDetailContract.View {

    private RecyclerView recyclerView;
    private LocationDetailAdapter adapter;
    private LocationDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        recyclerView = findViewById(R.id.location_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new LocationDetailPresenter(this);

        long locationId = getIntent().getLongExtra("location_id", -1);
        presenter.loadLocation(locationId);
    }

    @Override
    public void showLocation(Location location) {
        adapter = new LocationDetailAdapter(location);
        recyclerView.setAdapter(adapter);
    }

    public void deleteLocation(View view) {
        long locationId = getIntent().getLongExtra("location_id", -1);
        if (locationId != -1) {
            presenter.deleteLocation(locationId);
        } else {
            Toast.makeText(this,"No se pudo obtener el ID de la localización" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
