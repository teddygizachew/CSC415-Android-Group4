package com.example.campuslogin;

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

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder>{
    Context context;
    private List<Job> jobs;

    public JobsAdapter(Context context, List<Job> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.bind(job);
        holder.jobName.setText(job.getJobName());
        holder.jobDescription.setText(job.getJobDescription());
        holder.jobPayment.setText(job.getJobPayment());
        holder.jobDueDate.setText(job.getJobDueDate());
        holder.jobLocation.setText(job.getJobLocation());
        holder.jobDueDate.setText(job.getJobDueDate());
        holder.jobPostDate.setText(job.getJobPostDate());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        jobs.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Job> jobList) {
        jobs.addAll(jobList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView jobName, jobDescription, jobPayment, jobDueDate, jobLocation, jobType, jobPostDate;
        LinearLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.job_title);
            jobDescription = itemView.findViewById(R.id.job_description);
            jobPayment = itemView.findViewById(R.id.job_pay);
            jobDueDate = itemView.findViewById(R.id.job_due_date);
            jobLocation = itemView.findViewById(R.id.job_location);
            jobType = itemView.findViewById(R.id.job_type);
            jobPostDate = itemView.findViewById(R.id.job_post_date);

            container = itemView.findViewById(R.id.container);
        }

        public void bind(Job jobs) {
            // Bind the post data to the view elements
            jobName.setText(jobs.getJobName());
            jobDescription.setText(jobs.getJobDescription());
            jobPayment.setText(jobs.getJobPayment());
            jobDueDate.setText(jobs.getJobDueDate());
            jobLocation.setText(jobs.getJobLocation());
            jobType.setText(jobs.getJobType());
            jobPostDate.setText(jobs.getJobPostDate());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, JobActivity.class);
                    intent.putExtra("job", Parcels.wrap(jobs));
                    context.startActivity(intent);
                }
            });
        }
    }
}
