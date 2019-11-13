package com.example.alinakazakovaassignment2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mad2.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_FRIEND = "FRIEND";
    ArrayList<Friend> friends;
    Context context;
    private Friend f;

    private FriendAdapter adapter;
    private SQLiteDatabase db;

    public DBHandler( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }


    public void onCreate( SQLiteDatabase db ) {
        String createTable =
                "create table if not exists " + TABLE_FRIEND  +
                        "(name text primary key not null unique, phone text not null unique, email text not null unique)";
        db.execSQL(createTable);
    }

    public void onUpgrade( SQLiteDatabase db, int oldversion, int newversion ) {
        db.execSQL("drop table if exists " + TABLE_FRIEND + ";" );
        onCreate(db);
    }

    public void addFriend(Friend friend) {
        try {
            db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLE_FRIEND + "(name, phone, email) " +
                    "VALUES('" + friend.getName() + "','" +
                    friend.getNum() + "','" +
                    friend.getEmail() + "');");

            Toast.makeText(context, "Added friend", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e){
            Toast.makeText(context, "Error inserting friend", Toast.LENGTH_LONG).show();
        }
        db.close();

    }

    public void updateFriend(Friend friend, String update_name) {
        try {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_FRIEND + " " +
                    "SET name = '" + friend.getName() + "', " +
                    "phone = '" + friend.getNum() + "', " +
                    "email = '" + friend.getEmail() + "' " +
                        "WHERE name = '" + update_name + "';");
        } catch (SQLiteException e){
            Toast.makeText(context, "Error updating friend", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void dropTable(String table) {
        db = this.getWritableDatabase();
        db.execSQL("drop table if exists " + table + ";" );
        db.close();
    }

        public Friend getFriendByName(String name) {

            db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FRIEND +
                    " WHERE name = '" + name.trim() + "'", null);
            c.moveToNext();

            String cName = c.getString(c.getColumnIndex("name"));
            String cNum = c.getString(c.getColumnIndex("phone"));
            String cEmail = c.getString(c.getColumnIndex("email"));
            f = new Friend(cName, cNum, cEmail);
            c.close();
            db.close();
        return f;

    }

    public Friend getFriendByNum(String num) {

            db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FRIEND +
                    " WHERE num = '" + num.trim() + "'", null);
            c.moveToNext();

            String cName = c.getString(c.getColumnIndex("name"));
            String cNum = c.getString(c.getColumnIndex("phone"));
            String cEmail = c.getString(c.getColumnIndex("email"));
            f = new Friend(cName, cNum, cEmail);
            c.close();
            db.close();

        return f;

    }


    public ArrayList<Friend> getAllFriends() {
        String sqlQuery = "select * from " + TABLE_FRIEND;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Friend> friends = new ArrayList<Friend>( );
        while( cursor.moveToNext()) {
            f = new Friend(cursor.getString( 0 ) ,
                    cursor.getString( 1 ) , cursor.getString( 2) );

            friends.add( f );
        }
        cursor.close();
        db.close();
        return friends;
    }

}
