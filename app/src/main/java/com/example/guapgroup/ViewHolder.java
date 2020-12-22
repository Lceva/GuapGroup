package com.example.guapgroup;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView messadge;
    public ViewHolder(View itemView) {
        super(itemView);
        messadge = itemView.findViewById(R.id.item_mes);
    }
}