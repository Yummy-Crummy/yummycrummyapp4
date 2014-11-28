package thememeteam.com.yummycrummyapp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cassidy on 11/26/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountManager",
    TABLE_ACCOUNTS = "accounts",
    KEY_ID = "id",
    KEY_NAME = "name",
    KEY_PASSWORD = "password",
    KEY_EMAIL = "email",
    KEY_BIRTHDAY = "address",
    KEY_GENDER = "gender";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_ACCOUNTS = " CREATE TABLE " +
                TABLE_ACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_NAME + " TEXT ,"
                + KEY_PASSWORD + " TEXT ,"
                + KEY_EMAIL + " TEXT ,"
                + KEY_BIRTHDAY + " TEXT ,"
                + KEY_GENDER + " TEXT " + ")";
        db.execSQL(CREATE_TABLE_ACCOUNTS);
        int love = 0;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ACCOUNTS);

        onCreate(db);
    }

    public void createAccount(Account account){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, account.getName());
        values.put(KEY_PASSWORD, account.getPassword());
        values.put(KEY_EMAIL, account.getEmail());
        values.put(KEY_BIRTHDAY, account.getBirthday());
        values.put(KEY_GENDER, account.getGender());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public Account getAccount(int id){
        String query = "Select * FROM " + TABLE_ACCOUNTS + "WHERE" + KEY_ID + " = \"" + id + "\"";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            account.setId(Integer.parseInt(cursor.getString(0)));
            account.setName(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setEmail(cursor.getString(3));
            account.setBirthday(cursor.getString(4));
            account.setGender(cursor.getString(5));
        } else {
            account = null;
        }

        db.close();
        cursor.close();
        return account;
    }

    public boolean deleteAccount(int id){

        boolean result = false;

        String query = "Select * FROM" + TABLE_ACCOUNTS + "WHERE" + KEY_ID + " = \"" + id + "\"";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ACCOUNTS, KEY_ID + "=?",
                    new String[] { String.valueOf(account.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public int getAccountsCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNTS, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }

    public int updateAccount(Account account){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName());
        values.put(KEY_PASSWORD, account.getPassword());
        values.put(KEY_EMAIL, account.getEmail());
        values.put(KEY_BIRTHDAY, account.getBirthday());
        values.put(KEY_GENDER, account.getGender());

        return db.update(TABLE_ACCOUNTS, values, KEY_ID + "=?", new String[] {String.valueOf(account.getId())});

    }

    public List<Account> getAllAccounts(){
        List<Account> accounts = new ArrayList<Account>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNTS, null);

        if (cursor.moveToFirst()){
            do {
                Account account = new Account(Integer.parseInt(cursor.getString(0)), //get the id
                        cursor.getString(1), //get the name
                        cursor.getString(2), //get the password
                        cursor.getString(3), //get the email
                        cursor.getString(4), //get the birthday
                        cursor.getString(5)); //get the gender
                accounts.add(account);
            }
            while (cursor.moveToNext());
        }
        return accounts;

    }
}
