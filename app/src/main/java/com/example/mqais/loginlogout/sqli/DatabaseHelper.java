package com.example.mqais.loginlogout.sqli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.mqais.loginlogout.model.User;

import java.net.URL;

/**
 * Created by MQais on 20/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;

    private final static String DATABASE_NAME = "UserManager.db";

    private final static String TABLE_NAME = "user";

    private static final String COLUMNS_NAME_ID = "user_id";
    private static final String COLUMNS_NAME_NAME = "user_name";
    private static final String COLUMNS_NAME_EMAIL = "user_email";
    private static final String COLUMNS_NAME_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "create table " + TABLE_NAME + " (" + COLUMNS_NAME_ID + " integer primary key autoincrement,"
            + COLUMNS_NAME_NAME + " text," + COLUMNS_NAME_EMAIL + " text," + COLUMNS_NAME_PASSWORD + " text" + ")";
    private String DROP_USER_TABLE = "drop table if exists " + TABLE_NAME;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,  int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNS_NAME_NAME, user.getName());
        values.put(COLUMNS_NAME_EMAIL, user.getEmail());
        values.put(COLUMNS_NAME_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean checkUser(String email ){
        String[] colums = {
                COLUMNS_NAME_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMNS_NAME_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query( TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] colums = {
                COLUMNS_NAME_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMNS_NAME_EMAIL + " = ? " + "AND " + COLUMNS_NAME_PASSWORD + " = ?";
        String[] selectionArgs = { email,password };

        Cursor cursor = db.query( TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
}
