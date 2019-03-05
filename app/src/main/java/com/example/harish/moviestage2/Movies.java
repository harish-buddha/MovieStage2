package com.example.harish.moviestage2;

public class Movies {
    String title;
    String poster_path;
    String id;
    String vote_avg;
    String overview;
    String release_date;


    public Movies(String title, String poster_path, String id, String vote_avg, String overview, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.id = id;
        this.vote_avg = vote_avg;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_avg() {
        return vote_avg;
    }

    public void setVote_avg(String vote_avg) {
        this.vote_avg = vote_avg;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
