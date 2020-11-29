package com.example.ghb_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newcontactpop_firstname, getNewcontactpop_lastname, getNewcontactpop_mobile, getNewcontactpop_email;
    private Button newcontactpopup_cancel, getNewcontactpopup_save;
    // references to buttons and other controls on the layout
    // references to buttons and other controls on the layout
    Button btn_add;
    ImageButton btn_home;
    EditText et_name, et_email;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        btn_add = findViewById(R.id.btn_add);
        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        lv_customerList = findViewById(R.id.lv_customerList);

        dataBaseHelper = new DataBaseHelper(ContactList.this);
        ShowCustomersOnListView(dataBaseHelper);

        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        // button listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CustomerModel customerModel;
                try {
                    if (et_email.getText().toString().isEmpty() || et_name.getText().toString().isEmpty()) {
                        Toast.makeText(ContactList.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    } else {
                        customerModel = new CustomerModel(-1, et_name.getText().toString(), et_email.getText().toString());
                    }
                }
                catch (Exception e){
                    Toast.makeText(ContactList.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ContactList.this);

                ShowCustomersOnListView(dataBaseHelper);

            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(ContactList.this, "Deleted" + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(ContactList.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_customerList.setAdapter((customerArrayAdapter));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_addContact) {
            createNewContactDialog();
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
    public void openHomePage() {
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
}