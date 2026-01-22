package com.example.cityeventsandr.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.adapter.EventAdapter;
import com.example.cityeventsandr.api.EventApi;
import com.example.cityeventsandr.api.EventApiInterface;
import com.example.cityeventsandr.contract.EventListContract;
import com.example.cityeventsandr.domain.Event;
import com.example.cityeventsandr.presenter.EventListPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListView extends AppCompatActivity implements EventListContract.View {

    private EventAdapter eventAdapter;
    private List<Event> eventList;
    private EventListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new EventListPresenter(this);

        eventList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.event_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadEvents();
    }

    @Override
    public void showEvents(List<Event> events) {
        eventList.clear();
        eventList.addAll(events);
        eventAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(this, RegisterEventActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }
}