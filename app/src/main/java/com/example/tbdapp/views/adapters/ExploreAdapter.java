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
import com.example.tbdapp.activities.AdvisorProfileActivity;
import com.example.tbdapp.models.Advisor;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    private Context context;
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

    public ExploreAdapter(Context context, ArrayList<Advisor> advisorList) {
        this.context = context;
        this.advisorList = advisorList;
    }

    @NonNull
    @Override
    public ExploreAdapter.ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_item, parent, false);
        return new ExploreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, final int position) {
        final Advisor currentAdvisor = advisorList.get(position);

        holder.advisorImage.setImageResource(context.getResources().getIdentifier(currentAdvisor.avatar, "drawable", context.getPackageName()));
        holder.advisorName.setText(currentAdvisor.name);
        holder.advisorType.setText(currentAdvisor.advisorType);

        //OnClick listener for when an advisor is tapped to launch AdvisorProfileActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdvisorProfileActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("advisor", currentAdvisor);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return advisorList.size();
    }


}
