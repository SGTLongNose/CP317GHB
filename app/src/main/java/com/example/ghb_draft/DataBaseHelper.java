package com.example.ghb_draft;

import android.accounts.Account;
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

        public static final String ACCOUNTS_TABLE = "ACCOUNTS_TABLE";
        public static final String ACCOUNT_EMAIL = "ACCOUNT_EMAIL";
        public static final String ACCOUNT_BALANCE = "ACCOUNT_BALANCE";
        public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
        public static final String ACCOUNT_ID = "ACCOUNT_ID";
        public static final String STUDENT_NUMBER = "STUDENT_NUMBER";
        public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
        public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
        public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
        public static final String USER_ID = "USER_ID";
        public static final String USER_TABLE = "USER_TABLE";
        public static final String USER_EMAIL = "USER_EMAIL";
        public static final String USER_PHONE_NUMBER = "USER_PHONE_NUMBER";
        public static final String USER_FULL_NAME = "USER_FULL_NAME";
        public static final String USER_PASSWORD = "USER_PASSWORD";
        public static final String COL_WORD = "WORD";
        public static final String COL_DEFINITION = "DEFINITION";

        public static boolean ACTIVE_ADMIN = false;
        public static String ACTIVE_USER = "ACTIVE_USER";
        public static Accounts OUTGOING;
        public static Accounts RECIEVING;
        public DataBaseHelper(@Nullable Context context) {

            super(context, "customer.db", null, 1);
        }

        // this is called the first time a database is accessed. There should be code in here to create a new database.
        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + USER_ID + " TEXT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT)";
            String createTableStatement2 = "CREATE TABLE " + USER_TABLE + " (" + USER_FULL_NAME + " TEXT, " + USER_PHONE_NUMBER + " TEXT, " + USER_EMAIL + " TEXT, " + USER_PASSWORD + " TEXT)";
            String createTableStatement3 = "CREATE TABLE " + ACCOUNTS_TABLE + " (" + ACCOUNT_EMAIL + " TEXT, " + ACCOUNT_BALANCE + " FLOAT, " + ACCOUNT_TYPE + " TEXT, " + STUDENT_NUMBER + " INTEGER, " + ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT)";

            db.execSQL(createTableStatement);
            db.execSQL(createTableStatement2);
            db.execSQL(createTableStatement3);
        }

        // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public boolean addOneCard(int ID) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(STUDENT_NUMBER, ID);

            db.update(ACCOUNTS_TABLE, cv, "ACCOUNT_EMAIL = ?", new String[] {ACTIVE_USER});


            return true;

        }

        public boolean addOne(CustomerModel customerModel){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(USER_ID, customerModel.getUser());
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
        public boolean addOne(Accounts account){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(ACCOUNT_EMAIL, account.getEmail());
            cv.put(ACCOUNT_BALANCE, account.getBalance());
            cv.put(ACCOUNT_TYPE, account.getType());
            cv.put(STUDENT_NUMBER, account.getStudentNumber());


            long insert = db.insert(ACCOUNTS_TABLE, null, cv);

            if (insert == -1){
                return false;
            } else {
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

        public boolean addAccount(Accounts account) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv3 = new ContentValues();
            cv3.put(ACCOUNT_EMAIL, account.getEmail());
            cv3.put(ACCOUNT_BALANCE, account.getBalance());
            cv3.put(ACCOUNT_TYPE, account.getType());
            cv3.put(STUDENT_NUMBER, account.getStudentNumber());
            long insert = db.insert(ACCOUNTS_TABLE, null, cv3);
            if (insert == -1){
                return false;
            }else{
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
            String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_EMAIL + " = ?";
            Cursor cursor =  db.rawQuery(queryString, new String[]{customerModel.getEmail()});

            if (cursor.moveToFirst()){
                return true;
            }
            else{
                return false;
            }
        }
        public boolean deleteAccount(Accounts account){

            SQLiteDatabase db = this.getWritableDatabase();
            String queryString = "DELETE FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_ID + " = ?";
            Cursor cursor =  db.rawQuery(queryString, new String[]{account.getId().toString()});

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
            SQLiteDatabase db = this.getReadableDatabase();
            String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + USER_ID + " = ?";



            Cursor cursor = db.rawQuery(queryString, new String[] {ACTIVE_USER});
            if (cursor.moveToFirst()) {
                // loop through the cursor (result set) and create new customer objects. Put them into the return list.
                do {
                    String userID = cursor.getString(0);
                    String customerName = cursor.getString(1);
                    String customerEmail = cursor.getString(2);
                    CustomerModel newCustomer = new CustomerModel(userID, customerName, customerEmail);
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


        public List<Accounts> getAccounts() {
            List<Accounts> returnList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String queryString = "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_EMAIL + " = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {ACTIVE_USER});
            if (cursor.moveToFirst()) {
                do {
                    String accountEmail = cursor.getString(0);
                    Float accountBalance = cursor.getFloat(1);
                    String accountType = cursor.getString(2);
                    Integer studentNumber = cursor.getInt(3);
                    Integer accountId = cursor.getInt(4);
                    Accounts account = new Accounts(accountEmail, accountBalance, accountType, studentNumber, accountId);
                    returnList.add(account);

                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return returnList;
        }
        public List<Accounts> getOutgoingAccounts() {
            List<Accounts> returnList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String queryString = "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_EMAIL + " = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {ACTIVE_USER});
            if (cursor.moveToFirst()) {
                do {
                    String accountType = cursor.getString(2);
                    if (!(accountType.equals("One Card"))) {
                        String accountEmail = cursor.getString(0);
                        Float accountBalance = cursor.getFloat(1);
                        Integer studentNumber = cursor.getInt(3);
                        Integer accountId = cursor.getInt(4);
                        Accounts account = new Accounts(accountEmail, accountBalance, accountType, studentNumber, accountId);
                        returnList.add(account);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return returnList;
        }
        public List<Accounts> getSelectedAccounts() {
            List<Accounts> returnList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Accounts outgoing = getOUTGOING();
            Accounts receiving = getRECIEVING();
            returnList.add(outgoing);
            returnList.add(receiving);
            db.close();
            return returnList;
        }

        public void isAdmin (String email) {
            ACTIVE_USER = email;

            if (email.equals("Admin")){
                ACTIVE_ADMIN = true;
            } else {
                ACTIVE_ADMIN = false;
            }
        }

        public boolean Admin() {
            return ACTIVE_ADMIN;
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
            //special important line
            ACTIVE_USER = email;
            // close both the cursor and the db when done
            cursor.close();
            db.close();
            return hasObject;
        }
        public boolean isValidEmail(String email) {
            String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = ?";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, new String[] {email});
            boolean hasObject = false;
            if (cursor.moveToNext()) {
                hasObject = true;
            } else {

            }

            db.close();
            cursor.close();
            return hasObject;
        }
        public boolean findStudentAccount(){
            String query = "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_EMAIL + " =?";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, new String[] {ACTIVE_USER});
            boolean hasObject = true;
            if (cursor.moveToFirst()) {
                do {
                    String accountType = cursor.getString(2);
                    if (accountType.equals("One Card")){
                        hasObject = false;
                    }else{
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return hasObject;
        }

        public ArrayList<String> grabInfo() {

            String userString = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = ?";

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
        public boolean sendMoney(Accounts outgoing, Accounts receiving, float amount){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            float outgoingBalance = outgoing.getBalance();
            outgoingBalance -= amount;
            if (outgoingBalance >= 0) {
                int outgoingId = outgoing.getId();
                cv.put(ACCOUNT_BALANCE, outgoingBalance);
                db.update(ACCOUNTS_TABLE, cv, "ACCOUNT_ID = " + outgoingId, null);
                ContentValues cv2 = new ContentValues();
                float reveivingBalance = receiving.getBalance();
                reveivingBalance += amount;
                int receivingId = receiving.getId();
                cv2.put(ACCOUNT_BALANCE, reveivingBalance);
                db.update(ACCOUNTS_TABLE, cv2, "ACCOUNT_ID = " + receivingId, null);
                return true;
            } else {
                return false;
            }
        }

        public boolean sendEtransfer(String user, String rec, int amount) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cvUser = new ContentValues();
            ContentValues cvRec = new ContentValues();

            String userString = "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_EMAIL + " = ?";
            String recString = "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE " + ACCOUNT_EMAIL + " = ?";

            Cursor cursor = db.rawQuery(userString, new String[]{user});
            Cursor cursor2 = db.rawQuery(recString, new String[]{rec});


            if (cursor.moveToFirst()) {
                int x = Integer.parseInt(ACCOUNT_BALANCE);
                int y = x - amount;
                cvUser.put(ACCOUNT_BALANCE, y);
            } else {
                return false;
            }
            if (cursor2.moveToFirst()) {
                int x2 = Integer.parseInt(ACCOUNT_BALANCE);
                int y2 = x2 + amount;
                cvRec.put(ACCOUNT_BALANCE, y2);
            } else {
                return false;
            }


            return true;
        }
        public String getActiveUser(){
            return ACTIVE_USER;
        }

        public static void setActiveUser(String activeUser) {
            ACTIVE_USER = activeUser;
        }

        public static Accounts getOUTGOING() {
            return OUTGOING;
        }

        public static void setOUTGOING(Accounts account) {
            OUTGOING = account;
        }

        public static Accounts getRECIEVING() {
            return RECIEVING;
        }

        public static void setRECIEVING(Accounts account) {
            RECIEVING = account;
        }

    }