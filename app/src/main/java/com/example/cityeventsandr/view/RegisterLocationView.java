package com.example.cityeventsandr.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.contract.RegisterLocationContract;
import com.example.cityeventsandr.presenter.RegisterLocationPresenter;
import com.example.cityeventsandr.util.DateUtil;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;

import java.time.LocalDate;

public class RegisterLocationView extends AppCompatActivity implements RegisterLocationContract.View, OnMapClickListener {

    private RegisterLocationContract.Presenter presenter;
    private MapView mapView;
    private GesturesPlugin gesturesPlugin; //plugin para poder interactuar con el mapa
    private Point currentPoint; //punto que selecciona el usuario en el mapa
    private PointAnnotationManager pointAnnotationManager; //clase que se encarga de dibujar el marcador en el mapa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_location_view);

        presenter = new RegisterLocationPresenter(this);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        initializeAnnotationPlugin();
        initializeAnnotationManager();

    }

    public void registerLocation(View view) {

        if (currentPoint == null) {
            Toast.makeText(this, "Selecciona una ubicación en el mapa", Toast.LENGTH_LONG).show();
            return;
        }

        String name = ((EditText) findViewById(R.id.artist_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.artist_surname)).getText().toString();
        String category = ((EditText) findViewById(R.id.artist_type)).getText().toString();
        String streetLocated = ((EditText) findViewById(R.id.artist_genre)).getText().toString();
        int postalCode = Integer.parseInt(((EditText) findViewById(R.id.artist_followers)).getText().toString());
        LocalDate registerDate =  DateUtil.parseDate(((EditText) findViewById(R.id.artist_birthDate)).getText().toString());
        boolean disabledAccess = ((CheckBox) findViewById(R.id.artist_active)).isChecked();

        presenter.registerLocation(name, description, category, streetLocated, postalCode, registerDate, disabledAccess, currentPoint.latitude(), currentPoint.longitude());
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLocationList() {
        Intent intent = new Intent(this, LocationListView.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onMapClick(@NonNull Point point) { //al hacer click se ejecuta el method
        pointAnnotationManager.deleteAll(); //borra todos los puntos que hay y se queda con el ultimo
        currentPoint = point;
        addMarker(point);
        return false;
    }

    private void initializeAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView); //plugin permite hacer anotaciones mapa
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin,annotationConfig);
    }

    private void initializeAnnotationPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_location_foreground))
                .withIconSize(0.4);

        pointAnnotationManager.create(pointAnnotationOptions);
    }
}