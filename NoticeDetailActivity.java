package com.unj.collegenoticeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NoticeDetailActivity extends AppCompatActivity {

    private ImageView imageViewNotice;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        imageViewNotice = findViewById(R.id.imageViewNotice);
        textViewTitle = findViewById(R.id.textViewTitle);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String image = intent.getStringExtra("image");

            textViewTitle.setText(title);

            // Load and display the image using Glide
            Glide.with(this)
                    .load(image)
                    .into(imageViewNotice);
        }
    }
}

