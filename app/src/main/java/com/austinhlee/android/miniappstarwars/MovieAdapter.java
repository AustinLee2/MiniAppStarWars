package com.austinhlee.android.miniappstarwars;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Austin Lee on 2/8/2018.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){

        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater =  (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_view, viewGroup, false);
            holder = new ViewHolder();
            holder.titleTextView = view.findViewById(R.id.title_text_view);
            holder.descripTextView = view.findViewById(R.id.description_text_view);
            holder.characterTextView = view.findViewById(R.id.characters_text_view);
            holder.hasSeenTextView = view.findViewById(R.id.seen_text_view);
            holder.thumbnailImageView = view.findViewById(R.id.poster_image_view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView descripTextView = holder.descripTextView;
        TextView characterTextView = holder.characterTextView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        Movie movie = (Movie)getItem(i);

        titleTextView.setText(movie.getTitle());
        descripTextView.setText(movie.getDescription());
        String mainCharString = new String();
        ArrayList<String> mainCharList = movie.getMain_characters();
        boolean last = false;
        for (int j = 0; j < 3; j++) {
            if (!last) {
                mainCharString += mainCharList.get(j) + ", ";
                if (j == 1){
                    last = true;
                }
            }
            else
                mainCharString += mainCharList.get(j);
        }
        characterTextView.setText(mainCharString);
        int seenInt = movie.getSeen();
        switch (seenInt){
            case 1:
                hasSeenTextView.setText("Already seen");
                hasSeenTextView.setTextColor(Color.parseColor("#43A047"));
                break;
            case 2:
                hasSeenTextView.setText("Want to see");
                hasSeenTextView.setTextColor(Color.BLUE);
                break;
            case 3:
                hasSeenTextView.setText("Do not like");
                hasSeenTextView.setTextColor(Color.RED);
                break;

            default:
                hasSeenTextView.setText("Has seen?");
                hasSeenTextView.setTextColor(Color.GRAY);
                break;
        }
        Picasso.with(mContext).load(movie.getPosterURL()).into(thumbnailImageView);

        return view;

    }

    public ArrayList<Movie> getMovieList(){
        return mMovieList;
    }

    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descripTextView;
        public TextView characterTextView;
        public TextView hasSeenTextView;
        public ImageView thumbnailImageView;
    }

}
