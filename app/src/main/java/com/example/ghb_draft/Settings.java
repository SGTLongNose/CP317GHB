package com.example.ghb_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    private ImageButton btn_home;
    private Button btn_clear, btn_save, btn_delete;
    private EditText firstName, mobile, email, accountPassword;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView message;
    private Button cancel, delete;


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

        ArrayList<String> x = dataBaseHelper.grabInfo();

        String fName = x.get(0);
        String phone = x.get(1);
        String mail = x.get(2);
        String pw = x.get(3);

        firstName.setHint("Full Name: " + fName);
        mobile.setHint("Phone Number: " + phone);
        email.setHint("Email: " + mail);
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

        btn_save = (Button) findViewById(R.id.saveButton);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstName.getText().toString().isEmpty() || mobile.getText().toString().isEmpty() || accountPassword.getText().toString().isEmpty()) {
                    Toast.makeText(Settings.this, "Error changing info. Please fill in all sections.", Toast.LENGTH_SHORT).show();
                } else {
                    dataBaseHelper.updateSettings(firstName.getText().toString(), mobile.getText().toString(), accountPassword.getText().toString());
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createDeleteDialog();

            }
        });


    }

    public void openHomePage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }

    public void openLoginPage() {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }

    public void createDeleteDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View deletePopupView = getLayoutInflater().inflate(R.layout.deletepopup, null);
        message = (TextView) deletePopupView.findViewById(R.id.tv_delete);
        delete = (Button) deletePopupView.findViewById(R.id.btn_accountDelete);
        cancel = (Button) deletePopupView.findViewById(R.id.btn_accountCancel);

        dialogBuilder.setView(deletePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dataBaseHelper.deleteOne();
                openLoginPage();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}