package com.example.contactmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    // if we need to use context inside your viewmodel
    // you should use AndroidViewModel (AVM)
    // because it contains the application context



    private MyRepository myRepository;

    //livedata
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application ) {
        super(application);
        this.myRepository = new MyRepository(application);

    }

    public LiveData<List<Contacts>>  getAllContacts(){
        allContacts = myRepository.getALlContacts();
        return allContacts;
    }

    public void addNewContacts(Contacts contact){
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        myRepository.deleteContact(contact);
    }


}
