package com.example.cityeventsandr.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.contract.RegisterArtistContract;
import com.example.cityeventsandr.presenter.RegisterArtistPresenter;
import com.example.cityeventsandr.util.DateUtil;

import java.time.LocalDate;

public class RegisterArtistView extends AppCompatActivity implements RegisterArtistContract.View {

    private RegisterArtistContract.Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_artist_view);

        presenter = new RegisterArtistPresenter(this);
    }

    public void registerArtist(View view) {
        String name = ((EditText) findViewById(R.id.artist_name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.artist_surname)).getText().toString();
        String type = ((EditText) findViewById(R.id.artist_type)).getText().toString();
        String genre = ((EditText) findViewById(R.id.artist_genre)).getText().toString();
        LocalDate birthDate = DateUtil.parseDate(((EditText) findViewById(R.id.artist_birthDate)).getText().toString());
        int followers = Integer.parseInt(((EditText) findViewById(R.id.artist_followers)).getText().toString());
        float height = Float.parseFloat(((EditText) findViewById(R.id.artist_height)).getText().toString());
        boolean active = ((CheckBox) findViewById(R.id.artist_active)).isChecked();

        presenter.registerArtist(name,surname, genre,type, birthDate, followers, height, active);
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
        Intent intent = new Intent(this, ArtistListView.class);
        startActivity(intent);
        finish();
    }
}