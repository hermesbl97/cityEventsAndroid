package com.example.cityeventsandr.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.contract.RegisterArtistContract;
import com.example.cityeventsandr.domain.Artist;
import com.example.cityeventsandr.presenter.RegisterArtistPresenter;
import com.example.cityeventsandr.util.DateUtil;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

public class RegisterArtistView extends AppCompatActivity implements RegisterArtistContract.View {

    private RegisterArtistContract.Presenter presenter;
    private Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_artist_view);

        //Recogemos al artist si editamos y sino será null
        Artist artist = (Artist) getIntent().getSerializableExtra("artist");
        presenter = new RegisterArtistPresenter(this, artist);

        if (artist != null) { //si se modifica rellenamos los campos con sus valores
            ((EditText) findViewById(R.id.artist_name)).setText(artist.getName());
            ((EditText) findViewById(R.id.artist_surname)).setText(artist.getSurname());
            ((EditText) findViewById(R.id.artist_type)).setText(artist.getType());
            ((EditText) findViewById(R.id.artist_genre)).setText(artist.getGenre());
            ((EditText) findViewById(R.id.artist_birthDate)).setText(DateUtil.formatDate(artist.getBirthDate()));
            ((EditText) findViewById(R.id.artist_followers)).setText(String.valueOf(artist.getFollowers()));
            ((EditText) findViewById(R.id.artist_height)).setText(String.valueOf(artist.getHeight()));
            ((CheckBox) findViewById(R.id.artist_active)).setChecked(artist.isActive());
        }
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

        presenter.registerArtist(name,surname, genre,type, birthDate,
                followers, height, active);
    }

    public void selectImage(View view) { //acción para añadir imagen en el registro
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryActivityResultLauncher.launch(galleryIntent);
    }

    ActivityResultLauncher<Intent>  galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) { //si elijo una foto válida, cojo su dirección y la asigno al objeto
                    image_uri = result.getData().getData();
                    ImageView artistImage = findViewById(R.id.artist_image);
                    artistImage.setImageURI(image_uri);
                }
            }
    );


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToArtistList() {
        Intent intent = new Intent(this, ArtistListView.class);
        startActivity(intent);
        finish();
    }
}