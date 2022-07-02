package com.example.ficl_v4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ficl_v4.database.Manager;
import com.example.ficl_v4.databinding.FragmentRationBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RationFragment extends Fragment {

    private FragmentRationBinding binding;
    private ProgressBar progressBar;
    private LinearLayout layoutBreakfast;
    private LinearLayout layoutLunch;
    private LinearLayout layoutDinner;
    private LinearLayout layoutOther;
    private TextView protein;
    private TextView fat;
    private TextView carbohydrate;
    private RecyclerView list_products_for_breakfast,
            list_products_for_lunch, list_products_for_dinner, list_products_for_other;
    private CustomAdapterShow adapter;
    private ArrayList<String> name_b;
    private ArrayList<Integer> protein_b, fat_b, carbohydrate_b, weight_b, id_manufacturer_b;
    private ArrayList<String> name_l;
    private ArrayList<Integer> protein_l, fat_l, carbohydrate_l, weight_l, id_manufacturer_l;
    private ArrayList<String> name_d;
    private ArrayList<Integer> protein_d, fat_d, carbohydrate_d, weight_d, id_manufacturer_d;
    private ArrayList<String> name_o;
    private ArrayList<Integer> protein_o, fat_o, carbohydrate_o, weight_o, id_manufacturer_o;
    private Manager dbManager;
    private TextView scoresBreakfast, scoresLunch, scoresDinner, scoresOther;
    private FloatingActionButton reload_button_diary;

    public RationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView calories = root.findViewById(R.id.calories);
        protein = root.findViewById(R.id.protein);
        fat = root.findViewById(R.id.fat);
        carbohydrate = root.findViewById(R.id.carbohydrate);
        progressBar = root.findViewById(R.id.progressBar);
        reload_button_diary = root.findViewById(R.id.reload_button_diary);

        layoutBreakfast = root.findViewById(R.id.layoutBreakfast);
        layoutLunch = root.findViewById(R.id.layoutLunch);
        layoutDinner = root.findViewById(R.id.layoutDinner);
        layoutOther = root.findViewById(R.id.layoutOther);

        list_products_for_breakfast = root.findViewById(R.id.list_products_for_breakfast);
        list_products_for_lunch = root.findViewById(R.id.list_products_for_lunch);
        list_products_for_dinner = root.findViewById(R.id.list_products_for_dinner);
        list_products_for_other = root.findViewById(R.id.list_products_for_other);

        scoresBreakfast = root.findViewById(R.id.scoresBreakfast);
        scoresLunch = root.findViewById(R.id.scoresLunch);
        scoresDinner = root.findViewById(R.id.scoresDinner);
        scoresOther = root.findViewById(R.id.scoresOther);

        dbManager = new Manager(getContext());

        createArrays();
        storeAllDataForDiary();
        showListsProducts();

        int p_b = calculate(protein_b, weight_b);
        int f_b = calculate(fat_b, weight_b);
        int c_b = calculate(carbohydrate_b, weight_b);
        scoresBreakfast.setText(p_b + " / " + f_b + " / " + c_b);

        int p_l = calculate(protein_l, weight_l);
        int f_l = calculate(fat_l, weight_l);
        int c_l = calculate(carbohydrate_l, weight_l);
        scoresLunch.setText(p_l + " / " + f_l + " / " + c_l);

        int p_d = calculate(protein_d, weight_d);
        int f_d = calculate(fat_d, weight_d);
        int c_d = calculate(carbohydrate_d, weight_d);
        scoresDinner.setText(p_d + " / " + f_d + " / " + c_d);

        int p_o = calculate(protein_o, weight_o);
        int f_o = calculate(fat_o, weight_o);
        int c_o = calculate(carbohydrate_o, weight_o);
        scoresOther.setText(p_o + " / " + f_o + " / " + c_o);

        int p_all = p_b + p_l + p_d + p_o;
        int f_all = f_b + f_l + f_d + f_o;
        int c_all = c_b + c_l + c_d + c_o;

        int cal = calculateCalories(p_all, f_all, c_all);
        calories.setText("Calories: " + cal);
        protein.setText("Protein: " + p_all);
        fat.setText("Fat: " + f_all);
        carbohydrate.setText("Carbohydrate: " + c_all);

        int metabolism = dbManager.getMetabolism();
        progressBar.setProgress((int) (((double) cal / metabolism) * 100));

        layoutBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FillRationActivity.class);
                intent.putExtra("period_day", "Breakfast");
                startActivity(intent);
            }
        });
        layoutLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FillRationActivity.class);
                intent.putExtra("period_day", "Lunch");
                startActivity(intent);
            }
        });
        layoutDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FillRationActivity.class);
                intent.putExtra("period_day", "Dinner");
                startActivity(intent);
            }
        });
        layoutOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FillRationActivity.class);
                intent.putExtra("period_day", "Other");
                startActivity(intent);
            }
        });

        reload_button_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace();
            }
        });

        return root;
    }

    private void showListsProducts() {
        adapter = new CustomAdapterShow(getContext(), name_b, id_manufacturer_b, protein_b, fat_b, carbohydrate_b, weight_b, "Breakfast");
        list_products_for_breakfast.setAdapter(adapter);
        list_products_for_breakfast.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CustomAdapterShow(getContext(), name_l, id_manufacturer_l, protein_l, fat_l, carbohydrate_l, weight_l, "Lunch");
        list_products_for_lunch.setAdapter(adapter);
        list_products_for_lunch.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CustomAdapterShow(getContext(), name_d, id_manufacturer_d, protein_d, fat_d, carbohydrate_d, weight_d, "Dinner");
        list_products_for_dinner.setAdapter(adapter);
        list_products_for_dinner.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CustomAdapterShow(getContext(), name_o, id_manufacturer_o, protein_o, fat_o, carbohydrate_o, weight_o, "Other");
        list_products_for_other.setAdapter(adapter);
        list_products_for_other.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void createArrays() {
        name_b = new ArrayList<>();
        protein_b = new ArrayList<>();
        fat_b = new ArrayList<>();
        carbohydrate_b = new ArrayList<>();
        weight_b = new ArrayList<>();
        id_manufacturer_b = new ArrayList<>();

        name_l = new ArrayList<>();
        protein_l = new ArrayList<>();
        fat_l = new ArrayList<>();
        carbohydrate_l = new ArrayList<>();
        weight_l = new ArrayList<>();
        id_manufacturer_l = new ArrayList<>();

        name_d = new ArrayList<>();
        protein_d = new ArrayList<>();
        fat_d = new ArrayList<>();
        carbohydrate_d = new ArrayList<>();
        weight_d = new ArrayList<>();
        id_manufacturer_d = new ArrayList<>();

        name_o = new ArrayList<>();
        protein_o = new ArrayList<>();
        fat_o = new ArrayList<>();
        carbohydrate_o = new ArrayList<>();
        weight_o = new ArrayList<>();
        id_manufacturer_o = new ArrayList<>();
    }

    private void storeAllDataForDiary() {

        Cursor cursor;

        cursor = dbManager.getProductsInRation("Breakfast");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                name_b.add(cursor.getString(0));
                protein_b.add(cursor.getInt(1));
                fat_b.add(cursor.getInt(2));
                carbohydrate_b.add(cursor.getInt(3));
                id_manufacturer_b.add(cursor.getInt(4));
                weight_b.add(cursor.getInt(5));
            }
        }

        cursor = dbManager.getProductsInRation("Lunch");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                name_l.add(cursor.getString(0));
                protein_l.add(cursor.getInt(1));
                fat_l.add(cursor.getInt(2));
                carbohydrate_l.add(cursor.getInt(3));
                id_manufacturer_l.add(cursor.getInt(4));
                weight_l.add(cursor.getInt(5));
            }
        }

        cursor = dbManager.getProductsInRation("Dinner");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                name_d.add(cursor.getString(0));
                protein_d.add(cursor.getInt(1));
                fat_d.add(cursor.getInt(2));
                carbohydrate_d.add(cursor.getInt(3));
                id_manufacturer_d.add(cursor.getInt(4));
                weight_d.add(cursor.getInt(5));
            }
        }

        cursor = dbManager.getProductsInRation("Other");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                name_o.add(cursor.getString(0));
                protein_o.add(cursor.getInt(1));
                fat_o.add(cursor.getInt(2));
                carbohydrate_o.add(cursor.getInt(3));
                id_manufacturer_o.add(cursor.getInt(4));
                weight_o.add(cursor.getInt(5));
            }
        }
    }

    public int calculateCalories(int p, int f, int c) {
        int result = p * 4 + f * 9 + c * 4;
        return result;
    }

    public int calculate(ArrayList<Integer> arr_p, ArrayList<Integer> arr_w) {
        double result = 0;
        for (int i = 0; i < arr_p.size(); i++) {
            result += (arr_p.get(i) * arr_w.get(i) / 100.0);
        }
        return (int) result;
    }

    public void replace() {
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.detach(RationFragment.this).commitNow();
        fTrans.attach(RationFragment.this).commitNow();
    }
}