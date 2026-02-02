package com.example.cityeventsandr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityeventsandr.R;
import com.example.cityeventsandr.domain.Artist;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {

    private Context context;
    private List<Artist> artistList;

    public ArtistAdapter(Context context, List<Artist> artistList) {
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);
        return new ArtistHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder, int position) {
        Artist artist = artistList.get(position);

        holder.itemArtistName.setText(artist.getName() + " " + artist.getSurname() + " ⭐");
        holder.itemArtistType.setText(artist.getType());
        holder.itemArtistFollowers.setText(artist.getFollowers() + " followers \uD83D\uDC64");
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class ArtistHolder extends RecyclerView.ViewHolder {
        private TextView itemArtistName;
        private TextView itemArtistType;
        private TextView itemArtistFollowers;

        public ArtistHolder(@NonNull View itemView) {
            super(itemView);

            itemArtistName = itemView.findViewById(R.id.item_artist_name);
            itemArtistType = itemView.findViewById(R.id.item_artist_type);
            itemArtistFollowers = itemView.findViewById(R.id.item_artist_followers);
        }


    }
}
