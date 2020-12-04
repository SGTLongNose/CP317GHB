package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewAccounts extends AppCompatActivity {
    private Button savings, credit, student;
    private EditText student_num;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_accounts);
        savings = (Button) findViewById(R.id.btn_savings);
        student_num = (EditText) findViewById(R.id.editTextStudentNumber);
        dataBaseHelper = new DataBaseHelper(AddNewAccounts.this);
        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Accounts account = new Accounts(dataBaseHelper.getActiveUser(), (float) 0, "Savings", null);
                dataBaseHelper.addAccount(account);
                openHomePage();
            }
        });
        credit = (Button) findViewById(R.id.btn_credit);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Accounts account = new Accounts(dataBaseHelper.getActiveUser(), (float) 0, "Credit", null);
                dataBaseHelper.addAccount(account);
                openHomePage();
            }
        });
        student = (Button) findViewById(R.id.btn_student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    public void openHomePage(){
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
}