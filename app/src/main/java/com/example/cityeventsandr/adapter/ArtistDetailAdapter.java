package com.example.cityeventsandr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.domain.Artist;

public class ArtistDetailAdapter extends RecyclerView.Adapter<ArtistDetailAdapter.ViewHolder> {

    public Artist artist;

    public ArtistDetailAdapter(Artist artist) {
        this.artist = artist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artist_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistDetailAdapter.ViewHolder holder, int position) {
        holder.name.setText(artist.getName() + " " + artist.getSurname());
        holder.genre.setText(artist.getGenre());
        holder.type.setText(artist.getType());
        holder.birthDate.setText(String.valueOf(artist.getBirthDate()));
        holder.height.setText(String.valueOf(artist.getHeight()));
        holder.followers.setText(String.valueOf(artist.getFollowers()));
        holder.active.setText(artist.isActive()
                ? "Artista en activo"
                : "Artista retirado"
        );


    }

    @Override
    public int getItemCount() {
        return artist == null ? 0 : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, genre, type, birthDate, followers, height, active;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.artist_detail_name);
            genre = itemView.findViewById(R.id.artist_detail_genre);
            type = itemView.findViewById(R.id.artist_detail_type);
            birthDate = itemView.findViewById(R.id.artist_detail_birth_date);
            followers = itemView.findViewById(R.id.artist_detail_followers);
            height = itemView.findViewById(R.id.artist_detail_height);
            active = itemView.findViewById(R.id.artist_detail_active);
        }
    }
}
