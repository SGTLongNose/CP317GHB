package com.example.ghb_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SendEtransfer extends AppCompatActivity {
    private ImageButton btn_home;
    private Button btn_sendTrans, btn_clearTrans;
    private EditText recipientAccount, userAccount, transAmount;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_etransfer);

        recipientAccount = (EditText) findViewById(R.id.sendEtransfer_To);
        userAccount = (EditText) findViewById(R.id.sendEtransfer_WhichAccount);
        transAmount = (EditText) findViewById(R.id.sendEtransfer_Amount);

        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        btn_clearTrans = (Button) findViewById(R.id.btn_clearTrans);
        btn_clearTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipientAccount.setText("");
                userAccount.setText("");
                transAmount.setText("");
            }
        });

        btn_sendTrans = (Button) findViewById(R.id.btn_sendTrans);
        btn_sendTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.sendEtransfer(userAccount.getText().toString(), recipientAccount.getText().toString(), Integer.parseInt(transAmount.getText().toString()));
                openHomePage();
                Toast.makeText(getApplicationContext(), "Hello menu 2", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void openHomePage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
}