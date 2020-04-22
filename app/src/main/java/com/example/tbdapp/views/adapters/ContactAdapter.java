package com.example.tbdapp.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.activities.ChatActivity;
import com.example.tbdapp.models.Advisor;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<Advisor> mContactList;

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

    public ContactAdapter (Context context, ArrayList<Advisor> mContactList) {
        this.context = context;
        this.mContactList = mContactList;
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
        final Advisor currentItem =  mContactList.get(position);

        holder.mImageView.setImageResource(currentItem.advisorImage);
        holder.mTextView.setText(currentItem.advisorName);
        holder.mFirstLetter.setText(currentItem.advisorDescription);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("contact", currentItem);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
