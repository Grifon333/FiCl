package com.example.ficl_v4;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;
import com.example.ficl_v4.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private TextView nickname_user, weight_user, height_user, age_user, style_training_user, metabolism_user;
    private Manager dbManager;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nickname_user = root.findViewById(R.id.nickname_user);
        weight_user = root.findViewById(R.id.weight_user);
        height_user = root.findViewById(R.id.height_user);
        age_user = root.findViewById(R.id.age_user);
        style_training_user = root.findViewById(R.id.style_training_user);
        metabolism_user = root.findViewById(R.id.metabolism_user);

        dbManager = new Manager(getContext());

        Cursor cursor = dbManager.getUser();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                nickname_user.setText(cursor.getString(0));
                weight_user.setText("Height: " + cursor.getString(1));
                height_user.setText("Weight: " + cursor.getString(2));
                age_user.setText("Age: " + cursor.getString(3));
                style_training_user.setText("Style training: " + cursor.getString(4));
                metabolism_user.setText("Metabolism: " + cursor.getString(5));
            }
        }
        return root;
    }
}