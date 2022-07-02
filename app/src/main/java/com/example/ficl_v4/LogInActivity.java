package com.example.ficl_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ficl_v4.database.Manager;

public class LogInActivity extends AppCompatActivity {

    private EditText edLogin_login, edPassword_login;
    private Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        edLogin_login = findViewById(R.id.edLogin_login);
        edPassword_login = findViewById(R.id.edPassword_login);
        dbManager = new Manager(this);
    }

    public void next(View v) {
        String enter_login = edLogin_login.getText().toString().trim();
        String enter_password = edPassword_login.getText().toString().trim();
        String password = "";
        Cursor cursor = dbManager.getUserByLogin(enter_login);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "There is no such user", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                password = cursor.getString(0);

                if (enter_password.equals(password)) {
                    dbManager.ActivatedUser(enter_login);
                    Intent intent = new Intent(this, DiaryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}