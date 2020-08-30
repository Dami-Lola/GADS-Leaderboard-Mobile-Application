package com.damio.gadsleaderboard.learningleaders;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.damio.gadsleaderboard.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningLeadersRecyclerAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.ViewHolder> {

    private List<LearningLeadersEntity> learningLeadersEntityList;
    Context context;

    public LearningLeadersRecyclerAdapter(List<LearningLeadersEntity> learningLeadersEntityList, Context context) {
        this.learningLeadersEntityList = learningLeadersEntityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learningleaders_recyclerview_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(learningLeadersEntityList != null){
            return learningLeadersEntityList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.onBindViewHolder(learningLeadersEntityList.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, hours, country;
        public final ImageView badgeUrl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.leanerName);
            hours = itemView.findViewById(R.id.learningHour);
            country = itemView.findViewById(R.id.learnerCountry);
            badgeUrl = itemView.findViewById(R.id.learningLeadersImage);
        }

        void onBindViewHolder(final LearningLeadersEntity learningLeadersEntityList) {
            Log.e("bind", "onBindViewHolder: " + learningLeadersEntityList);

            name.setText(learningLeadersEntityList.getName());
            hours.setText(learningLeadersEntityList.getHours());
            country.setText(learningLeadersEntityList.getCountry());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(learningLeadersEntityList.getBadgeUrl())
                    .error(R.drawable.ic_launcher_background)
                    .into(badgeUrl);
        }
    }
}
