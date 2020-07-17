package com.example.retrofit_mvvm_room.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofit_mvvm_room.repository.ListRepository;
import com.example.retrofit_mvvm_room.model.MovieResults;

public class MainActivityViewModel extends AndroidViewModel {

    public static String LANGUAGE="en-us";

    public static String CATEGORY="popular";
    public static int PAGE=1;
    public  static  String apikey="ENTER YOUR API KEY";


    private ListRepository listRepository;
    private LiveData<MovieResults> movieResultsLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        listRepository=new ListRepository();
        this.movieResultsLiveData=listRepository.getMovieResults(CATEGORY,apikey,LANGUAGE,PAGE);
    }

    public LiveData<MovieResults> getMovieResultsLiveData(){
            return movieResultsLiveData;
    }
}
