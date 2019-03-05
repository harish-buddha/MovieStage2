package com.example.harish.moviestage2.Fav_Movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel{
    repository movieRepository;
    private LiveData<List<Fav_Movies>> listLiveData;
    Fav_Movies favMovieData;
    public ViewModel(Application application)
    {
        super(application);
        movieRepository=new repository(application);
        listLiveData=movieRepository.listLiveData();
    }


    public Fav_Movies findMovieData(String id)
    {
        Fav_Movies favMovieData=movieRepository.findMovie(id);
        return favMovieData;
    }
    public LiveData<List<Fav_Movies>> getListLiveData()
    {
        return listLiveData;
    }


    public  void insert(Fav_Movies favMovieData)
    {
        movieRepository.insertData(favMovieData);
    }
    public void deleteData(Fav_Movies favMovieData)
    {
        movieRepository.deleteData(favMovieData);
    }

}
