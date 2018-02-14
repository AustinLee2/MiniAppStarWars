package com.austinhlee.android.miniappstarwars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    private int mSeen;
    private Movie mMovie;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mSeen = 0;


        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        mMovieAdapter = new MovieAdapter(this, movieList);

        //get data to pass
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(mMovieAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMovie = movieList.get(i);
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("title", mMovie.getTitle());
                intent.putExtra("posterURL", mMovie.getPosterURL());
                intent.putExtra("description", mMovie.getDescription());
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                mSeen = data.getIntExtra("seen", 0);
                mMovie.setSeen(mSeen);
                mMovieAdapter.notifyDataSetChanged();
            }
        }
    }
}
