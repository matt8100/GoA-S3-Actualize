package com.example.tbdapp.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.views.ContactItem;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<ContactItem> mContactList;

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
        public TextView mFirstLetter;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.contactImage);
            mTextView = itemView.findViewById(R.id.contactName);
            mFirstLetter = itemView.findViewById(R.id.contactFirstLetter);
        }
    }

    public ContactAdapter (ArrayList<ContactItem> contactList) {
        mContactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ContactViewHolder evh = new ContactViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactItem currentItem =  mContactList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView.setText(currentItem.getText());
        holder.mFirstLetter.setText(currentItem.getFirstLetter());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
