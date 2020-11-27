package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    private ImageButton btn_home;
    private Button btn_clear, btn_save;
    private EditText firstName, lastName, mobile, email, accountNumber, accountPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dataBaseHelper = new DataBaseHelper(Settings.this);

        btn_save = (Button) findViewById(R.id.saveButton);
        firstName = (EditText) findViewById(R.id.settings_firstname);
        mobile = (EditText) findViewById(R.id.settings_mobile);
        email = (EditText) findViewById(R.id.settings_email);
        accountPassword = (EditText) findViewById(R.id.settings_password);

        ArrayList<String> x = dataBaseHelper.grabInfo("razor234508@gmail.com");

        String fName = x.get(0);
        String phone = x.get(1);
        String mail = x.get(2);
        String pw = x.get(3);

        firstName.setHint("Full Name: " + fName);
        mobile.setHint("Phone Number: " + phone);
        email.setHint("Email Address: " + mail);
        accountPassword.setHint("Password: " + pw);


        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        btn_clear = (Button) findViewById(R.id.clearButton);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.setText("");
                mobile.setText("");
                email.setText("");
                accountPassword.setText("");
            }
        });


    }

    public void openHomePage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
}