package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.Random;

public class DBHelper extends SQLiteOpenHelper{
    String sql;
    Context context;
    Cursor c;
    public DBHelper(@Nullable Context context) {
        super(context, "AccountsDatabase", null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE IF NOT EXISTS Accounts(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Address VARCHAR, ContactNumber VARCHAR, BirthDate VARCHAR, Course VARCHAR, Year VARCHAR, Age VARCHAR, Username VARCHAR, Password VARCHAR, PIN VARCHAR)";
        db.execSQL(sql);

    }

    public boolean CreateAccount(String name, String address, String contact, String birthday, String course, String year, String age, String username, String password, String PIN){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Address", address);
        cv.put("ContactNumber", contact);
        cv.put("BirthDate", birthday);
        cv.put("Course", course);
        cv.put("Year", year);
        cv.put("Age", age);
        cv.put("Username", username);
        cv.put("Password", password);
        cv.put("PIN", PIN);
        long res = db.insert("Accounts", null, cv);
        if(res ==-1){
            return false;
        }else{
          return true;
        }


    }

    public Cursor FindAccount(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "SELECT * FROM Accounts WHERE Username = '"+username+"' AND Password = '"+password+"'";
        Cursor c = db.rawQuery(sql,null);
        return c;
    }

    public String GenerateRandomPIN(){
        Random r = new Random();
        String d = String.format("%04d", r.nextInt(10003));
        return d;
    }

    public Cursor FindPIN(String PIN){
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "SELECT * FROM Accounts WHERE PIN = '"+PIN+"'";
                Cursor c= db.rawQuery(sql,null);
        return c;
    }

    public Cursor FindDetailsWithName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "SELECT * FROM Accounts WHERE Name LIKE '%"+name+"%'";
        Cursor c = db.rawQuery(sql,null);
        return c;
    }

    public Cursor FindDetailsWithId(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        sql = "SELECT * FROM Accounts WHERE ID = '"+id+"'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public Cursor GetAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        sql = "SELECT * FROM Accounts";
        return db.rawQuery(sql, null);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
