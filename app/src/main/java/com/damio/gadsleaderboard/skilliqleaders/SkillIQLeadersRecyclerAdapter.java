package com.damio.gadsleaderboard.skilliqleaders;

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

public class SkillIQLeadersRecyclerAdapter extends RecyclerView.Adapter<SkillIQLeadersRecyclerAdapter.ViewHolder> {

    private List<SkillIQLeadersEntity> skillIQLeadersEntityList;
    Context context;

    public SkillIQLeadersRecyclerAdapter(List<SkillIQLeadersEntity> skillIQLeadersEntityList, Context context) {
        this.skillIQLeadersEntityList = skillIQLeadersEntityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skilliqleaders_recyclerview_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(skillIQLeadersEntityList != null){
            return skillIQLeadersEntityList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.onBindViewHolder(skillIQLeadersEntityList.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, score, country;
        public final ImageView badgeUrl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.leanerName);
            score = itemView.findViewById(R.id.skillIQScore);
            country = itemView.findViewById(R.id.skillIQCountry);
            badgeUrl = itemView.findViewById(R.id.skillIQImage);
        }

        void onBindViewHolder(final SkillIQLeadersEntity skillIQLeadersEntityList) {
            Log.e("bind", "onBindViewHolder: " + skillIQLeadersEntityList);

            name.setText(skillIQLeadersEntityList.getName());
            score.setText(skillIQLeadersEntityList.getScore());
            country.setText(skillIQLeadersEntityList.getCountry());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(skillIQLeadersEntityList.getBadgeUrl())
                    .error(R.drawable.ic_launcher_background)
                    .into(badgeUrl);
        }
    }
}
