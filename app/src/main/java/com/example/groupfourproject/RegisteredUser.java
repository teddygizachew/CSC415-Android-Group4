package com.example.groupfourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class RegisteredUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, createAccount;
    private EditText etFirstName, etLastName, etEmail, etPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user);

        // initialize the FireBaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        createAccount = (TextView) findViewById(R.id.createAccount_button);
        createAccount.setOnClickListener(this);

        etFirstName = (EditText) findViewById(R.id.firstName_input);
        etLastName = (EditText) findViewById(R.id.lastName_input);
        etEmail = (EditText) findViewById(R.id.email_input_user);
        etPassword = (EditText) findViewById(R.id.password_input_user);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_user);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.createAccount_button:
                createAccount();
                break;
        }
    }


    private void createAccount() {

    }
}