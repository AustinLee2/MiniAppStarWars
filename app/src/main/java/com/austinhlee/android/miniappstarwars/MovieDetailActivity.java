package com.austinhlee.android.miniappstarwars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView mTextView;
    private TextView mDescripTextView;
    private Button mButton;
    private ImageView mImageView;
    private RadioGroup mRadioGroup;
    private RadioButton mWantToSeeButton;
    private RadioButton mAlreadySeenRadioButton;
    private RadioButton mDontLikeButton;


    private Intent mIntent;
    private int mCheckInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mTextView = findViewById(R.id.detailTitle_text_view);
        mImageView = findViewById(R.id.detail_image_view);
        mDescripTextView = findViewById(R.id.detailDescrip_text_view);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        String imageURL = intent.getStringExtra("posterURL");

        Picasso.with(mContext).load(imageURL).into(mImageView);
        mTextView.setText(title);
        mDescripTextView.setText(intent.getStringExtra("description"));

        mAlreadySeenRadioButton = findViewById(R.id.already_seen_button);
        mWantToSeeButton = findViewById(R.id.want_to_see_button);
        mDontLikeButton = findViewById(R.id.do_not_like_button);

        mRadioGroup = findViewById(R.id.detail_radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.already_seen_button){
                    mCheckInt = 1;
                }
                else if (checkedId == R.id.want_to_see_button){
                    mCheckInt = 2;
                }
                else if (checkedId == R.id.do_not_like_button){
                    mCheckInt = 3;
                }
            }
        });
        mButton = findViewById(R.id.submit_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent=new Intent();
                mIntent.putExtra("seen",mCheckInt);
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });
    }
}
