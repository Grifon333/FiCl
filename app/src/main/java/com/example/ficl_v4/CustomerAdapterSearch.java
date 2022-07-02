package com.example.ficl_v4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ficl_v4.database.Manager;

import java.util.ArrayList;

public class CustomerAdapterSearch extends RecyclerView.Adapter<CustomerAdapterSearch.MyViewHolder> {
    private final Context context;
    private final ArrayList<String> name, manufacturer;
    private final ArrayList<Integer> protein, fat, carbohydrate;
    private final String period_day;

    CustomerAdapterSearch(Context context, ArrayList<String> names,
                           ArrayList<String> manufacturers, ArrayList<Integer> protein,
                           ArrayList<Integer> fat, ArrayList<Integer> carbohydrate,
                          String period_day) {
        this.context = context;
        this.name = names;
        this.manufacturer = manufacturers;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.period_day = period_day;
    }

    @NonNull
    @Override
    public CustomerAdapterSearch.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_product, parent, false);
        return new CustomerAdapterSearch.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapterSearch.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.manufacturer.setText(String.valueOf(manufacturer.get(position)));
        holder.p_product.setText(String.valueOf(protein.get(position)));
        holder.f_product.setText(String.valueOf(fat.get(position)));
        holder.c_product.setText(String.valueOf(carbohydrate.get(position)));
        holder.mainProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager dbManager = new Manager(context);

                String n = name.get(position);
                String m = manufacturer.get(position);
                int p = protein.get(position);
                int f = fat.get(position);
                int c = carbohydrate.get(position);
                int id_manufacturer = dbManager.getIdManufacturer(m);

                Product product = new Product(n, p, f, c, id_manufacturer);
                int id_product = dbManager.getIdProduct(product);

                showDialog(dbManager, id_product);
            }
        });
    }

    private void showDialog(Manager db, int id_product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter weight in grams");
        final EditText input = new EditText(context);
        input.setInputType(EditorInfo.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(input);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int weight_product = Integer.parseInt(input.getText().toString());
                db.addProductInRation(weight_product, period_day, id_product);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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
        }
    }
}