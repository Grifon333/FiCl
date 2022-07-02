package com.example.ficl_v4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ficl_v4.database.Manager;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    private TextInputEditText edNameProduct_update, edManufacturerProduct_update, edProteinProduct_update,
            edFatProduct_update, edCarbohydrateProduct_update;
    private String name, manufacturer, protein, fat, carbohydrate;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edNameProduct_update = findViewById(R.id.edNameProduct_update);
        edManufacturerProduct_update = findViewById(R.id.edManufacturerProduct_update);
        edProteinProduct_update = findViewById(R.id.edProteinProduct_update);
        edFatProduct_update = findViewById(R.id.edFatProduct_update);
        edCarbohydrateProduct_update = findViewById(R.id.edCarbohydrateProduct_update);
        dbManager = new Manager(this);

        getAndSetIntentData();
    }

    public void updateProduct(View v) {
        int id_manufacturer = dbManager.getIdManufacturer(manufacturer);
        Product oldProduct = new Product(name, Integer.parseInt(protein), Integer.parseInt(fat), Integer.parseInt(carbohydrate), id_manufacturer);
        String id = Integer.toString(dbManager.getIdProduct(oldProduct));

        dbManager.addManufacturer(edManufacturerProduct_update.getText().toString());
        int id_manufacturer_new = dbManager.getIdManufacturer(edManufacturerProduct_update.getText().toString());
        Product product = new Product(edNameProduct_update.getText().toString(),
                Integer.parseInt(edProteinProduct_update.getText().toString()),
                Integer.parseInt(edFatProduct_update.getText().toString()),
                Integer.parseInt(edCarbohydrateProduct_update.getText().toString()),
                id_manufacturer_new);
        dbManager.updateProduct(id, product);

        this.finish();
    }

    public void deleteProduct(View v) {
        confirmDialog();
    }

    private void getAndSetIntentData() {
        if (getIntent().hasExtra("name") &&
                getIntent().hasExtra("manufacturer") &&
                getIntent().hasExtra("manufacturer") &&
                getIntent().hasExtra("manufacturer") &&
                getIntent().hasExtra("manufacturer")) {

            // Getting data from Intent
            name = getIntent().getStringExtra("name");
            manufacturer = getIntent().getStringExtra("manufacturer");
            protein = getIntent().getStringExtra("protein");
            fat = getIntent().getStringExtra("fat");
            carbohydrate = getIntent().getStringExtra("carbohydrate");

            // Setting Intent data
            edNameProduct_update.setText(name);
            edManufacturerProduct_update.setText(manufacturer);
            edProteinProduct_update.setText(protein);
            edFatProduct_update.setText(fat);
            edCarbohydrateProduct_update.setText(carbohydrate);
        }
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id_manufacturer = dbManager.getIdManufacturer(manufacturer);
                Product oldProduct = new Product(name, Integer.parseInt(protein), Integer.parseInt(fat), Integer.parseInt(carbohydrate), id_manufacturer);
                String id = Integer.toString(dbManager.getIdProduct(oldProduct));
                dbManager.deleteProduct(id);

                Intent intent = new Intent(UpdateActivity.this, DiaryActivity.class);
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