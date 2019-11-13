package com.example.alinakazakovaassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditFriendActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputNum;
    private EditText inputEmail;
    private DBHandler dbhandler;
    private SQLiteDatabase db;
    private MainActivity ma = new MainActivity();
    private int position;
    private EditText nameTxt;
    private EditText numberTxt;
    private EditText emailTxt;
    private String name;
    private String number;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);

         inputName = (EditText) findViewById(R.id.editTxtName);
         inputNum = (EditText) findViewById(R.id.editTxtNumber);
         inputEmail = (EditText) findViewById(R.id.editTxtEmail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(FriendAdapter.NAME);
        String number = intent.getStringExtra(FriendAdapter.NUMBER);
        String email = intent.getStringExtra(FriendAdapter.EMAIL);
        position = intent.getIntExtra(FriendAdapter.POSITION, 0);

        getSupportActionBar().setTitle("Edit " + name);

        Toast.makeText(this, email, Toast.LENGTH_LONG).show();

        inputName.setText(name);
        inputNum.setText(number);
        inputEmail.setText(email);

    }


    public void saveFriend(View v) {
        if(validateFields(v)) {
            // save changes to the database
            Friend updated_friend = new Friend(inputName.getText().toString(), inputNum.getText().toString(), inputEmail.getText().toString());
            dbhandler = new DBHandler(this);
            Friend current_friend = dbhandler.getAllFriends().get(position);
            dbhandler.updateFriend(updated_friend, current_friend.getName());
            this.finish();
        }
    }

    public boolean validateFields(View v) {
        boolean passed = false;
        nameTxt = (EditText) findViewById(R.id.editTxtName);
        numberTxt =  (EditText) findViewById(R.id.editTxtNumber);
        emailTxt = (EditText) findViewById(R.id.editTxtEmail);

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
