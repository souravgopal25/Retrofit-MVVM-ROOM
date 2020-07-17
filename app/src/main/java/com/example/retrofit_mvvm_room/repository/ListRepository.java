package com.example.retrofit_mvvm_room.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_mvvm_room.model.MovieResults;
import com.example.retrofit_mvvm_room.retrofit.ApiInterface;
import com.example.retrofit_mvvm_room.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository {
    private static final String TAG = ListRepository.class.getSimpleName();
    private ApiInterface apiInterface;



    public ListRepository() {
        apiInterface = RetrofitRequest.getRetrofitInstance().create(ApiInterface.class);
    }

    public LiveData<MovieResults> getMovieResults(String category,String apikey,String language,int page){
        final MutableLiveData<MovieResults> data =new MutableLiveData<>();
        apiInterface.getlistofMovies(category,apikey,language,page)
                .enqueue(new Callback<MovieResults>() {
                    @Override
                    public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                            data.setValue(response.body());
                        Log.d(TAG,"TOTAL RESULTS "+response.body().getTotal_results());
                    }

                    @Override
                    public void onFailure(Call<MovieResults> call, Throwable t) {
                        data.setValue(null);
                        Log.e(TAG,"FAILED" +t.getMessage());

                    }
                });





        return data;
    }

}
