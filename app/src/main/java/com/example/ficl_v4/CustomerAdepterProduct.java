package com.example.ficl_v4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdepterProduct extends RecyclerView.Adapter<CustomerAdepterProduct.MyViewHolder> {
    private final Context context;
    private final ArrayList<String> name, manufacturer;
    private final ArrayList<Integer> protein, fat, carbohydrate;
    private Animation translate_anim;

    CustomerAdepterProduct(Context context, ArrayList<String> names,
                           ArrayList<String> manufacturers, ArrayList<Integer> protein,
                           ArrayList<Integer> fat, ArrayList<Integer> carbohydrate) {
        this.context = context;
        this.name = names;
        this.manufacturer = manufacturers;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdepterProduct.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.manufacturer.setText(String.valueOf(manufacturer.get(position)));
        holder.p_product.setText(String.valueOf(protein.get(position)));
        holder.f_product.setText(String.valueOf(fat.get(position)));
        holder.c_product.setText(String.valueOf(carbohydrate.get(position)));
        holder.mainProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("manufacturer", String.valueOf(manufacturer.get(position)));
                intent.putExtra("protein", String.valueOf(protein.get(position)));
                intent.putExtra("fat", String.valueOf(fat.get(position)));
                intent.putExtra("carbohydrate", String.valueOf(carbohydrate.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, manufacturer, p_product, f_product, c_product;
        CardView card_product;
        LinearLayout mainProductLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            manufacturer = itemView.findViewById(R.id.product_manufacturer);
            card_product = itemView.findViewById(R.id.card_product);
            p_product = itemView.findViewById(R.id.p_product);
            f_product = itemView.findViewById(R.id.f_product);
            c_product = itemView.findViewById(R.id.c_product);
            mainProductLayout = itemView.findViewById(R.id.mainProductLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainProductLayout.setAnimation(translate_anim);
        }
    }
}