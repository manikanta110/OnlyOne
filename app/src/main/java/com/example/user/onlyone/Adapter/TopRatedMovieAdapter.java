package com.example.user.onlyone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.onlyone.DetailActivity;
import com.example.user.onlyone.Models.Result;
import com.example.user.onlyone.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by user on 17-Oct-17.
 */

public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.MyViewHolder> {
    List<Result> results;
    Context context;


    public TopRatedMovieAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final  MyViewHolder holder, int position) {
        holder.title.setText(results.get(position).getOriginalTitle());
        String vote =Double.toString(results.get(position).getVoteAverage());
        holder.rating.setText("Rating:" +(vote)+"/10");
        Picasso.with(context).load(results.get(position).getPosterPath()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
     public    ImageView image;
       public TextView title,rating;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.thumbnail);
            title =(TextView)itemView.findViewById(R.id.title);
            rating =(TextView)itemView.findViewById(R.id.user_rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Result clickeddata = results.get(pos);
                        Intent intent = new Intent(context,DetailActivity.class);
                        intent.putExtra("title",results.get(pos).getOriginalTitle());
                        intent.putExtra("release_date",results.get(pos).getReleaseDate());
                        intent.putExtra("content",results.get(pos).getOverview());
                        intent.putExtra("poster",results.get(pos).getPosterPath());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);


                    }
                }
            });
        }
    }
}
