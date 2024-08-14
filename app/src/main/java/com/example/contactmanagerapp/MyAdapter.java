package com.example.contactmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contactsArrayList;

    public MyAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creating new View Holders for items in recyclerview
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contact_list_item, parent, false);

        return new ContactViewHolder(contactListItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // Called by the recyclerview when it needs to display or update an item
        // at a specific position in the list or grid

        Contacts currentContact = contactsArrayList.get(position);
        holder.contactListItemBinding.setContact(currentContact);


    }

    @Override
    public int getItemCount() {
        if (contactsArrayList != null) {
            return contactsArrayList.size();
        } else {
            return 0;
        }
    }

    // setter


    public void setContactsArrayList(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        // Inform the associated Recyclerview that the underlying
        // dataset has changed and the Recyclerview should refresh
        // its views to reflect these changes

        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        // new concept
        // data binding with recycler view
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
