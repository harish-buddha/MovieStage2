package com.example.harish.moviestage2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    Context context;
    List<TrailerLinks> trailerLinksList;

    public TrailerAdapter(Main2Activity main2Activity, List<TrailerLinks> trailerLinksList) {
        this.trailerLinksList = trailerLinksList;
        this.context = main2Activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(trailerLinksList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return trailerLinksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_trailer);
            imageView = itemView.findViewById(R.id.trailer_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                String key = trailerLinksList.get(pos).getKey_trailer();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + key));
                context.startActivity(intent);
            }
        }
    }
}
