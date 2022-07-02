package com.example.ficl_v4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterActive extends RecyclerView.Adapter<CustomAdapterActive.MyViewHolder> {

    private Context context;
    private ArrayList icon, title;
    private int currentActive;

    CustomAdapterActive(Context context, ArrayList icon, ArrayList title) {
        this.context = context;
        this.icon = icon;
        this.title = title;
        currentActive = -1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    private CardView oldElement;
    private CardView selectElement;

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.icon.setImageResource((Integer) icon.get(position));
        holder.title.setText(String.valueOf(title.get(position)));
        holder.cardElement.setBackgroundColor(0xffb8e0ff);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectElement = holder.cardElement;
                selectElement.setBackgroundColor(Color.YELLOW);
                currentActive = position + 1;

                if (oldElement != null) {
                    oldElement.setBackgroundColor(0xffb8e0ff);
                    if (selectElement == oldElement) {
                        currentActive = -1;
                        oldElement = null;
                        return;
                    }
                }
                oldElement = selectElement;
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public int getCurrentActive() {
        return currentActive;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        LinearLayout mainLayout;
        CardView cardElement;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.iconActivity);
            title = itemView.findViewById(R.id.titleActivity);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            cardElement = itemView.findViewById(R.id.cardElement);
        }
    }
}