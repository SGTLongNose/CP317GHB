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

import org.w3c.dom.Text;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    private EditText Name;
    private EditText Password, email;
    private TextView Attempts;
    private Button Login;
    private Button Register;
    private int counter = 5;
    //check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        Name = (EditText) findViewById(R.id.et_Name);
        Password = (EditText) findViewById(R.id.et_Password);
        Attempts = (TextView) findViewById(R.id.tv_attmepts);
        Login = (Button) findViewById(R.id.btn_Login);
        Register = (Button) findViewById(R.id.btn_Register);

        Attempts.setText("Number of Attempts Remaining: " + String.valueOf(counter));

//        final String[] email = {Name.getText().toString()};
//        String password = Password.getText().toString();


//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validate(Name.getText().toString(), Password.getText().toString());
//            }
//        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Name.getText().toString().equals("Admin") && Password.getText().toString().equals("password")) {
                    dataBaseHelper.isAdmin(Name.getText().toString());
                    openAdminMainPage();
                } else {
                    try {
                        Log.d("Test", "------------------------------------------------------------");
                        boolean x = dataBaseHelper.isValidEmailAndPassword(Name.getText().toString(), Password.getText().toString());
                        Log.d("Test", "------------------------------------------------------------");
                        Log.d("Test1", Name.getText().toString() + "Add ON");
                        Log.d("Test2", Password.getText().toString() + "Add ON");

                        if (!x) {
                            Toast.makeText(MainActivity.this, "The combination of Email and Password is not registered in the User DB", Toast.LENGTH_SHORT).show();
                            counter--;
                            Attempts.setText("Number of Attempts Remaining: " + String.valueOf(counter));
                        } else {
                            openMainPage();
                        }

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error with registration", Toast.LENGTH_SHORT).show();
                        Log.d("1", e.getMessage());
                    }
                }

            }
        });


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

    public void openAdminMainPage() {
        Intent intent = new Intent(this, AdminUserLogin.class);
        startActivity(intent);
    }
}