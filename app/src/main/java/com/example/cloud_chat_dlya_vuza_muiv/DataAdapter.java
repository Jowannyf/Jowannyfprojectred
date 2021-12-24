package com.example.cloud_chat_dlya_vuza_muiv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

  ArrayList<String> messages;
  LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.message, parent, false);
    return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        String msg = messages.get(position);
        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
