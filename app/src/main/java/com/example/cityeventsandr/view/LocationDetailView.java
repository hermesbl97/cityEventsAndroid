package com.example.cityeventsandr.view;//package com.example.cityeventsandr.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.adapter.LocationDetailAdapter;
import com.example.cityeventsandr.contract.LocationDetailContract;
import com.example.cityeventsandr.domain.Location;
import com.example.cityeventsandr.presenter.LocationDetailPresenter;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

public class LocationDetailView extends AppCompatActivity implements LocationDetailContract.View, Style.OnStyleLoaded {

    private RecyclerView recyclerView;
    private LocationDetailAdapter adapter;
    private LocationDetailContract.Presenter presenter;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        recyclerView = findViewById(R.id.location_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mapView = findViewById(R.id.mainMap);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        presenter = new LocationDetailPresenter(this);

        long locationId = getIntent().getLongExtra("location_id", -1);
        presenter.loadLocation(locationId);
    }

    @Override
    public void showLocation(Location location) {
        this.location = location;

        adapter = new LocationDetailAdapter(location);
        recyclerView.setAdapter(adapter);
    }

    public void deleteLocation(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Está seguro de querer eliminar la localización?")
                .setPositiveButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long locationId = getIntent().getLongExtra("location_id", -1);
                                    presenter.deleteLocation(locationId);
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

    public void editLocation(View view) {
        long locationId = getIntent().getLongExtra("location_id", -1);
        if (locationId == -1) return;

        Intent intent = new Intent(this, RegisterLocationView.class);

        // Pasamos el objeto Location actual.
        intent.putExtra("location", adapter.location); //pasamos los datos de la activiy vista a la de registrar para editar
        startActivity(intent);
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
    public void navigateToLocationList() {
        Intent intent = new Intent(this, LocationListView.class);
        startActivity(intent);
        finish();
    }

    private void initializeAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView); //plugin permite hacer anotaciones mapa
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin,annotationConfig);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) { //cuando cargues el mapa ejecuta el method
        initializeAnnotationManager();

        if (location != null) {
            Point point = Point.fromLngLat(location.getLongitude(), location.getLatitude());
            addMarker(point);
        }
    }

    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_location_foreground))
                .withIconSize(0.4);

        pointAnnotationManager.create(pointAnnotationOptions);
    }
}
