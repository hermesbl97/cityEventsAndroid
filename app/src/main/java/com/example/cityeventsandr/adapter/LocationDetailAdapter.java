package com.example.cityeventsandr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.domain.Location;

public class LocationDetailAdapter extends RecyclerView.Adapter<LocationDetailAdapter.ViewHolder> {

    private Location location;

    public LocationDetailAdapter(Location location) {
        this.location = location;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(location.getName());
        holder.category.setText(location.getCategory());
        holder.description.setText(location.getDescription());
        holder.street.setText(location.getStreetLocated());
        holder.postalCode.setText(String.valueOf(location.getPostalCode()));
        holder.date.setText(String.valueOf(location.getRegisterDate()));
        holder.disabled.setText(location.isDisabledAccess()
                        ? "Acceso adaptado a personas con discapacidad"
                        : "Sin acceso adaptado a personas con discapacidad"
        );
    }

    @Override
    public int getItemCount() {
        return location == null ? 0 : 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, description, street, postalCode, date, disabled;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.location_detail_name);
            category = itemView.findViewById(R.id.location_detail_category);
            description = itemView.findViewById(R.id.location_detail_description);
            street = itemView.findViewById(R.id.location_detail_street);
            postalCode = itemView.findViewById(R.id.location_detail_postal_code);
            date = itemView.findViewById(R.id.location_detail_register_date);
            disabled = itemView.findViewById(R.id.location_detail_disabled_access);
        }
    }
}
