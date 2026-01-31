package com.example.cityeventsandr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.domain.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationHolder> {

    private Context context;
    private List<Location> locationList;


    public LocationAdapter(Context context ,List<Location> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false);
        return new LocationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationHolder holder, int position) {
        Location location = locationList.get(position);

        holder.itemLocationName.setText("Nombre: "+ location.getName());
        holder.itemLocationCategory.setText("Categoría:" +  location.getCategory());

        if (location.isDisabledAccess()) {
            holder.itemLocationDisabledAccess.setVisibility(View.VISIBLE);
        } else {
            holder.itemLocationDisabledAccess.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        private TextView itemLocationName;
        private TextView itemLocationCategory;
        private TextView itemLocationDisabledAccess;

        public LocationHolder(@NonNull View itemView) {
            super(itemView);

            itemLocationName = itemView.findViewById(R.id.item_location_name);
            itemLocationCategory = itemView.findViewById(R.id.item_location_category);
            itemLocationDisabledAccess = itemView.findViewById(R.id.item_location_disabledAccess);
        }
    }
}
