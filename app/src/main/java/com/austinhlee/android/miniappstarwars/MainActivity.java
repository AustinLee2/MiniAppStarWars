package com.austinhlee.android.miniappstarwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        MovieAdapter adapter = new MovieAdapter(this, movieList);

        //get data to pass
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

    }
}
