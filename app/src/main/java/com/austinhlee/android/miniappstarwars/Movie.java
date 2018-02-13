package com.austinhlee.android.miniappstarwars;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Austin Lee on 2/8/2018.
 */

public class Movie {

    private String title;
    private int episode_number;
    private ArrayList<String> main_characters;
    private String description;
    private String posterURL;
    private String url;
    private int seen;

    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        //try to read from JSON file
        //get information by using the tags
        //construct a Recipe Object for each recipe in JSON
        //add object to ArrayList
        //return ArrayList

        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);

            JSONArray movies = json.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.setTitle(movies.getJSONObject(i).getString("title"));
                movie.setEpisode_number(movies.getJSONObject(i).getInt("episode_number"));
                JSONArray arrJson = movies.getJSONObject(i).getJSONArray("main_characters");
                ArrayList<String> arr = new ArrayList<>();
                for(int j = 0; j < arrJson.length(); j++)
                    arr.add(arrJson.getString(j));
                movie.setMain_characters(arr);
                movie.setDescription(movies.getJSONObject(i).getString("description"));
                movie.setPosterURL(movies.getJSONObject(i).getString("poster"));
                movie.setUrl(movies.getJSONObject(i).getString("url"));

                //add to arrayList
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public ArrayList<String> getMain_characters() {
        return main_characters;
    }

    public void setMain_characters(ArrayList<String> main_characters) {
        this.main_characters = main_characters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }
}

