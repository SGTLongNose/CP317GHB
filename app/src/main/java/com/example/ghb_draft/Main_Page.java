package com.example.ghb_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Page extends AppCompatActivity {
    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newcontactpop_firstname, getNewcontactpop_lastname, getNewcontactpop_mobile, getNewcontactpop_email;
    private Button newcontactpopup_cancel, getNewcontactpopup_save;
    private ListView accountList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);

        accountList = (ListView)findViewById(R.id.lv_accountList);

        AccountClass[] countries = new AccountClass[]{
                new AccountClass("Savings", "Amount",R.drawable.ic_baseline_money_24),
                new AccountClass("Credit", "Amount",R.drawable.ic_baseline_credit_card_24),
                new AccountClass("Debit", "Amount",R.drawable.ic_baseline_monetization_on_24),

        };

        AccountAdapter adapter = new AccountAdapter(this,R.layout.accountlist,countries);
        accountList.setAdapter(adapter);


        button = (ImageButton) findViewById(R.id.btn_SendFunds);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSendFunds();
            }
        });
        button2 = (ImageButton) findViewById(R.id.btn_ETransfer);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openETransfer();
            }
        });
        button3 = (ImageButton) findViewById(R.id.btn_SavedContacts);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactList();
            }
        });
        button4 = (ImageButton) findViewById(R.id.btn_Settings);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSettings();
            }
        });
        button5 = (ImageButton) findViewById(R.id.btn_EHistory);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openETranHistory();
            }
        });

    }
    public void openETransfer() {
        Intent intent = new Intent(this, SendEtransfer.class);
        startActivity(intent);
    }
    public void openSendFunds() {
        Intent intent = new Intent(this, SendFunds.class);
        startActivity(intent);
    }
    public void openContactList() {
        Intent intent = new Intent(this, ContactList.class);
        startActivity(intent);
    }
    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openETranHistory() {
        Intent intent = new Intent(this, ETran_History.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu1) {
            Toast.makeText(getApplicationContext(), "An admin will be with you shortly.", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.menu2) {
            //Toast.makeText(getApplicationContext(), "Hello menu 2", Toast.LENGTH_LONG).show();
            createNewContactDialog();
        }
        if (id == R.id.menu3) {
            Toast.makeText(getApplicationContext(), "Heya", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.submenu1) {
            Toast.makeText(getApplicationContext(), "Hello sub", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.submenu2) {
            Toast.makeText(getApplicationContext(), "sub Hello", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.it_log) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);

    }

    public void createNewContactDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        newcontactpop_firstname = (EditText) contactPopupView.findViewById(R.id.newcontactpopup_firstname);
        getNewcontactpop_lastname = (EditText) contactPopupView.findViewById(R.id.newcontactpopup_lastname);
        getNewcontactpop_mobile = (EditText) contactPopupView.findViewById(R.id.newcontactpopup_mobile);
        getNewcontactpop_email = (EditText) contactPopupView.findViewById(R.id.newcontactpopup_email);

        getNewcontactpopup_save = (Button) contactPopupView.findViewById(R.id.saveButton);
        newcontactpopup_cancel = (Button) contactPopupView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        getNewcontactpopup_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

            }
        });
        newcontactpopup_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                dialog.dismiss();
            }
        });

    }
}