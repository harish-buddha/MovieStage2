package com.example.harish.moviestage2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harish.moviestage2.Fav_Movies.Fav_Movies;
import com.example.harish.moviestage2.Fav_Movies.ViewModel;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    ImageView imageView;
    String poster_path, title, id, vote_avg, overview, release_date;
    RecyclerView recyclerView1, recyclerView2;
    RequestQueue requestQueue1;
    List<Review> reviewList;
    List<TrailerLinks> trailerLinksList;
    LikeButton likeButton;
    ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        tv1 = findViewById(R.id.title_text);
        tv2 = findViewById(R.id.vote_avg);
        tv3 = findViewById(R.id.release_date);
        tv4 = findViewById(R.id.overview_text);
        tv5 = findViewById(R.id.userrev);
        tv6 = findViewById(R.id.trailer_tv);
        imageView = findViewById(R.id.img1);
        recyclerView1 = findViewById(R.id.rec1);
        recyclerView2 = findViewById(R.id.rec2);
        likeButton = findViewById(R.id.star_button);

        requestQueue1 = Volley.newRequestQueue(this);
        reviewList = new ArrayList<>();
        trailerLinksList = new ArrayList<>();
        poster_path = getIntent().getStringExtra("poster_path");
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        vote_avg = getIntent().getStringExtra("vote_avg");
        overview = getIntent().getStringExtra("overview");
        release_date = getIntent().getStringExtra("release_date");
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Fav_Movies fm = new Fav_Movies();
                fm.setMoviename(title);
                fm.setPosterPath(poster_path);

                fm.setRelease_date(release_date);
                fm.setDec(overview);
                fm.setId(Integer.parseInt(id));
                viewModel.insert(fm);

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Fav_Movies fm = new Fav_Movies();
                fm.setMoviename(title);
                fm.setPosterPath(poster_path);

                fm.setRelease_date(release_date);
                fm.setDec(overview);
                fm.setId(Integer.parseInt(id));
                viewModel.deleteData(fm);

            }
        });

        Picasso.get().load(poster_path).into(imageView);
        tv1.setText(title);
        tv2.setText(vote_avg);
        tv3.setText(release_date);
        tv4.setText(overview);
        getreviews();
        getTrailerlinks();
        checkfavourite();

    }

    private void checkfavourite() {


        Fav_Movies fav_movies = viewModel.findMovieData(id);
        if (fav_movies != null) {
            likeButton.setLiked(true);
        } else {
            likeButton.setLiked(false);
        }
    }

    public void getreviews() {
        String id = getIntent().getStringExtra("id");


        String reviewsurl = "https://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=9579b92f0766e5fb7c586e7f7fe3d874";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, reviewsurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {


                    String author = null;
                    String content = null;

                    JSONObject root = new JSONObject(response);

                    JSONArray results = root.getJSONArray("results");


                    if (results.length() == 0) {

                        tv5.setText("Reviews: No reviews!");
                    } else {

                        for (int i = 0; i < results.length(); i++) {

                            JSONObject jsonObject = results.getJSONObject(i);
                            author = jsonObject.getString("author");

                            content = jsonObject.getString("content");


                            Review review = new Review(author, content);
                            reviewList.add(review);

                        }
                    }


                    ReviewAdapter reviewAdapter = new ReviewAdapter(Main2Activity.this, reviewList);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                    recyclerView2.setAdapter(reviewAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue1.add(stringRequest1);


    }

    public void getTrailerlinks() {

        String id = getIntent().getStringExtra("id");

        String Trailerurl = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=9579b92f0766e5fb7c586e7f7fe3d874";
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, Trailerurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {


                    String trailer_id = null;
                    String key_trailer = null;
                    String type = null;
                    String name = null;
                    JSONObject root = new JSONObject(response);


                    JSONArray results = root.getJSONArray("results");

                    if (results.length() == 0) {

                        tv6.setText("TRAILERS :  NO TRAILERS");
                    } else {
                        for (int k = 0; k < results.length(); k++) {
                            JSONObject object = results.getJSONObject(k);
                            trailer_id = object.getString("id");
                            key_trailer = object.getString("key");
                            name = object.getString("name");
                            type = object.getString("type");

                            TrailerLinks trailerLinks = new TrailerLinks(trailer_id, key_trailer, name, type);
                            trailerLinksList.add(trailerLinks);
                        }
                    }

                    TrailerAdapter adapter = new TrailerAdapter(Main2Activity.this, trailerLinksList);
                    recyclerView1.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                    recyclerView1.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue1.add(stringRequest2);

    }


}
