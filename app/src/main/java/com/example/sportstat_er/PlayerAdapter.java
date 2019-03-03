package com.example.sportstat_er;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private List<Player> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, position, team;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            team = (TextView) view.findViewById(R.id.team);
            position = (TextView) view.findViewById(R.id.position);
        }
    }


    public PlayerAdapter(List<Player> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Player movie = moviesList.get(position);
        holder.name.setText(movie.getName());
        holder.team.setText(movie.getTeam());
        holder.position.setText(movie.getPosition());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
