package com.example.harish.moviestage2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.harish.moviestage2.Fav_Movies.Fav_Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.ViewHolder> {
    Context context;
    List<Fav_Movies> fav_moviesList;

    public Fav_Adapter(MainActivity mainActivity, List<Fav_Movies> fav_moviesList) {

        this.context = mainActivity;
        this.fav_moviesList = fav_moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Picasso.get().load(fav_moviesList.get(i).getPosterPath()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return fav_moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            int pos = getAdapterPosition();

            Intent i = new Intent(context, Main2Activity.class);
            i.putExtra("poster_path", fav_moviesList.get(pos).getPosterPath());
            i.putExtra("id", fav_moviesList.get(pos).getId());
            i.putExtra("title", fav_moviesList.get(pos).getMoviename());
            i.putExtra("overview", fav_moviesList.get(pos).getDec());
            i.putExtra("release_date", fav_moviesList.get(pos).getRelease_date());
            i.putExtra("vote_avg", fav_moviesList.get(pos).getVote_avg());
            context.startActivity(i);

        }
    }
}
