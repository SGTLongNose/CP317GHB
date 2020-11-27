package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    private ImageButton btn_home;
    private Button btn_clear, btn_save;
    private EditText firstName, lastName, mobile, email, accountNumber, accountPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       // dataBaseHelper.grabInfo();

        btn_save = (Button) findViewById(R.id.saveButton);
        firstName = (EditText) findViewById(R.id.settings_firstname);
        lastName = (EditText) findViewById(R.id.settings_lastname);
        mobile = (EditText) findViewById(R.id.settings_mobile);
        email = (EditText) findViewById(R.id.settings_email);
        accountNumber = (EditText) findViewById(R.id.settings_accountNumber);
        accountPassword = (EditText) findViewById(R.id.settings_password);

        firstName.setHint("");

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
                lastName.setText("");
                mobile.setText("");
                email.setText("");
                accountNumber.setText("");
                accountPassword.setText("");
            }
        });


    }

    public void openHomePage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
}