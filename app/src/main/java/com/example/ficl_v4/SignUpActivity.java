package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText edLogin, edPassword, edConfirmPassword;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edLogin = findViewById(R.id.edLoginSignUp);
        edPassword = findViewById(R.id.edPasswordSignUp);
        edConfirmPassword = findViewById(R.id.edConfirmPasswordSignUp);
        dbManager = new Manager(this);
    }

    public void register(View v) {
        String login = edLogin.getText().toString();
        String password = edPassword.getText().toString();
        String confirmPassword = edConfirmPassword.getText().toString();

        Cursor cursor = dbManager.getUserByLogin(login);
        if (cursor.getCount() == 0) {
            if (password.equals(confirmPassword)) {
                Intent intent = new Intent(this, ScoresActivity.class);
                intent.putExtra("login", login);
                intent.putExtra("password", password);
                startActivity(intent);
            } else if (login.equals("") || password.equals("")) {
                Toast.makeText(this, "You need to fill in all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error, passwords don`t equals", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Username is already taken", Toast.LENGTH_SHORT).show();
        }
    }
}