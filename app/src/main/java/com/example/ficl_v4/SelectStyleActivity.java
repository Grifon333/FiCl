package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectStyleActivity extends AppCompatActivity {

    private ImageButton imageDrying, imageMaintenance, imageMass;
    private TextView habit1, habit2, habit3;
    private final int colorSelect = 0xFFE6382C;
    private final int sizeSelect = 24;
    private String selectHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_style);

        selectHabit = null;

        imageDrying = findViewById(R.id.imageDrying);
        imageMaintenance = findViewById(R.id.imageMaintenance);
        imageMass = findViewById(R.id.imageMass);

        habit1 = findViewById(R.id.habit1);
        habit2 = findViewById(R.id.habit2);
        habit3 = findViewById(R.id.habit3);
    }

    public void searchFirst(View v) {
        selectHabit = "Drying";
        imageDrying.setBackgroundColor(colorSelect);
        habit1.setBackgroundColor(colorSelect);
        habit1.setTextSize(sizeSelect);

        imageMaintenance.setBackgroundColor(0xFF000000);
        imageMass.setBackgroundColor(0xFF000000);
        habit2.setBackgroundColor(0x00000000);
        habit3.setBackgroundColor(0x00000000);
        habit2.setTextSize(20);
        habit3.setTextSize(20);
    }

    public void searchSecond(View v) {
        selectHabit = "Maintenance";
        imageMaintenance.setBackgroundColor(colorSelect);
        habit2.setBackgroundColor(colorSelect);
        habit2.setTextSize(sizeSelect);

        imageDrying.setBackgroundColor(0xFF000000);
        imageMass.setBackgroundColor(0xFF000000);
        habit1.setBackgroundColor(0x00000000);
        habit3.setBackgroundColor(0x00000000);
        habit1.setTextSize(20);
        habit3.setTextSize(20);
    }

    public void searchThird(View v) {
        selectHabit = "Mass";
        imageMass.setBackgroundColor(colorSelect);
        habit3.setBackgroundColor(colorSelect);
        habit3.setTextSize(sizeSelect);

        imageDrying.setBackgroundColor(0xFF000000);
        imageMaintenance.setBackgroundColor(0xFF000000);
        habit1.setBackgroundColor(0x00000000);
        habit2.setBackgroundColor(0x00000000);
        habit1.setTextSize(20);
        habit2.setTextSize(20);
    }

    public void move(View v) {

        if (selectHabit == null) {
            Toast.makeText(this, "Select Habit", Toast.LENGTH_SHORT).show();
        } else {
            String login = getIntent().getStringExtra("login");
            String password = getIntent().getStringExtra("password");
            int weight = getIntent().getIntExtra("weight", 0);
            int height = getIntent().getIntExtra("height", 0);
            int age = getIntent().getIntExtra("age", 0);

            Intent intent = new Intent(this, SelectActiveActivity.class);
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            intent.putExtra("weight", weight);
            intent.putExtra("height", height);
            intent.putExtra("age", age);
            intent.putExtra("select_habit", selectHabit);
            startActivity(intent);
        }
    }
}