package com.example.harish.moviestage2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<Movies> list;

    public MyAdapter(MainActivity mainActivity, List<Movies> list) {
        this.context = mainActivity;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(list.get(i).getPoster_path()).into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
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
            i.putExtra("image", list.get(pos).getPoster_path());
            i.putExtra("poster_path", list.get(pos).getPoster_path());
            i.putExtra("id", list.get(pos).getId());
            i.putExtra("title", list.get(pos).getTitle());
            i.putExtra("overview", list.get(pos).getOverview());
            i.putExtra("release_date", list.get(pos).getRelease_date());
            i.putExtra("vote_avg", list.get(pos).getVote_avg());
            context.startActivity(i);
        }
    }
}
