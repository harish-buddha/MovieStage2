package com.example.harish.moviestage2.Fav_Movies;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface MyDao {

    @Query("select * from  FavMovies")
    LiveData<List<Fav_Movies>> getall();

    @Insert
    void insert (Fav_Movies movies);

    @Delete
    void delete(Fav_Movies movies);

    @Query("select * from FavMovies where id== :id")
    Fav_Movies search(int id);

}
