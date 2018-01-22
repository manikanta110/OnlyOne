package com.example.user.onlyone;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.onlyone.Adapter.TopRatedMovieAdapter;
import com.example.user.onlyone.Models.Genre;
import com.example.user.onlyone.Models.GenreClass;
import com.example.user.onlyone.Models.Result;
import com.example.user.onlyone.Models.TopMovie;
import com.example.user.onlyone.api.Client;
import com.example.user.onlyone.api.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFilter extends AppCompatActivity {
    Spinner spinner;
    Button filter;
    private static final String TAG = "ActivityFilter";
    RecyclerView recyclerView;
    LinearLayout layout;
    List<Result> movies=new ArrayList<>();
    ArrayList<Genre> genreList = new ArrayList<>();

  static final   int Action = 28;
  static final   int Adventure = 12;
  static final   int Animation = 16;
   static final int Comedy = 35;
   static final int Crime = 80;
   static final int Documentary = 99;
  static final   int Drama = 18;
  static final   int Family=10751;
 static final    int Fantasy = 14;
  static final   int History = 36;
  static final   int Horror = 27;
  static final   int Music =10402;
 static final    int Mystery = 9648;
   static final int Romance = 10749;
  static  final   int Scifi = 878;
 static final    int TvMovie = 10770;
 static final    int Thriller = 53;
  static  final   int War = 10752;
  static final   int Western = 37;
  CheckBox action,adventure,comedy,crime,thriller,drama,fantasy,history,horror;
  Button filter_btn,filter_cancel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initrecycler();
        initviews();



    }

    private void initrecycler() {

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        filter = (Button)findViewById(R.id.filter_genre);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAndOpenGenre();
            }
        });
        recyclerView.setHasFixedSize(true);
        layout =(LinearLayout)findViewById(R.id.linear_network);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       recyclerView.setAdapter(new TopRatedMovieAdapter(this,movies));

    }

    private void goAndOpenGenre() {
        recyclerView.setVisibility(View.INVISIBLE);
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_genre);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.row_filter,null);
        linearLayout.addView(view);
        linearLayout.setVisibility(View.VISIBLE);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genreList.add(new Genre(Action));

            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {genreList.add(new Genre(Adventure));
            }
        });
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genreList.add(new Genre(Comedy));
            }
        });
        crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genreList.add(new Genre(Crime));
            }
        });
        thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {genreList.add(new Genre(Thriller));
            }
        });
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genreList.add(new Genre(Drama));
            }
        });
        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {genreList.add(new Genre(Fantasy));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {genreList.add(new Genre(History));
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genreList.add(new Genre(Horror));
            }
        });
        filter_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(action.isChecked()||adventure.isChecked()||comedy.isChecked()||crime.isChecked()||thriller.isChecked()||drama.isChecked()||
                        fantasy.isChecked()||history.isChecked()||horror.isChecked()){
                    linearLayout.setVisibility(View.INVISIBLE);
                    goAndOpenFilter(genreList);
                }
                else{
                    Toast.makeText(ActivityFilter.this, "Atleast One Filter Must be Checked", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void goAndOpenFilter(final List<Genre> genreList) {
        Common common= new Common();
        boolean isconnected =common.CheckNetworkConnextion(this);
        if(isconnected){

            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Loading....");
            pd.setCancelable(false);
            pd.show();
            Service service = Client.getRetrofit().create(Service.class);
            Call<GenreClass> call = service.getfiltermovies((ArrayList<Genre>) genreList,"81c4047a8486904dd6cf0787b4b47dc9");
            call.enqueue(new Callback<GenreClass>() {
                @Override
                public void onResponse(Call<GenreClass> call, Response<GenreClass> response) {
                    pd.dismiss();


               // List<Result>   movies = response.body().getResults();
                    Log.d(TAG, "onResponse: "+response.code());

                 /*   if(movies!=null){
                        TopRatedMovieAdapter adapter =  new TopRatedMovieAdapter(getApplicationContext(),movies);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.smoothScrollToPosition(0);

                    }
                    Log.d(TAG, "onResponse: "+response.errorBody());*/

                }

                @Override
                public void onFailure(Call<GenreClass> call, Throwable t) {
                    Toast.makeText(ActivityFilter.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{
            Toast.makeText(this, "Connection UnAvailable", Toast.LENGTH_SHORT).show();

            layout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.network,null);
            layout.addView(view);
            ImageView imageView = (ImageView)view.findViewById(R.id.text_refresh);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goAndOpenFilter(genreList);
                }
            });

        }

    }

    private List<Result> fetchfilterresults(Response<GenreClass> response) {
        return response.body().getResults();
    }


    private void initviews() {
        String[] names = getResources().getStringArray(R.array.Entries);
        spinner =(Spinner)findViewById(R.id.selector);
        action = (CheckBox)findViewById(R.id.action);
        adventure = (CheckBox)findViewById(R.id.adventure);
        comedy =(CheckBox)findViewById(R.id.comedy);
        crime=(CheckBox)findViewById(R.id.crime);
        thriller=(CheckBox)findViewById(R.id.thriller);
        drama = (CheckBox)findViewById(R.id.drama);
        fantasy=(CheckBox)findViewById(R.id.fantasy);
        history = (CheckBox)findViewById(R.id.history);
        horror=(CheckBox)findViewById(R.id.horror);
        filter_btn = (Button)findViewById(R.id.filter);
        filter_cancel = (Button)findViewById(R.id.filter_cancel);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,names);
        spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String selected = adapterView.getItemAtPosition(i).toString();
               if(selected.equals("Top Rated")){
                   gotoTopMovies();
               }
               else if(selected.equals("UpComing")){
                   gotoUpcomingMovies();
               }
               else{
                   gotoPopularMovies();

               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {


           }
       });
    }

    private void gotoPopularMovies() {
        Common common= new Common();
        boolean isconnected =common.CheckNetworkConnextion(this);
        if(isconnected){
            layout.setVisibility(View.INVISIBLE);
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Loading....");
            pd.setCancelable(false);
            pd.show();
            Service service = Client.getRetrofit().create(Service.class);
            Call<TopMovie> call = service.getpopularmovies("81c4047a8486904dd6cf0787b4b47dc9");
            call.enqueue(new Callback<TopMovie>() {
                @Override
                public void onResponse(Call<TopMovie> call, Response<TopMovie> response) {
                    pd.dismiss();
                    Log.i(TAG, "onResponse: "+fetchresults(response));
                     movies =response.body().getResults();
                    recyclerView.setAdapter(new TopRatedMovieAdapter(getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);

                }

                @Override
                public void onFailure(Call<TopMovie> call, Throwable t) {
                    Toast.makeText(ActivityFilter.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{
            Toast.makeText(this, "Connection UnAvailable", Toast.LENGTH_SHORT).show();

            layout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.network,null);
            layout.addView(view);
            ImageView imageView = (ImageView)view.findViewById(R.id.text_refresh);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoPopularMovies();
                }
            });

        }
    }

    private List<Result> fetchresults(Response<TopMovie> response) {
        return response.body().getResults();
    }

    private void gotoUpcomingMovies() {

        Common common= new Common();
        boolean isconnected =common.CheckNetworkConnextion(this);
        if(isconnected){
            layout.setVisibility(View.INVISIBLE);
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Loading....");
            pd.setCancelable(false);
            pd.show();
            Service service = Client.getRetrofit().create(Service.class);
            Call<TopMovie> call = service.getupcomingmovies("81c4047a8486904dd6cf0787b4b47dc9");
            call.enqueue(new Callback<TopMovie>() {
                @Override
                public void onResponse(Call<TopMovie> call, Response<TopMovie> response) {
                    pd.dismiss();
                    Log.i(TAG, "onResponse: "+fetchresults(response));
                     movies =response.body().getResults();
                    recyclerView.setAdapter(new TopRatedMovieAdapter(getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);

                }

                @Override
                public void onFailure(Call<TopMovie> call, Throwable t) {
                    Toast.makeText(ActivityFilter.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{

            Toast.makeText(this, "Connection UnAvailable", Toast.LENGTH_SHORT).show();

            layout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.network,null);
            layout.addView(view);
            ImageView imageView = (ImageView)view.findViewById(R.id.text_refresh);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUpcomingMovies();
                }
            });

        }
    }


    private void gotoTopMovies() {

        Common common= new Common();
        boolean isconnected =common.CheckNetworkConnextion(this);
        if(isconnected){
            layout.setVisibility(View.INVISIBLE);
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Loading....");
            pd.setCancelable(false);
            pd.show();
            Service service = Client.getRetrofit().create(Service.class);
            Call<TopMovie> call = service.getmovies("81c4047a8486904dd6cf0787b4b47dc9");
            call.enqueue(new Callback<TopMovie>() {
                @Override
                public void onResponse(Call<TopMovie> call, Response<TopMovie> response) {
                    pd.dismiss();
                    Log.i(TAG, "onResponse: "+fetchresults(response));
                     movies =response.body().getResults();
                    recyclerView.setAdapter(new TopRatedMovieAdapter(getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);

                }

                @Override
                public void onFailure(Call<TopMovie> call, Throwable t) {
                    Toast.makeText(ActivityFilter.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{

            Toast.makeText(this, "Connection UnAvailable", Toast.LENGTH_SHORT).show();

            layout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.network,null);
            layout.addView(view);
            ImageView imageView = (ImageView)view.findViewById(R.id.text_refresh);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   gotoTopMovies();
                }
            });


        }

    }

}
