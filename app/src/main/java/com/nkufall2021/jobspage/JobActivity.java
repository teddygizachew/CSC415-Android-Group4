package com.nkufall2021.jobspage;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.parceler.Parcels;

public class JobActivity extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView location;
    TextView type;
    TextView post_date;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Log.d(TAG, "onCreate: created");

        title = findViewById(R.id.job_title);
        description = findViewById(R.id.job_description);
        location = findViewById(R.id.job_location);
        type = findViewById(R.id.job_type);
        post_date = findViewById(R.id.job_post_date);
        info = findViewById(R.id.job_info);

        Job job = (Job) Parcels.unwrap(getIntent().getParcelableExtra("job"));

        title.setText(job.getTitle());
        description.setText(job.getDescription());
        location.setText(job.getJob_location());
        type.setText(job.getJob_type());
        post_date.setText(job.getJob_post_date());
        info.setText(job.getJob_info());
    }
}