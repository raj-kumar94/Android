package com.example.raj.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by raj on 21-Oct-16.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "user_details.db";
    private static final String TABLE_USERS = "user_info2";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME= "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_AGE = "age";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
        String sql = "create table "+TABLE_USERS+" ("+
                COLUMN_ID+ " integer primary key autoincrement, "+
                COLUMN_NAME+ " text, "+
                COLUMN_USERNAME+ " text, "+
                COLUMN_PASSWORD+ "text, "+
                COLUMN_AGE+ " text)";
        * */
       // String sql = "drop table user_info";
        String sql= "create table user_info2 "+
                "(name text, username text, passwd text, age text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user_info2");
        onCreate(db);
    }

    //add new rows to database
    public void addUsers(UserDetails ud)
    {
        ContentValues values = new ContentValues();
        values.put("name",ud.getName());
        values.put("username",ud.getUsername());
        values.put("passwd",ud.getPassword());
        values.put("age",ud.getAge());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("user_info2",null,values);
    }

    //deleting items from database
    public void deleteUsers(String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement("DELETE FROM user_info2 WHERE name = ?");
        stmt.bindString(1, name);
        stmt.execute();

        // db.execSQL("delete from "+TABLE_USERS+ " where "+COLUMN_NAME+" = "+ "name;");
    }

    public boolean isUserValid(String uname,String pass)
    {

        Cursor c = getReadableDatabase().rawQuery(
                "select * from user_info2 where username=? and passwd=?" ,  new String[]{uname,pass});
        if (c.getCount()>0)
            return true;
        return false;
    }


    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String uname = "rcrazy";
        String query = "select * from user_info2 ";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // Move to the first row in your results
        //check that moveToFirst returns true
         c.moveToFirst();

            while(!c.isAfterLast()) {
                if(c.getString(c.getColumnIndex("username")) != null) {
                    dbString += c.getString(c.getColumnIndex("name"))+" ";
                    dbString += c.getString(c.getColumnIndex("username"))+" ";
                    dbString += c.getString(c.getColumnIndex("passwd"))+" ";
                    dbString += c.getString(c.getColumnIndex("age"))+" ";
                    dbString += "\n";
                }
                c.moveToNext(); //added
            }


        c.close(); //added
        db.close();
        return dbString;
    }
}
