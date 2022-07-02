package com.example.ficl_v4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterShow extends RecyclerView.Adapter<CustomAdapterShow.MyViewHolder> {
    private final Context context;
    private final ArrayList<String> name;
    private final ArrayList<Integer> protein;
    private final ArrayList<Integer> fat;
    private final ArrayList<Integer> carbohydrate;
    private final ArrayList<Integer> weight;
    private final ArrayList<Integer> id_manufacturer;
    private final String period_day;

    CustomAdapterShow(Context context, ArrayList<String> names, ArrayList<Integer> id_manufacturer, ArrayList<Integer> protein,
                      ArrayList<Integer> fat, ArrayList<Integer> carbohydrate,
                      ArrayList<Integer> weight, String period_day) {
        this.context = context;
        this.name = names;
        this.id_manufacturer = id_manufacturer;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.weight = weight;
        this.period_day = period_day;
    }

    @NonNull
    @Override
    public CustomAdapterShow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_product_in_diary, parent, false);
        return new CustomAdapterShow.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterShow.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.pfc_product.setText(protein.get(position) + " \\ " + fat.get(position) + " \\ " + carbohydrate.get(position));
        holder.weight.setText(String.valueOf(weight.get(position)));
        holder.mainProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateInDiaryActivity.class);
                intent.putExtra("name", name.get(position));
                intent.putExtra("weight", String.valueOf(weight.get(position)));
                intent.putExtra("protein", String.valueOf(protein.get(position)));
                intent.putExtra("fat", String.valueOf(fat.get(position)));
                intent.putExtra("carbohydrate", String.valueOf(carbohydrate.get(position)));
                intent.putExtra("id_manufacturer", String.valueOf(id_manufacturer.get(position)));
                intent.putExtra("period_day", period_day);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, pfc_product, weight;
        CardView card_product_diary;
        LinearLayout mainProductLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name_in_diary);
            pfc_product = itemView.findViewById(R.id.pfc_product_in_diary);
            weight = itemView.findViewById(R.id.weight_product_in_diary);
            mainProductLayout = itemView.findViewById(R.id.product_layout_in_diary);
            card_product_diary = itemView.findViewById(R.id.card_product_diary);
        }
    }
}