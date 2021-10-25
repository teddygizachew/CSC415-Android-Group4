package com.nkufall2021.jobspage;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<Job> jobs = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private JobListAdapter mJobListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobs.addLast(new Job(1, "Student Union Food Delivery", "Northern Kentucky University",
                "University Housing - Norse Hall", "Part Time", "Sun Oct 24, 2021",
                "Etiam rutrum suscipit arcu vel interdum. Cras at nisl non velit vehicula bibendum. Ut consequat ullamcorper ex, nec tincidunt nulla semper nec. Praesent et congue lacus. Phasellus eu cursus quam. Nunc eleifend eleifend turpis a convallis. Fusce eu ultrices augue. Pellentesque tortor diam, gravida sed venenatis ac, blandit ut mauris. In risus est, interdum at porttitor a, elementum a urna. Nulla venenatis purus nisi, sed iaculis ex venenatis ac. Sed ipsum eros, varius mattis purus a, convallis bibendum purus. Donec a neque sed libero suscipit porta at sit amet lorem. Pellentesque molestie auctor semper. Nullam commodo velit eget augue maximus mollis. In tristique felis in nisl facilisis, vehicula porta diam aliquet.\n" +
                        "\n" +
                        "Duis porttitor aliquam leo at fringilla. Duis pretium sed arcu nec faucibus. Duis vel semper nunc. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sollicitudin ex id mi laoreet, ac viverra ante elementum. Fusce porta libero sit amet diam maximus tincidunt. Vestibulum sodales lectus nec nunc dignissim interdum id id erat. Morbi pellentesque, eros ac scelerisque pellentesque, erat diam porta nisi, nec laoreet mi arcu sed nisi. Curabitur sagittis magna vel tortor commodo, egestas vulputate diam mollis. Ut porta commodo diam, at hendrerit lectus scelerisque aliquet. Curabitur eget velit elementum, rutrum diam eget, ultricies nulla. Maecenas condimentum mi mauris, et vulputate arcu mattis sed."));
        jobs.addLast(new Job(2, "Lukas Administration Delivery", "Northern Kentucky University",
                "Student Union Plaza - 2nd Floor", "Full Time","Sun Oct 24, 2021",
                "Etiam rutrum suscipit arcu vel interdum. Cras at nisl non velit vehicula bibendum. Ut consequat ullamcorper ex, nec tincidunt nulla semper nec. Praesent et congue lacus. Phasellus eu cursus quam. Nunc eleifend eleifend turpis a convallis. Fusce eu ultrices augue. Pellentesque tortor diam, gravida sed venenatis ac, blandit ut mauris. In risus est, interdum at porttitor a, elementum a urna. Nulla venenatis purus nisi, sed iaculis ex venenatis ac. Sed ipsum eros, varius mattis purus a, convallis bibendum purus. Donec a neque sed libero suscipit porta at sit amet lorem. Pellentesque molestie auctor semper. Nullam commodo velit eget augue maximus mollis. In tristique felis in nisl facilisis, vehicula porta diam aliquet.\n" +
                        "\n" +
                        "Duis porttitor aliquam leo at fringilla. Duis pretium sed arcu nec faucibus. Duis vel semper nunc. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sollicitudin ex id mi laoreet, ac viverra ante elementum. Fusce porta libero sit amet diam maximus tincidunt. Vestibulum sodales lectus nec nunc dignissim interdum id id erat. Morbi pellentesque, eros ac scelerisque pellentesque, erat diam porta nisi, nec laoreet mi arcu sed nisi. Curabitur sagittis magna vel tortor commodo, egestas vulputate diam mollis. Ut porta commodo diam, at hendrerit lectus scelerisque aliquet. Curabitur eget velit elementum, rutrum diam eget, ultricies nulla. Maecenas condimentum mi mauris, et vulputate arcu mattis sed."));
        jobs.addLast(new Job(3, "University Center Delivery", "Northern Kentucky University",
                "University Center - 4th Floor", "Internship", "Sun Oct 24, 2021",
                "Etiam rutrum suscipit arcu vel interdum. Cras at nisl non velit vehicula bibendum. Ut consequat ullamcorper ex, nec tincidunt nulla semper nec. Praesent et congue lacus. Phasellus eu cursus quam. Nunc eleifend eleifend turpis a convallis. Fusce eu ultrices augue. Pellentesque tortor diam, gravida sed venenatis ac, blandit ut mauris. In risus est, interdum at porttitor a, elementum a urna. Nulla venenatis purus nisi, sed iaculis ex venenatis ac. Sed ipsum eros, varius mattis purus a, convallis bibendum purus. Donec a neque sed libero suscipit porta at sit amet lorem. Pellentesque molestie auctor semper. Nullam commodo velit eget augue maximus mollis. In tristique felis in nisl facilisis, vehicula porta diam aliquet.\n" +
                        "\n" +
                        "Duis porttitor aliquam leo at fringilla. Duis pretium sed arcu nec faucibus. Duis vel semper nunc. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sollicitudin ex id mi laoreet, ac viverra ante elementum. Fusce porta libero sit amet diam maximus tincidunt. Vestibulum sodales lectus nec nunc dignissim interdum id id erat. Morbi pellentesque, eros ac scelerisque pellentesque, erat diam porta nisi, nec laoreet mi arcu sed nisi. Curabitur sagittis magna vel tortor commodo, egestas vulputate diam mollis. Ut porta commodo diam, at hendrerit lectus scelerisque aliquet. Curabitur eget velit elementum, rutrum diam eget, ultricies nulla. Maecenas condimentum mi mauris, et vulputate arcu mattis sed."));
        jobs.addLast(new Job(4, "Health Innovation Center Delivery", "Northern Kentucky University",
                "Founders Hall - 2nd Floor", "Research", "Sun Oct 24, 2021",
                "Etiam rutrum suscipit arcu vel interdum. Cras at nisl non velit vehicula bibendum. Ut consequat ullamcorper ex, nec tincidunt nulla semper nec. Praesent et congue lacus. Phasellus eu cursus quam. Nunc eleifend eleifend turpis a convallis. Fusce eu ultrices augue. Pellentesque tortor diam, gravida sed venenatis ac, blandit ut mauris. In risus est, interdum at porttitor a, elementum a urna. Nulla venenatis purus nisi, sed iaculis ex venenatis ac. Sed ipsum eros, varius mattis purus a, convallis bibendum purus. Donec a neque sed libero suscipit porta at sit amet lorem. Pellentesque molestie auctor semper. Nullam commodo velit eget augue maximus mollis. In tristique felis in nisl facilisis, vehicula porta diam aliquet.\n" +
                        "\n" +
                        "Duis porttitor aliquam leo at fringilla. Duis pretium sed arcu nec faucibus. Duis vel semper nunc. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sollicitudin ex id mi laoreet, ac viverra ante elementum. Fusce porta libero sit amet diam maximus tincidunt. Vestibulum sodales lectus nec nunc dignissim interdum id id erat. Morbi pellentesque, eros ac scelerisque pellentesque, erat diam porta nisi, nec laoreet mi arcu sed nisi. Curabitur sagittis magna vel tortor commodo, egestas vulputate diam mollis. Ut porta commodo diam, at hendrerit lectus scelerisque aliquet. Curabitur eget velit elementum, rutrum diam eget, ultricies nulla. Maecenas condimentum mi mauris, et vulputate arcu mattis sed."));

        setAdapter();
    }

    private void setAdapter() {
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.jobListRecyclerView);
        // Create an adapter and supply the data to be displayed.
        mJobListAdapter = new JobListAdapter(this, jobs);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mJobListAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}