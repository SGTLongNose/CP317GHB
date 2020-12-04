package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminUserLogin extends AppCompatActivity {

    private Button btn_back, btn_login;
    private EditText email;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_login);
        dataBaseHelper = new DataBaseHelper(AdminUserLogin.this);

        email = (EditText) findViewById(R.id.et_userEmail);

        btn_back = (Button) findViewById(R.id.btn_backToLogin);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        btn_login = (Button) findViewById(R.id.btn_adminLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(email.getText().toString());
                try {
                    boolean x = dataBaseHelper.isValidEmail(email.getText().toString());
                    System.out.println(x);
                    if (!x) {
                        Toast.makeText(AdminUserLogin.this, "Email does not exist.", Toast.LENGTH_SHORT).show();
                    } else {
                        openMainPage();
                    }

                } catch (Exception e) {
                    Toast.makeText(AdminUserLogin.this, "Error logging in", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public void backToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openMainPage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }

}