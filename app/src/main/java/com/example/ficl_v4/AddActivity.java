package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;
import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {

    private TextInputEditText edNameProduct, edManufacturerProduct, edProteinProduct, edFatProduct, edCarbohydrateProduct;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edNameProduct = findViewById(R.id.edNameProduct);
        edManufacturerProduct = findViewById(R.id.edManufacturerProduct);
        edProteinProduct = findViewById(R.id.edProteinProduct);
        edFatProduct = findViewById(R.id.edFatProduct);
        edCarbohydrateProduct = findViewById(R.id.edCarbohydrateProduct);

        dbManager = new Manager(this);
    }

    public void addProduct(View v) {
        if (edNameProduct.getText().toString().equals("") ||
                edManufacturerProduct.getText().toString().equals("") ||
                edProteinProduct.getText().toString().equals("") ||
                edFatProduct.getText().toString().equals("") ||
                edCarbohydrateProduct.getText().toString().equals("")) {
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            String name = edNameProduct.getText().toString().trim().toLowerCase();
            int protein = Integer.parseInt(edProteinProduct.getText().toString().trim());
            int fat = Integer.parseInt(edFatProduct.getText().toString().trim());
            int carbohydrate = Integer.parseInt(edCarbohydrateProduct.getText().toString().trim());

            String manufacturer = edManufacturerProduct.getText().toString().trim();
            dbManager.addManufacturer(manufacturer);

            int id_manufacturer = dbManager.getIdManufacturer(manufacturer);

            Product product = new Product(name, protein, fat, carbohydrate, id_manufacturer);
            dbManager.addProduct(product);

            edNameProduct.setText("");
            edManufacturerProduct.setText("");
            edProteinProduct.setText("");
            edFatProduct.setText("");
            edCarbohydrateProduct.setText("");
        }
    }
}