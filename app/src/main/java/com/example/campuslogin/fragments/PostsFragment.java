package com.example.campuslogin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campuslogin.Job;
import com.example.campuslogin.JobsAdapter;
import com.example.campuslogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvJobs;
    protected JobsAdapter jobsAdapter;
    SearchView searchView;
    DatabaseReference databaseReference;
    SwipeRefreshLayout swipeContainer;
    ArrayList<Job> list;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeContainer = view.findViewById(R.id.swipeContainer);
        searchView = view.findViewById(R.id.searchView);

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data");
                queryPosts();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            Job job = dataSnapshot.getValue((Job.class));
                            list.add(job);
                        }
                        jobsAdapter.notifyDataSetChanged();
                        // Now we call setRefreshing(false) to signal refresh has finished
                        swipeContainer.setRefreshing(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        rvJobs = view.findViewById(R.id.rvJobs);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Job");
        rvJobs.setHasFixedSize(true);
        rvJobs.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        jobsAdapter = new JobsAdapter(getContext(), list);
        rvJobs.setAdapter(jobsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Job job = dataSnapshot.getValue((Job.class));
                    list.add(job);
                }
                jobsAdapter.notifyDataSetChanged();
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                populateSearch(snapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        };

//        databaseReference.addListenerForSingleValueEvent(eventListener);

//        // Steps to use the recycler view:
//        // 0. Create layout for one row in the list
//        // 1. create the adapter
//        // 2. create the data source
////        // 3. set the adapter on the recycler view
//        rvJobs.setAdapter(jobsAdapter);
//        // 4. set the layout manager on the recycler view
//        rvJobs.setLayoutManager(new LinearLayoutManager(getContext()));
//        queryPosts();

    }

    @Override
    public void onStart() {
        super.onStart();

        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot da:snapshot.getChildren()) {
                            Job job = da.getValue((Job.class));
                            list.add(job);
                        }

                        jobsAdapter = new JobsAdapter(getContext(), list);
                        rvJobs.setAdapter(jobsAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<Job> myList = new ArrayList<>();
        for (Job object : list) {
            if (object.getJobDescription().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }
        }
        jobsAdapter = new JobsAdapter(getContext(), myList);
        rvJobs.setAdapter(jobsAdapter);
    }

    //    private void populateSearch(DataSnapshot snapshot) {
//        Log.d(TAG, "populateSearch: Reading Data");
//        ArrayList<Job> list = new ArrayList<>();
//        if(snapshot.exists()) {
//            for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//                Job job = dataSnapshot.getValue((Job.class));
//                list.add(job);
//
//                jobsAdapter.clear();
//                jobsAdapter.addAll(list);
//                jobsAdapter.notifyDataSetChanged();
//                // Now we call setRefreshing(false) to signal refresh has finished
//                swipeContainer.setRefreshing(false);
//
//                txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Job job = txtSearch.getText().toString();
//                        searchUser(job);
//                    }
//                });
//            }
//        }
//    }

//    private void searchUser(Job job) {
//        Query query = databaseReference.orderByChild("job").equalTo(job);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {;
//                    ArrayList<Job> list = new ArrayList<>();
//                    for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//                        Job job = dataSnapshot.getValue((Job.class));
//                        list.add(job);
//                    }
//                }
//                else {
//                    Log.d(TAG, "onDataChange: No data found");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void queryPosts() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Job job = dataSnapshot.getValue((Job.class));
                    list.add(job);
                }
                jobsAdapter.clear();
                jobsAdapter.addAll(list);
                jobsAdapter.notifyDataSetChanged();
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}