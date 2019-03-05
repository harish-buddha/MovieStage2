package com.example.harish.moviestage2.Fav_Movies;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class repository {

    private MyDao movieDao;
    private LiveData<List<Fav_Movies>> favMoviesData;

    public repository(Application application)
    {
        AppDatabase database=AppDatabase.getDatabase(application);
        movieDao=database.myDao();
        favMoviesData=  movieDao.getall();
    }

    public Fav_Movies findMovie(String id)
    {
        Fav_Movies movieData=movieDao.search(Integer.parseInt(id));
        return movieData;
    }
    public LiveData<List<Fav_Movies>> listLiveData()
    {
        return favMoviesData;
    }

    public void insertData(Fav_Movies favMovieData)
    {
        new MyTask(movieDao).execute(favMovieData);
    }

    public void deleteData(Fav_Movies data)
    {
        new MyDeleteTask(movieDao).execute(data);
    }

    public class MyTask extends AsyncTask<Fav_Movies,Void,Void>
    {

        public MyDao mDao;
        public MyTask(MyDao movieDao) {
            mDao=movieDao;
        }

        @Override
        protected Void doInBackground(Fav_Movies... favMovieData)
        {
            mDao.insert(favMovieData[0]);
            return null;
        }
    }
    public class MyDeleteTask extends AsyncTask<Fav_Movies,Void,Void>
    {

        public MyDao dao;
        public MyDeleteTask(MyDao movieDao) {
            dao=movieDao;
        }

        @Override
        protected Void doInBackground(Fav_Movies... favMovieData)
        {
            dao.delete(favMovieData[0]);
            return null;
        }
    }

}
