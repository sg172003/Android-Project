package com.unj.collegenoticeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.AbstractList;
import java.util.ArrayList;

public class UserNoticeAdapter extends RecyclerView.Adapter<UserNoticeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NoticeData> noticeList;

    public UserNoticeAdapter(Context context, ArrayList<NoticeData> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticeData noticeData = noticeList.get(position);
        holder.textViewTitle.setText(noticeData.getTitle());

        // Handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity with notice details
                Intent intent = new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("title", noticeData.getTitle());
                intent.putExtra("image", noticeData.getImage());
                // Add other details if needed
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
