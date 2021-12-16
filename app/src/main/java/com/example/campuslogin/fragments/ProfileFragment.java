package com.example.campuslogin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.campuslogin.LoginActivity;
import com.example.campuslogin.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    FirebaseAuth mAuth;
    private Button btnLogout;
    ImageView imageView;
    String name;
    private EditText setEmail,etEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_profile, container, false);

        //Switch buttonNotify = findViewById(R.id.notify);
/*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"TakeOn")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("TakeOn")
                .setContentText("You can now get any notification from TakeOn")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        buttonNotify.setOnClickListener(view -> {
            notificationManager.notify(100,builder.build());

        });

 */
    }
    /*
    private  void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "studentChannel";
            String description = "channel for student notification";
            int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("TakeOn", name, importance);
            channel.setDescription(description);

            NotificationManager notficationManager = getSystemService(NotificationManager.class);
            notficationManager.createNotificationChannel(channel);
        }
    }

     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();

       // String email =
       // setEmail = view.findViewById(R.id.editEmail);
       // setEmail.setText(email);




        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(ProfileFragment.this.getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });



        
    }

    //public void btnSettings_onClick(View view) {
    //    Intent intent = new Intent(ProfileFragment.this.getActivity(), SettingsActivity.class);
    //    startActivity(intent);
    //}

}
