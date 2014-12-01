package thememeteam.com.yummycrummyapp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cassidy on 11/26/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static int myAccount = 0;
    public static int myProfile = 0;
    private static final String DATABASE_NAME = "accountManager",
    TABLE_ACCOUNTS = "accounts",
    KEY_ID = "id",
    KEY_NAME = "name",
    KEY_PASSWORD = "password",
    KEY_EMAIL = "email",
    KEY_BIRTHDAY = "birthday",
    KEY_GENDER = "gender",
    TABLE_PROFILES = "profiles",
    KEY_PROFILE_ID = "profileID",
    KEY_PROFILE_NAME = "profileName",
    KEY_PROFILE_BIRTHDAY = "profileBirthday",
    KEY_PROFILE_GENDER = "profileGender";

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
        String CREATE_TABLE_PROFILES = " CREATE TABLE " +
                TABLE_PROFILES + "("
                + KEY_ID + " INTEGER ,"
                + KEY_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_PROFILE_NAME + " TEXT ,"
                + KEY_PROFILE_BIRTHDAY + " TEXT ,"
                + KEY_PROFILE_GENDER + " TEXT " + ")";
        db.execSQL(CREATE_TABLE_PROFILES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);

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

    public void createProfile (Profile profile){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, profile.getAccountID());
        values.put(KEY_PROFILE_ID, profile.getProfileID());
        values.put(KEY_PROFILE_NAME, profile.getName());
        values.put(KEY_PROFILE_BIRTHDAY, profile.getBirthday());
        values.put(KEY_PROFILE_GENDER, profile.getGender());

        db.insert(TABLE_PROFILES, null, values);
        db.close();

    }


    public Account getAccount(String name, String password){
        String query = "Select * FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_NAME + " = \"" + name + "\"" + " AND " + KEY_PASSWORD + " = \"" + password + "\"";
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




    //this functions retrieves the correct profile based on the AccountId and the profile name. It is used to ensure that
    //every profile name is unique
    public Profile getProfile(int accountID, String name, int profileID, int action ){
        Cursor cursor;
        String queryByName = "Select * FROM " + TABLE_PROFILES + " WHERE " + KEY_ID + " = \"" + accountID + "\"" + " AND " + KEY_PROFILE_NAME + " = \"" + name + "\"";
        String queryByNumber = "Select * FROM " + TABLE_PROFILES + " WHERE " + KEY_ID + " = \"" + accountID + "\"" + " AND " + KEY_PROFILE_ID + " = \"" + profileID + "\"";
        SQLiteDatabase db = getReadableDatabase();
        if(action == 0){
            cursor = db.rawQuery(queryByName, null);}
        else{
            cursor = db.rawQuery(queryByNumber, null);
        }

        Profile profile = new Profile();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            profile.setAccountID(Integer.parseInt(cursor.getString(0)));
            profile.setProfileID(Integer.parseInt(cursor.getString(1)));
            profile.setName(cursor.getString(2));
            profile.setBirthday(cursor.getString(3));
            profile.setGender(cursor.getString(4));
        } else {
            profile = null;
        }

        db.close();
        cursor.close();
        return profile;
    }

    public boolean deleteAccount(int id){

        boolean result = false;

        String query = "Select * FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_ID + " = \"" + id + "\"";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ACCOUNTS, KEY_ID + " =? ",
                    new String[] { String.valueOf(account.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean deleteProfile(int id){

        boolean result = false;

        String query = "Select * FROM " + TABLE_PROFILES + " WHERE " + KEY_PROFILE_ID + " = \"" + id + "\"";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Profile profile = new Profile();

        if (cursor.moveToFirst()){
            profile.setProfileID(Integer.parseInt(cursor.getString(1)));
            db.delete(TABLE_PROFILES, KEY_PROFILE_ID + " =? ",
                    new String[] { String.valueOf(profile.getProfileID())});
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

    public int getProfileCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROFILES, null);
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

        int rowsAffected = db.update(TABLE_ACCOUNTS, values, KEY_ID + " =? ", new String[] {String.valueOf(account.getId())});
        db.close();

        return rowsAffected;

    }

    public List<Account> getAllAccounts(){
        List<Account> accounts = new ArrayList<Account>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNTS, null);

        if (cursor.moveToFirst()){
            do {
                accounts.add(new Account(Integer.parseInt(cursor.getString(0)), //get the id
                        cursor.getString(1), //get the name
                        cursor.getString(2), //get the password
                        cursor.getString(3), //get the email
                        cursor.getString(4), //get the birthday
                        cursor.getString(5)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accounts;

    }


    //This function grabs all of the profiles associated with a particular account id and return
    //an ArrayList with all of those profiles
    public List<Profile> getCorrectProfiles(int id){
        List<Profile> profiles = new ArrayList<Profile>();
        String query = "Select * FROM " + TABLE_PROFILES + " WHERE " + KEY_ID + " = \"" + id + "\"";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                profiles.add(new Profile(Integer.parseInt(cursor.getString(0)), //get the id
                        Integer.parseInt(cursor.getString(1)), //get the name
                        cursor.getString(2), //get the password
                        cursor.getString(3), //get the birthday
                        cursor.getString(4))); //get the gender
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return profiles;

    }

    //this function gets all of the profiles for all accounts
    public List<Profile> getAllProfiles(){
        List<Profile> profiles = new ArrayList<Profile>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROFILES, null);

        if (cursor.moveToFirst()){
            do {
                profiles.add(new Profile(Integer.parseInt(cursor.getString(0)), //get the id
                        Integer.parseInt(cursor.getString(1)), //get the name
                        cursor.getString(2), //get the password
                        cursor.getString(3), //get the birthday
                        cursor.getString(4))); //get the gender
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return profiles;

    }

    public int getMyAccount(){
        return myAccount;
    }

    public void setMyAccount(int id){
        myAccount = id;
    }

    public int getMyProfile(){ return myProfile;}

    public void setMyProfile(int id){
        myProfile = id;
    }
}
