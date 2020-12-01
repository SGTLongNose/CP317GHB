package com.example.ghb_draft;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

        public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
        public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
        public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
        public static final String COLUMN_ID = "ID";
        public static final String USER_TABLE = "USER_TABLE";
        public static final String USER_EMAIL = "USER_EMAIL";
        public static final String USER_PHONE_NUMBER = "USER_PHONE_NUMBER";
        public static final String USER_FULL_NAME = "USER_FULL_NAME";
        public static final String USER_PASSWORD = "USER_PASSWORD";
        public static final String COL_WORD = "WORD";
        public static final String COL_DEFINITION = "DEFINITION";

        public static String ACTIVE_USER = "ACTIVE_USER";

        public DataBaseHelper(@Nullable Context context) {

            super(context, "customer.db", null, 1);
        }

        // this is called the first time a database is accessed. There should be code in here to create a new database.
        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT)";
            String createTableStatement2 = "CREATE TABLE " + USER_TABLE + " (" + USER_FULL_NAME + " TEXT, " + USER_PHONE_NUMBER + " TEXT, " + USER_EMAIL + " TEXT, " + USER_PASSWORD + " TEXT)";

            db.execSQL(createTableStatement);
            db.execSQL(createTableStatement2);
        }

        // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public boolean addOne(CustomerModel customerModel){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
            cv.put(COLUMN_CUSTOMER_EMAIL, customerModel.getEmail());


            long insert = db.insert(CUSTOMER_TABLE, null, cv);

            if (insert == -1){
                return false;
            }
            else {
                return true;
            }


        }

        public boolean isEmail(String email) {
            SQLiteDatabase db = getWritableDatabase();
            String selectString = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = ?";
            Cursor cursor = db.rawQuery(selectString, new String[] {email});

            boolean hasObject = false;
            if (cursor.moveToFirst()) {
                hasObject = true;

                int count = 0;
                while (cursor.moveToNext()) {
                    count++;
                }
                Log.d(null, String.format("%d records found", count));

            }
            cursor.close();
            db.close();
            return  hasObject;


        }
        public boolean isPassword(String password) {
            SQLiteDatabase db = getWritableDatabase();
            String selectString = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_PASSWORD + " = ?";
            Cursor cursor = db.rawQuery(selectString, new String[] {password});

            boolean hasObject = false;
            if (cursor.moveToFirst()) {
                hasObject = true;


                int count = 0;
                while (cursor.moveToNext()) {
                    count++;
                }
                Log.d(null, String.format("%d records found", count));


            }
            cursor.close();
            db.close();
            return  hasObject;


        }

        public boolean addUser(UserModel userModel) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv2 = new ContentValues();

            cv2.put(USER_FULL_NAME, userModel.getFullName());
            cv2.put(USER_PHONE_NUMBER, userModel.getUserPhone());
            cv2.put(USER_EMAIL, userModel.getUserEmail());
            cv2.put(USER_PASSWORD, userModel.getUserPassword());

            long insert = db.insert(USER_TABLE, null, cv2);

            if (insert == -1){
                return false;
            }
            else {
                return true;
            }
        }

        public boolean deleteOne(){
            // find customerModel in the database. If it found, delete it and return true.
            // if it is not found, return false

            SQLiteDatabase db = this.getWritableDatabase();
            String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + USER_EMAIL + "= ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {ACTIVE_USER});

            if (cursor.moveToFirst()){
                return true;
            }
            else{
                return false;
            }
        }

        public boolean deleteOne(CustomerModel customerModel){
            // find customerModel in the database. If it found, delete it and return true.
            // if it is not found, return false

            SQLiteDatabase db = this.getWritableDatabase();
            String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();
            Cursor cursor =  db.rawQuery(queryString, null);

            if (cursor.moveToFirst()){
                return true;
            }
            else{
                return false;
            }
        }


        public List<CustomerModel> getEveryone() {
            List<CustomerModel> returnList = new ArrayList<>();

            // get data from the database

            String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.moveToFirst()) {
                // loop through the cursor (result set) and create new customer objects. Put them into the return list.
                do {
                    int customerID = cursor.getInt(0);
                    String customerName = cursor.getString(1);
                    String customerEmail = cursor.getString(2);

                    CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerEmail);
                    returnList.add(newCustomer);
                } while (cursor.moveToNext());
            }
            else {
                // failure. do not add anything to the list
            }

            // close both the cursor and the db when done
            cursor.close();
            db.close();
            return returnList;
        }

    public boolean isValidEmailAndPassword(String email, String password) {
        UserModel user = new UserModel();

        // get data from the database
        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[] {email});

        boolean hasObject = false;
        if (cursor.moveToNext()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return list.
            String pw = cursor.getString(3);
            Log.d("idk", pw);
            if (pw.equals(password)) {
                hasObject = true;
            }

        }
        ACTIVE_USER = email;
        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return hasObject;
    }


    public ArrayList<String> grabInfo() {

        String userString = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = ?";

        Log.d("9", ACTIVE_USER);

        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(userString, new String[]{ACTIVE_USER});

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return list.

            String pw = cursor.getString(3);
            String name = cursor.getString(0);
            String phone = cursor.getString(1);

            list.add(name);
            list.add(phone);
            list.add(ACTIVE_USER);
            list.add(pw);
        }
        else {
            // failure. do not add anything to the list
        }
        cursor.close();
        db.close();
        return list;

    }

    public boolean updateSettings(String name, String phone, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_FULL_NAME, name);
        contentValues.put(USER_PHONE_NUMBER, phone);
        contentValues.put(USER_PASSWORD, password);

        db.update(USER_TABLE, contentValues, "USER_EMAIL = ?", new String[] {ACTIVE_USER});

        return true;

    }




}
