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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main_Page extends AppCompatActivity {
    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText oneCard_ID;
    private Button oneCard_cancel, oneCard_add;
    private ListView accountList;
    ArrayAdapter accountArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);

        accountList = (ListView)findViewById(R.id.lv_accountList);
        dataBaseHelper = new DataBaseHelper(Main_Page.this);

        ShowAccountsOnListView(dataBaseHelper);

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
    private void ShowAccountsOnListView(DataBaseHelper dataBaseHelper2) {
        accountArrayAdapter = new ArrayAdapter<>(Main_Page.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAccounts());
        accountList.setAdapter((accountArrayAdapter));
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
        final View contactPopupView = getLayoutInflater().inflate(R.layout.onecardpopup, null);
        oneCard_ID = (EditText) contactPopupView.findViewById(R.id.oneCard_ID);

        oneCard_add = (Button) contactPopupView.findViewById(R.id.oneCard_add);
        oneCard_cancel = (Button) contactPopupView.findViewById(R.id.oneCard_cancel);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        oneCard_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                dataBaseHelper.addOneCard(Integer.parseInt(oneCard_ID.getText().toString()));

                dialog.dismiss();
            }
        });
        oneCard_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                dialog.dismiss();
            }
        });

    }
}