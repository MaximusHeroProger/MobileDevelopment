package com.example.contactssql;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void create(Contact contact);

    @Query("SELECT * FROM contacts")
    List<Contact> readAll();

    @Query("SELECT * FROM contacts WHERE id = :id")
    Contact reed(int id);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
