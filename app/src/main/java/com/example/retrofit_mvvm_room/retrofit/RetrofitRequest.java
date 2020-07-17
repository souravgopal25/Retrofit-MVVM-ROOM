package com.example.retrofit_mvvm_room.retrofit;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    /* private static  final String=getRes*/
    public static String LANGUAGE="en-us";
    public static final String BASE_URL="https://api.themoviedb.org";
    public static String CATEGORY="popular";
    public static int PAGE=1;


    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit= new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            
        }
        return retrofit;
    }
}
