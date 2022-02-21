package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "tabletka.db";

    public DBHelper(Context context) {
        super(context, "tabletka.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE User(user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT, " +
                "password TEXT, email TEXT, birthday TEXT, weight REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS User");
    }

    public Boolean insertUser(String username, String passwd, String email, String birthday, Long weight){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", username);
        contentValues.put("password",passwd);
        contentValues.put("email", email);
        contentValues.put("birthday", birthday);
        contentValues.put("weight", weight);
        long result = DB.insert("User",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkUser(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM User WHERE email=?",new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserPassword(String email, String passwd){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM User WHERE email=? AND password=?",new String[]{email,passwd});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public User searchUser(String email){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM User WHERE email=?",new String[]{email});
        User user= new User(cursor.getString(1),cursor.getString(3),cursor.getString(2),cursor.getString(4),cursor.getLong(5));
        return user;
    }
}
