package com.example.contactssql;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "contacts")
public class Contact {

    @PrimaryKey
    public int id;

    @ColumnInfo (name = "name")
    public String name;

    @ColumnInfo (name = "surname")
    public String surname;

    @ColumnInfo (name = "patronymic")
    public String patronymic;

    @ColumnInfo (name = "phone")
    public String phone;

    @ColumnInfo (name = "email")
    public String email;

    @ColumnInfo (name = "photo", typeAffinity = ColumnInfo.BLOB)
    public byte[] photoData;

    @Ignore
    public Uri photo;

    @Ignore
    @Override
    public String toString() {
        return (surname.isEmpty()  ? "" : surname) +
                (name.isEmpty() ? "" :
                surname.isEmpty() ? "" : " ") + name +
                (patronymic.isEmpty() ? "" :
                (surname.isEmpty() || name.isEmpty() ? "" : " ") + patronymic);
    }
}
