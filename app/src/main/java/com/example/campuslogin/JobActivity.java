package com.example.campuslogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

public class JobActivity extends AppCompatActivity {

    TextView jobName, jobDescription, jobPayment, jobDueDate, jobLocation, jobType, jobPostDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        jobName = findViewById(R.id.job_title);
        jobDescription = findViewById(R.id.job_description);
        jobPayment = findViewById(R.id.job_pay);
        jobDueDate = findViewById(R.id.job_due_date);
        jobLocation = findViewById(R.id.job_location);
        jobType = findViewById(R.id.job_type);
        jobPostDate = findViewById(R.id.job_post_date);

        Job job = (Job) Parcels.unwrap(getIntent().getParcelableExtra("job"));

        jobName.setText(job.getJobName());
        jobDescription.setText(job.getJobDescription());
        jobPayment.setText(job.getJobPayment());
        jobDueDate.setText(job.getJobDueDate());
        jobLocation.setText(job.getJobLocation());
        jobType.setText(job.getJobType());
        jobPostDate.setText(job.getJobPostDate());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}