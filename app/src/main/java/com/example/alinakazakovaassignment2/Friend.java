package com.example.alinakazakovaassignment2;

/*A friend is defined as having a name which is unique,
a phone number (assuming this is unique) and an email address which is unique too. */

import java.util.ArrayList;

public class Friend {

    private String name;
    private String num;
    private String email;


    public Friend(String name, String num, String email) {
        this.name = name;
        this.num = num;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() { return this.num; }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() { return this.email; }

    public void setEmail(String email) {
        this.email = email;
    }


    public static ArrayList<Friend> createFriends() {
        ArrayList<Friend> friends = new ArrayList<Friend>();

        for (int i = 0; i < 5; i++) {
            friends.add(new Friend("Friend " + (i+1), String.valueOf(((i+1))), (i+1) + "@email.com"));
        }

        return friends;
    }

}