package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;

import java.util.ArrayList;

public class FillRationActivity extends AppCompatActivity {

    private CustomerAdapterSearch adapter;
    private ArrayList<String> name, manufacturer;
    private ArrayList<Integer> protein, fat, carbohydrate;
    private ArrayList<String> new_name, new_manufacturer;
    private ArrayList<Integer> new_protein, new_fat, new_carbohydrate;
    private Manager dbManager;
    private RecyclerView list_products_for_ration;
    private EditText edNameSelectProduct;
    private String period_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ration);

        list_products_for_ration = findViewById(R.id.list_products_for_ration);
        edNameSelectProduct = findViewById(R.id.edNameSelectProduct);

        name = new ArrayList<>();
        protein = new ArrayList<>();
        fat = new ArrayList<>();
        carbohydrate = new ArrayList<>();
        manufacturer = new ArrayList<>();
        dbManager = new Manager(this);

        new_name = new ArrayList<>();
        new_protein = new ArrayList<>();
        new_fat = new ArrayList<>();
        new_carbohydrate = new ArrayList<>();
        new_manufacturer = new ArrayList<>();

        storeData();
        getPeriodDay();

        adapter = new CustomerAdapterSearch(
                this, name, manufacturer, protein, fat, carbohydrate, period_day
        );
        list_products_for_ration.setAdapter(adapter);
        list_products_for_ration.setLayoutManager(new LinearLayoutManager(this));
    }

    private void storeData() {
        Cursor cursor = dbManager.getAllProducts();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                protein.add(cursor.getInt(1));
                fat.add(cursor.getInt(2));
                carbohydrate.add(cursor.getInt(3));
                manufacturer.add(cursor.getString(4));
            }
        }
    }

    public void selectProducts(View v) {
        new_name.clear();
        new_protein.clear();
        new_fat.clear();
        new_carbohydrate.clear();
        new_manufacturer.clear();

        String nameForSearch = edNameSelectProduct.getText().toString();
        storeNewData(nameForSearch);
        adapter = new CustomerAdapterSearch(
                this, new_name, new_manufacturer, new_protein, new_fat, new_carbohydrate, period_day
        );
        list_products_for_ration.setAdapter(adapter);
        list_products_for_ration.setLayoutManager(new LinearLayoutManager(this));
    }

    private void storeNewData(String search) {
        Cursor cursor = dbManager.searchProducts(search);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                new_name.add(cursor.getString(0));
                new_protein.add(cursor.getInt(1));
                new_fat.add(cursor.getInt(2));
                new_carbohydrate.add(cursor.getInt(3));
                new_manufacturer.add(cursor.getString(4));
            }
        }
    }

    public void getPeriodDay() {
        if (getIntent().hasExtra("period_day"))
            period_day = getIntent().getStringExtra("period_day");
    }

}