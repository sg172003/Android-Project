package com.unj.collegenoticeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class UserViewNotice extends AppCompatActivity {

    private RecyclerView ViewNoticeRecycler;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private UserNoticeAdapter adapter;

    private DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_notice);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btnLogOut = findViewById(R.id.btnLogOut);

        if (user == null) {
            Intent i = new Intent(UserViewNotice.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(UserViewNotice.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        ViewNoticeRecycler = findViewById(R.id.ViewNoticeRecycler);
        progressBar = findViewById(R.id.progressBar);

        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        ViewNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        ViewNoticeRecycler.setHasFixedSize(true);

        getNotice();

    }

    private void getNotice() {
        reference.orderByChild("time").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    NoticeData data = snapshot1.getValue(NoticeData.class);
                    list.add(data);
                }

                // Sort the list in reverse order based on time
                Collections.sort(list, new Comparator<NoticeData>() {
                    @Override
                    public int compare(NoticeData noticeData1, NoticeData noticeData2) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy hh:mm a", Locale.US);
                        try {
                            Date date1 = format.parse(noticeData1.getDate() + " " + noticeData1.getTime());
                            Date date2 = format.parse(noticeData2.getDate() + " " + noticeData2.getTime());
                            if (date1 != null && date2 != null) {
                                return date2.compareTo(date1);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }

                });

                adapter = new UserNoticeAdapter(UserViewNotice.this, list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                ViewNoticeRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UserViewNotice.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}