package com.example.contacts;

import android.net.Uri;

public class ListItem {
    public Uri avatar;
    public String name;
    public String number;
    public String email;

    public ListItem(Uri avatar, String name, String number, String email) {
        this.avatar = avatar;
        this.name = name;
        this.number = number;
        this.email = email;
    }
}
