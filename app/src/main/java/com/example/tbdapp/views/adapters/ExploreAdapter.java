package com.example.tbdapp.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    private ArrayList<Advisor> advisorList;

    public static class ExploreViewHolder extends RecyclerView.ViewHolder {
        public ImageView advisorImage;
        public TextView advisorName;
        public TextView advisorType;

        public ExploreViewHolder(@NonNull View v) {
            super(v);
            advisorImage = v.findViewById(R.id.advisorImage);
            advisorName = v.findViewById(R.id.advisorName);
            advisorType = v.findViewById(R.id.advisorType);
        }
    }

    public ExploreAdapter(ArrayList<Advisor> advisorList) {
        this.advisorList = advisorList;
    }

    @NonNull
    @Override
    public ExploreAdapter.ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_item, parent, false);
        ExploreViewHolder evh = new ExploreViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
        Advisor currentAdvisor = advisorList.get(position);

        holder.advisorImage.setImageResource(currentAdvisor.advisorImage);
        holder.advisorName.setText(currentAdvisor.advisorName);
        holder.advisorType.setText(currentAdvisor.advisorType);
    }

    @Override
    public int getItemCount() {
        return advisorList.size();
    }
}
