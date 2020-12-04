package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.Inet4Address;

public class Registration extends AppCompatActivity {
    private ImageButton btn_back;
    Button btn_register, btn_clearRegister;
    EditText fullName, phoneNumber, emailAddress, password, rePassword;

    ArrayAdapter userArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn_register = findViewById(R.id.btn_RegisterButton);
        btn_clearRegister = findViewById(R.id.btn_clearRegister);
        fullName = findViewById(R.id.et_registerFullName);
        phoneNumber = findViewById(R.id.et_registerPhoneNumber);
        emailAddress = findViewById(R.id.et_registerEmail);
        password = findViewById(R.id.et_registerPassword);
        rePassword = findViewById(R.id.et_registerConPassword);

        dataBaseHelper = new DataBaseHelper(Registration.this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailAddress.getText().toString();

                UserModel userModel;
                try {

                    if (dataBaseHelper.isEmail(email) == false) {
                        if (password.getText().toString().equals(rePassword.getText().toString())) {
                            Accounts starterAccount = new Accounts(emailAddress.getText().toString(), (float) 250, "Savings", null, -1);
                            userModel = new UserModel(fullName.getText().toString(), phoneNumber.getText().toString(), emailAddress.getText().toString(), password.getText().toString());
                            Toast.makeText(Registration.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                            dataBaseHelper.addUser(userModel);
                            dataBaseHelper.addAccount(starterAccount);
                            openLoginScreen();

                        } else {
                            password.setText("");
                            rePassword.setText("");
                            Toast.makeText(Registration.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                            //userModel = new UserModel("error", "error", "error", "error");

                        }

                    } else {
                        emailAddress.setText("");
                        Toast.makeText(Registration.this, "Email exists in system already", Toast.LENGTH_SHORT).show();
                        //userModel = new UserModel("error", "error", "error", "error");
                    }

                }
                catch (Exception e){
                    Toast.makeText(Registration.this, "Error with registration", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel("error", "error", "error", "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Registration.this);

            }
        });

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginScreen();
            }
        });
    }



    public void openLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}