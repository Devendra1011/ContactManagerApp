package com.example.contactmanagerapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// Each entity is defines as table
// Each item define column

@Entity(tableName = "contacts_table")
public class Contacts {

    // ColumnInfo Explicitly provide name the column

    @PrimaryKey(autoGenerate = true) //  Necessary for creation of table
    @ColumnInfo(name = "contact_id")
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;


    // Constructor
    public Contacts( String name, String email) {
        this.name = name;
        this.email = email;
    }


    // Empty Constructor is to prevent any error
    public Contacts() {

    }

    // Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
