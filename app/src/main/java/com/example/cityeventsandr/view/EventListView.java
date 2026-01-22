package com.example.cityeventsandr.view;

import android.os.Bundle;
import android.widget.Toast;

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
}