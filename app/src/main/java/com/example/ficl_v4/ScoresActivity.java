package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ScoresActivity extends AppCompatActivity {

    private TextInputLayout weightInputLayout, heightInputLayout, ageInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        weightInputLayout = findViewById(R.id.weightInputLayout);
        heightInputLayout = findViewById(R.id.heightInputLayout);
        ageInputLayout = findViewById(R.id.ageInputLayout);
    }

    public void next(View v) {
        String login = getIntent().getStringExtra("login");
        String password = getIntent().getStringExtra("password");
        String weightStr = weightInputLayout.getEditText().getText().toString();
        String heightStr = heightInputLayout.getEditText().getText().toString();
        String ageStr = ageInputLayout.getEditText().getText().toString();

        if (weightStr.equals("") || heightStr.equals("") || ageStr.equals("")) {
            Toast.makeText(this, "You need to fill in all the fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SelectStyleActivity.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("weight", Integer.parseInt(weightStr));
            intent.putExtra("height", Integer.parseInt(heightStr));
            intent.putExtra("age", Integer.parseInt(ageStr));
            startActivity(intent);
        }
    }
}