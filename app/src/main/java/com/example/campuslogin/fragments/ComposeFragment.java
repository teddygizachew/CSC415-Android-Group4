package com.example.campuslogin.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campuslogin.DAOJob;
import com.example.campuslogin.Job;
import com.example.campuslogin.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ComposeFragment extends Fragment {

    private EditText jobName, jobDescription, jobPayment, jobDueDate, jobLocation, jobType, jobPostDate;
    private Button btnPost;

    public ComposeFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jobName = view.findViewById(R.id.jobNameText);
        jobDescription = view.findViewById(R.id.jobDescriptionText);
        jobPayment = view.findViewById(R.id.jobPaymentText);
        jobDueDate = view.findViewById(R.id.jobDueDateText);
        jobLocation = view.findViewById(R.id.jobLocationText);
        jobType = view.findViewById(R.id.jobTypeText);
        jobPostDate = view.findViewById(R.id.jobPostDateText);
        btnPost = view.findViewById(R.id.btnSubmit);

        DAOJob dao = new DAOJob();

        btnPost.setOnClickListener(v -> {
            Job job = new Job(
                    jobName.getText().toString(),
                    jobDescription.getText().toString(),
                    jobPayment.getText().toString(),
                    jobDueDate.getText().toString(),
                    jobLocation.getText().toString(),
                    jobType.getText().toString(),
                    jobPostDate.getText().toString()
            );
            if (
                    jobName.getText().toString().equals("") ||
                    jobDescription.getText().toString().equals("") ||
                    jobPayment.getText().toString().equals("") ||
                            jobDueDate.getText().toString().equals("") ||
                            jobDueDate.getText().toString().equals("") ||
                            jobType.getText().toString().equals("") ||
                            jobPostDate.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            dao.add(job).addOnSuccessListener(suc -> {
                Toast.makeText(getContext(), "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(err -> {
                Toast.makeText(getContext(), ""+err.getMessage(), Toast.LENGTH_SHORT).show();
            });

            // Update dao
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("jobName", jobName.getText().toString());
//            hashMap.put("jobDescription", jobDescription.getText().toString());
//            hashMap.put("jobPayment", jobPayment.getText( ).toString());
//            hashMap.put("jobDueDate", jobDueDate.getText().toString());
//
//            dao.update("-MqJ0pLS1Cgl3yViM2EY", hashMap).addOnSuccessListener(suc -> {
//                Toast.makeText(getContext(), "Record is updated", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(err -> {
//                Toast.makeText(getContext(), ""+err.getMessage(), Toast.LENGTH_SHORT).show();
//            });

            // Remove
//            dao.remove("-MqJ0pLS1Cgl3yViM2EY").addOnSuccessListener(suc -> {
//                Toast.makeText(getContext(), "Record is updated", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(err -> {
//                Toast.makeText(getContext(), ""+err.getMessage(), Toast.LENGTH_SHORT).show();
//            });

        });
    }
}