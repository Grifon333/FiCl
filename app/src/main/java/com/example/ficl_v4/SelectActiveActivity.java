package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;

import java.util.ArrayList;

public class SelectActiveActivity extends AppCompatActivity {

    private RecyclerView list;
    private CustomAdapterActive adapter;
    private ArrayList icons;
    private ArrayList titles;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_active);

        list = findViewById(R.id.list);
        dbManager = new Manager(this);

        icons = new ArrayList();
        icons.add(R.drawable.one);
        icons.add(R.drawable.two);
        icons.add(R.drawable.three);
        icons.add(R.drawable.four);
        icons.add(R.drawable.five);
        icons.add(R.drawable.six);
        icons.add(R.drawable.seven);
        titles = new ArrayList();
        titles.add("No or minimal physical activity");
        titles.add("Moderate weight training 3 times a week");
        titles.add("Moderate weight training 5 times a week");
        titles.add("Intensive training 5 times a week");
        titles.add("Training every day");
        titles.add("Intensive training every day or 2 times a day");
        titles.add("Daily physical activity + physical work");

        adapter = new CustomAdapterActive(this, icons, titles);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    public void next(View v) {
        int selectActive = adapter.getCurrentActive();
        if (selectActive == -1) {
            Toast.makeText(this, "Select Active", Toast.LENGTH_SHORT).show();
        } else {
            String login = getIntent().getStringExtra("login");
            String password = getIntent().getStringExtra("password");
            int weight = getIntent().getIntExtra("weight", 0);
            int height = getIntent().getIntExtra("height", 0);
            int age = getIntent().getIntExtra("age", 0);
            String select_habit = getIntent().getStringExtra("select_habit");

            dbManager.addUser(login, password, weight, height, age, selectActive, select_habit);

            Intent intent = new Intent(this, DiaryActivity.class);
            startActivity(intent);
        }
    }
}