package com.example.user.onlyone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title,r_date,Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Detail Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView =(ImageView)findViewById(R.id.de_poster);
        title =(TextView)findViewById(R.id.de_title);
        r_date = (TextView)findViewById(R.id.de_release);
        Content =(TextView)findViewById(R.id.de_content);

        String name = getIntent().getExtras().getString("title");
        String poster = getIntent().getExtras().getString("poster");
        String date = getIntent().getExtras().getString("release_date");
        String content = getIntent().getExtras().getString("content");

        title.setText(name);
        r_date.setText(date);
        Content.setText(content);
        Picasso.with(this).load(poster).into(imageView);


    }
}
