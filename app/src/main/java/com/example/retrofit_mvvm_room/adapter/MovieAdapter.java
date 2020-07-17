package com.example.retrofit_mvvm_room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_mvvm_room.R;
import com.example.retrofit_mvvm_room.model.MovieResults;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    public static final String TAG=MovieAdapter.class.getSimpleName();
    private Context context;
    private List<MovieResults.ResultsBean> mlist;
    final private ListItemClickListener mOnClickListener;




    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.movielist,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResults.ResultsBean obj=mlist.get(position);
        String url="http://image.tmdb.org/t/p/w500/"+obj.getPoster_path();
        Glide.with(context).load(url).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return  mlist.size();
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedIndex);

    }

    public MovieAdapter(Context context, List<MovieResults.ResultsBean> mlist, ListItemClickListener mOnClickListener) {
        this.context = context;
        this.mlist = mlist;
        this.mOnClickListener = mOnClickListener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView =itemView.findViewById(R.id.postImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition=getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

        }
    }


}
