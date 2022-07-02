package com.example.ficl_v4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ficl_v4.database.Manager;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateInDiaryActivity extends AppCompatActivity {

    private TextView name_product_for_update;
    private TextInputEditText update_weight;
    private String name, period_day;
    private Integer weight, protein, fat, carbohydrate, id_manufacturer;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_in_diary);

        name_product_for_update = findViewById(R.id.name_product_for_update);
        update_weight = findViewById(R.id.update_weight_for_update);
        dbManager = new Manager(this);

        getAndSetIntentData();
    }

    public void updateProductInDiary(View v) {
        int id_product = dbManager.getIdProduct(new Product(name, protein, fat, carbohydrate, id_manufacturer));
        int id = dbManager.getIdProductInDiary(id_product, weight, period_day);
        dbManager.updateProductInDiary(Integer.toString(id), Integer.parseInt(update_weight.getText().toString()));

        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);
    }

    public void deleteProductInDiary(View v) {
        confirmDialog();
    }

    private void getAndSetIntentData() {
        if (getIntent().hasExtra("name") &&
                getIntent().hasExtra("weight") && getIntent().hasExtra("protein") &&
                getIntent().hasExtra("fat") && getIntent().hasExtra("carbohydrate") &&
                getIntent().hasExtra("id_manufacturer") && getIntent().hasExtra("period_day")) {

            // Getting data from Intent
            name = getIntent().getStringExtra("name");
            weight = Integer.parseInt(getIntent().getStringExtra("weight"));
            protein = Integer.parseInt(getIntent().getStringExtra("protein"));
            fat = Integer.parseInt(getIntent().getStringExtra("fat"));
            carbohydrate = Integer.parseInt(getIntent().getStringExtra("carbohydrate"));
            id_manufacturer = Integer.parseInt(getIntent().getStringExtra("id_manufacturer"));
            period_day = getIntent().getStringExtra("period_day");

            // Setting Intent data
            name_product_for_update.setText(name);
            update_weight.setText(Integer.toString(weight));
        }
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Manager dbManager = new Manager(UpdateInDiaryActivity.this);
                int id_product = dbManager.getIdProduct(new Product(name, protein, fat, carbohydrate, id_manufacturer));
                int id = dbManager.getIdProductInDiary(id_product, weight, period_day);
                dbManager.deleteProductInDiary(Integer.toString(id));
                Intent intent = new Intent(UpdateInDiaryActivity.this, DiaryActivity.class);
                startActivity(intent);
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