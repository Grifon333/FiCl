package com.example.ficl_v4;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;
import com.example.ficl_v4.databinding.FragmentProductsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private RecyclerView list_products;
    private FloatingActionButton add_button, delete_button, reload_button;
    private ArrayList<String> name, manufacturer;
    private ArrayList<Integer> protein, fat, carbohydrate;
    private Manager dbManager;
    private CustomerAdepterProduct adapter;
    private ImageView clear_image;
    private TextView text_clear;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list_products = root.findViewById(R.id.list_products);
        add_button = root.findViewById(R.id.add_button);
        delete_button = root.findViewById(R.id.delete_button);
        reload_button = root.findViewById(R.id.reload_button);
        clear_image = root.findViewById(R.id.clear_image);
        text_clear = root.findViewById(R.id.text_clear);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
        reload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace();
            }
        });


        name = new ArrayList<>();
        protein = new ArrayList<>();
        fat = new ArrayList<>();
        carbohydrate = new ArrayList<>();
        manufacturer = new ArrayList<>();
        dbManager = new Manager(root.getContext());

        storeData();

        adapter = new CustomerAdepterProduct(
                getContext(), name, manufacturer, protein, fat, carbohydrate
        );
        list_products.setAdapter(adapter);
        list_products.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return root;
    }

    private void storeData() {
        Cursor cursor = dbManager.getAllProducts();
        if (cursor.getCount() == 0) {
//            Toast.makeText(binding.getRoot().getContext(), "No data", Toast.LENGTH_SHORT).show();
            clear_image.setVisibility(View.VISIBLE);
            text_clear.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                protein.add(cursor.getInt(1));
                fat.add(cursor.getInt(2));
                carbohydrate.add(cursor.getInt(3));
                manufacturer.add(cursor.getString(4));
            }
            clear_image.setVisibility(View.GONE);
            text_clear.setVisibility(View.GONE);
        }
    }

    public void replace() {
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.detach(ProductsFragment.this).commitNow();
        fTrans.attach(ProductsFragment.this).commitNow();
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete all products");
        builder.setMessage("Are you sure you want to delete all products ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbManager.deleteAllProduct();
                replace();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}