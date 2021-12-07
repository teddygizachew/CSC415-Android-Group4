package com.example.campuslogin;

import org.parceler.Parcel;

@Parcel

public class Job {
    private String jobName;
    private String jobDescription;
    private String jobPayment;
    private String jobDueDate;
    private String jobLocation;
    private String jobType;
    private String jobPostDate;


    public Job() {

    }

    public Job(String jobName, String jobDescription, String jobPayment, String jobDueDate,
               String jobLocation, String jobType, String jobPostDate) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobPayment = jobPayment;
        this.jobDueDate = jobDueDate;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobPostDate = jobPostDate;

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobPayment() {
        return jobPayment;
    }

    public void setJobPayment(String jobPayment) {
        this.jobPayment = jobPayment;
    }

    public String getJobDueDate() {
        return jobDueDate;
    }

    public void setJobDueDate(String jobDueDate) {
        this.jobDueDate = jobDueDate;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobPostDate() {
        return jobPostDate;
    }

    public void setJobPostDate(String jobPostDate) {
        this.jobPostDate = jobPostDate;
    }
}
