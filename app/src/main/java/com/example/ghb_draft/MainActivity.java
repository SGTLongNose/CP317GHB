package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    private EditText Name;
    private EditText Password;
    private TextView Attempts;
    private Button Login;
    private Button Register;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.et_Name);
        Password = (EditText) findViewById(R.id.et_Password);
        Attempts = (TextView) findViewById(R.id.tv_attmepts);
        Login = (Button) findViewById(R.id.btn_Login);
        Register = (Button) findViewById(R.id.btn_Register);

        Attempts.setText("Number of Attempts Remaining: " + String.valueOf(counter));

        String email = Name.getText().toString();
        String password = Password.getText().toString();

//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validate(Name.getText().toString(), Password.getText().toString());
//            }
//        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("Test","------------------------------------------------------------");
                    Log.d("Test1",Name.getText().toString() + "Add ON");
                    Log.d("Test2",Password.getText().toString() + "Add ON");
//                    if (dataBaseHelper.isEmail(email) == true){
//                        if (dataBaseHelper.isPassword(password) == true) {
//                            openMainPage();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(MainActivity.this, "Email couldn't be found in the database", Toast.LENGTH_SHORT).show();
//                    }
                    if(!dataBaseHelper.isValidEmailAndPassword(Name.getText().toString(), Password.getText().toString()).validUser()){
                        Toast.makeText(MainActivity.this, "The combination of Email and Password is not registered in the User DB", Toast.LENGTH_SHORT).show();
                        counter--;
                        Attempts.setText("Number of Attempts Remaining: " + String.valueOf(counter));
                    }else{
                        openMainPage();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error with registration", Toast.LENGTH_SHORT).show();
                }

            }
       });

//        public void saveLogin(Login) {
//            Intent Login = new Intent(MainActivity.this, Login.class);
//
//
//
//        }



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

    }
    public void openRegister() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
    public void openMainPage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }

//    private void validate(String userName, String userPassword) {
//        if ((userName.equals("Harlan")) && (userPassword.equals("123"))) {
//            Intent intent = new Intent(MainActivity.this, Main_Page.class);
//            startActivity(intent);
//        } else {
//            counter--;
//
//            Attempts.setText("Number of Attempts Remaining: " + String.valueOf(counter));
//
//            if (counter == 0) {
//                Login.setEnabled(false);
//            }
//        }
//    }

}
