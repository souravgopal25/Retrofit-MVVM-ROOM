package com.example.retrofit_mvvm_room.view;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.retrofit_mvvm_room.R;
import com.example.retrofit_mvvm_room.adapter.MovieAdapter;
import com.example.retrofit_mvvm_room.model.MovieResults;
import com.example.retrofit_mvvm_room.view_model.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{


    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.progress_circular_movie_article)
    ProgressBar progress_circular_movie_article;
    @BindView(R.id.my_recycler_view)
    RecyclerView my_recycler_view;



    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private MovieAdapter adapter;
    private ArrayList<MovieResults.ResultsBean> listofmovies1 = new ArrayList<>();
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialization();
        getMovie();


    }

    private void getMovie() {
        mainActivityViewModel.getMovieResultsLiveData().observe(this, new Observer<MovieResults>() {
            @Override
            public void onChanged(MovieResults movieResponse) {
                if (movieResponse != null) {

                    progress_circular_movie_article.setVisibility(View.GONE);
                    List<MovieResults.ResultsBean> listofMovies = movieResponse.getResults();
                    listofmovies1.addAll(listofMovies);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    public void initialization() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(calculateNoOfColumns(this), LinearLayoutManager.VERTICAL);
        my_recycler_view.setLayoutManager(staggeredGridLayoutManager);

        my_recycler_view.setHasFixedSize(true);

        //adapter
        //TODO
        adapter=new MovieAdapter(MainActivity.this,listofmovies1,this);
        my_recycler_view.setAdapter(adapter);


        //viewmodel
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);








    }
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }

    @Override
    public void onListItemClick(int clickedIndex) {
        Toast.makeText(this, "Clicked On :"+listofmovies1.get(clickedIndex).getTitle(), Toast.LENGTH_SHORT).show();

    }
}