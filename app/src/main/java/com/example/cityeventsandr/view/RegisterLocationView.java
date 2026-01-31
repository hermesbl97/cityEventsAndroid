package com.example.cityeventsandr.view;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.contract.RegisterLocationContract;
import com.example.cityeventsandr.presenter.RegisterLocationPresenter;
import com.example.cityeventsandr.util.DateUtil;

import java.time.LocalDate;

public class RegisterLocationView extends AppCompatActivity implements RegisterLocationContract.View {

    private RegisterLocationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_location_view);

        presenter = new RegisterLocationPresenter(this);

    }

    public void registerLocation(View view) {

        String name = ((EditText) findViewById(R.id.location_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.location_description)).getText().toString();
        String category = ((EditText) findViewById(R.id.location_category)).getText().toString();
        String streetLocated = ((EditText) findViewById(R.id.location_streetLocated)).getText().toString();
        int postalCode = Integer.parseInt(((EditText) findViewById(R.id.location_postalCode)).getText().toString());
        LocalDate registerDate =  DateUtil.parseDate(((EditText) findViewById(R.id.location_registerDate)).getText().toString());
        boolean disabledAccess = ((CheckBox) findViewById(R.id.location_disabledAccess)).isChecked();

        presenter.registerLocation(name, description, category, streetLocated, postalCode, registerDate, disabledAccess);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}