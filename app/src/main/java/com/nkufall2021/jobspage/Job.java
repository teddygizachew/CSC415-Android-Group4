package com.nkufall2021.jobspage;

import org.parceler.Parcel;

@Parcel
public class Job {

    int jobId;
    String job_title;
    String job_description;
    String job_location;
    String job_type;
    String job_post_date;
    String job_info;

    public Job() {

    }

    public Job(int jobId, String job_title, String job_description, String job_location,
               String job_type, String job_post_date, String job_info) {
        this.jobId = jobId;
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_location = job_location;
        this.job_type = job_type;
        this.job_post_date = job_post_date;
        this.job_info = job_info;
    }

    public String getTitle() {
        return job_title;
    }

    public int getJobId() {
        return jobId;
    }

    public String getDescription() {
        return job_description;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getJob_type() {
        return job_type;
    }

    public String getJob_post_date() {
        return job_post_date;
    }

    public String getJob_info() {
        return job_info;
    }

}
