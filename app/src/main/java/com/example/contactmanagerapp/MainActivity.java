package com.example.contactmanagerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Data Source
    private ContactDatabase contactDatabase;

    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    // Adapter
    private MyAdapter myAdapter;

    // Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(handler);

        RecyclerView recyclerView = activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // adapter
        myAdapter = new MyAdapter(contactsArrayList);

        // Database
        contactDatabase = ContactDatabase.getInstance(this);

        // ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        // Inserting new Contact just for testing
        Contacts c1 = new Contacts("jack","jack@gmail.com");


        viewModel.addNewContacts(c1);
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();

                for (Contacts c:contacts){
                    Log.v("TAGY",c.getName());
                    contactsArrayList.add(c);

                }

                myAdapter.notifyDataSetChanged();
            }
        });

        //linking
        recyclerView.setAdapter(myAdapter);






    }
}