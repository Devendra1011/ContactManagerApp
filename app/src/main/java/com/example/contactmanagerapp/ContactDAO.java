package com.example.contactmanagerapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


// DAO  is the interface that define set of methods for performing database operation on the entities
//  Insert,Delete,Update,Select,Other Queries
@Dao
public interface ContactDAO {

    @Insert
    void insert(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Query("SELECT * FROM contacts_table");
    List<Contacts> getAllContacts();
}
