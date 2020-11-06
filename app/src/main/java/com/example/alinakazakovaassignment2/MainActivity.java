package com.example.alinakazakovaassignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Friend> friends;
    private DBHandler dbhandler;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Friend List");

        // open/ create db
        dbhandler = new DBHandler(this);
        db = dbhandler.getWritableDatabase();

//         create table (it doesn't exist)
        dbhandler.onCreate(db);

//         populate RV with friends(if any)
        friends = dbhandler.getAllFriends();

        RecyclerView rvFriends = (RecyclerView) findViewById(R.id.rvFriends);
        FriendAdapter adapter = new FriendAdapter(friends, this);
        rvFriends.setAdapter(adapter);
        rvFriends.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchFriend(View v) {
        ArrayList<Friend> searchFriends = new ArrayList<Friend>();
        EditText searchRes = (EditText) findViewById(R.id.inputSrch);
        String res = searchRes.getText().toString();
        Friend friend;

        if(res.trim().length() == 0 ) {
            searchFriends = dbhandler.getAllFriends();
            Toast.makeText(this, "Is empty", Toast.LENGTH_LONG).show();
        } else {
            if(res.trim().matches("-?\\d+(\\.\\d+)?")) {
                friend = dbhandler.getFriendByNum(res);
                Toast.makeText(this, "Is a number", Toast.LENGTH_LONG).show();
            } else {
                friend = dbhandler.getFriendByName(res);
            }

            searchFriends.add(friend);

        }

        
        RecyclerView rvFriends = (RecyclerView) findViewById(R.id.rvFriends);
        FriendAdapter adapter = new FriendAdapter(searchFriends, this);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

    }

    public void goToAddFriend(View v) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
    }
}
