package com.example.harish.moviestage2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<Review> reviewList;

    public ReviewAdapter(Main2Activity main2Activity, List<Review> reviewList) {
        this.context = main2Activity;
        this.reviewList = reviewList;

    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item2, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder viewHolder, int i) {


        viewHolder.author_tv.setText(reviewList.get(i).getAuthor());
        viewHolder.content_tv.setText(reviewList.get(i).getContent());


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author_tv, content_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author_tv = itemView.findViewById(R.id.author);
            content_tv = itemView.findViewById(R.id.content);
        }
    }
}
