package com.example.contactssql;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
