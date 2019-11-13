package com.example.alinakazakovaassignment2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFriendActivity extends AppCompatActivity {

    private DBHandler dbhandler;
    private SQLiteDatabase db;
    private MainActivity ma = new MainActivity();
    private EditText nameTxt;
    private EditText numberTxt;
    private EditText emailTxt;
    private String name;
    private String number;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_friend);

    }

    public void addFriend(View v) {
        if(validateFields(v)) {
            dbhandler = new DBHandler(this);
            db = dbhandler.getWritableDatabase();

            Friend friend = new Friend(name, number, email);
            dbhandler.addFriend(friend);
            this.finish();
        }
    }

    public boolean validateFields(View v) {
        boolean passed = false;
        nameTxt = (EditText) findViewById(R.id.addTxtName);
        numberTxt =  (EditText) findViewById(R.id.addTxtNumber);
        emailTxt = (EditText) findViewById(R.id.addTxtEmail);

        name = nameTxt.getText().toString();
        number = numberTxt.getText().toString();
        email = emailTxt.getText().toString();

        if(name.matches("-?\\d+(\\.\\d+)?")) {
            Toast.makeText(this, "Name can't be numbers", Toast.LENGTH_LONG).show();
        } else if (name.trim().isEmpty()) {
            Toast.makeText(this, "Name can't be empty", Toast.LENGTH_LONG).show();
        } else if(!number.matches("[0-9]+")) {
            Toast.makeText(this, "Number must be digits", Toast.LENGTH_LONG).show();
        } else if(email.trim().isEmpty()) {
            Toast.makeText(this, "Email can't be empty", Toast.LENGTH_LONG).show();
        } else if(!email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+")) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show();
        }
        else {
            passed = true;
        }
        return passed;
    }

}
