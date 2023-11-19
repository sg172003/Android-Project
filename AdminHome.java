package com.unj.collegenoticeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminHome extends AppCompatActivity {

    TextView Upload, Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Delete = findViewById(R.id.Delete);
        Upload = findViewById(R.id.Upload);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, DeleteNotice.class);
                startActivity(i);
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, UploadNotice.class);
                startActivity(i);
            }
        });

    }
}