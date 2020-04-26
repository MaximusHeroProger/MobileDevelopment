package com.example.contacts;

import android.graphics.drawable.Drawable;

public class ListItem {
    public Drawable avatar;
    public String name;
    public String number;
    public String email;

    public ListItem(Drawable avatar, String name, String number, String email) {
        this.avatar = avatar;
        this.name = name;
        this.number = number;
        this.email = email;
    }
}
