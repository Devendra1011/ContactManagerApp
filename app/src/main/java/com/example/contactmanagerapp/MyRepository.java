package com.example.contactmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository {

    // The Available Database
    // Room Database
    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;

    public MyRepository(Application application) {

        ContactDatabase  contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        //it is used to make ui thread free to handle user interaction and it work
        //sequentially in background

        executor = Executors.newSingleThreadExecutor();

        // While database operation occur in background we often need to update the ui with the result
        // updates should be done in ui thread
        // any ui related task will executed in main thread
        // Used for updating the UI
        handler = new Handler(Looper.getMainLooper());
    }
    // every method
    public void addContact(Contacts contact) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);

            }
        });
    }
    public void deleteContact(Contacts contact) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);

            }
        });

    }

    public LiveData<List<Contacts>> getALlContacts() {
        return contactDAO.getAllContacts();
    }
}
