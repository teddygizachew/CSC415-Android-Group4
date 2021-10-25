package com.nkufall2021.jobspage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;


import java.util.LinkedList;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    Context context;
    private final LinkedList<Job> jobs;
    private final LayoutInflater layoutInflater;

    public JobListAdapter(Context context,
                          LinkedList<Job> wordList) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.jobs = wordList;
    }

    @NonNull
    @Override
    public JobListAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.joblist_item,
                parent, false);
        return new JobViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListAdapter.JobViewHolder holder, int position) {
//        String mCurrent = jobList.get(position);
//        holder.jobItemView.setText(mCurrent);
        Job job = jobs.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder{
        LinearLayout container;
        TextView title;
        TextView description;
        TextView location;
        TextView type;
        TextView post_date;
        TextView info;

        public JobViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            description = itemView.findViewById(R.id.job_description);
            location = itemView.findViewById(R.id.job_location);
            type = itemView.findViewById(R.id.job_type);
            post_date = itemView.findViewById(R.id.job_post_date);
            info = itemView.findViewById(R.id.job_info);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Job job) {
            title.setText(job.getTitle());
            description.setText(job.getDescription());
            location.setText(job.getJob_location());
            type.setText(job.getJob_type());
            post_date.setText(job.getJob_post_date());
//            info.setText(job.getJob_info());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, JobActivity.class);
                    intent.putExtra("job", Parcels.wrap(job));
                    context.startActivity(intent);
                }
            });
        }
    }

}
