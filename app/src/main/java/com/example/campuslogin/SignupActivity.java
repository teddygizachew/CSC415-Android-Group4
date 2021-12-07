package com.example.campuslogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    // UI variables
    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnCreateAccount;
    private TextView tvSignin;
    ProgressDialog progressDialog;

    // Firebase variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // initialize UI variables
        etEmail = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        btnCreateAccount = findViewById(R.id.buttonLogin);
        progressDialog = new ProgressDialog(this);

        tvSignin = findViewById(R.id.tvSignin);

        // initialize Firebase variables
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity();
            }
        });
    }


    // create new user account
    public void CreateUserAccount(View view) {
        if(validateInputs()){
            updateProgress();

            String email = getTextAsString(etEmail);
            String password = getTextAsString(etPassword);
            mAuth.createUserWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this,
                                        "New Account Created Successfully",
                                        Toast.LENGTH_SHORT).show();
                                sendUserToDestinationActivity();
                            } else {
                                Toast.makeText(SignupActivity.this,
                                        "Oops... " + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    // validate all user EditText inputs
    private boolean validateInputs() {
        if(!isValidEmailAddress() || !isValidPassword() || !matchingPasswords()){
            return false;
        } else {
            return true;
        }
    }


    // validate email address
    private boolean isValidEmailAddress() {
        if (isEmptyInput(etEmail)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            etEmail.setError("Enter a valid email address");
            etEmail.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    // validate password
    private boolean isValidPassword() {
        if (isEmptyInput(etPassword)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        } else if (!isValidPasswordLength(etPassword)) {
            etPassword.setError("Password must be between 6 and 16 characters long");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    // validate confirmed password
    private boolean matchingPasswords() {
        if (isEmptyInput(etConfirmPassword)) {
            etConfirmPassword.setError("Password is required");
            etConfirmPassword.requestFocus();
            return false;
        } else if (!isValidPasswordLength(etConfirmPassword)) {
            etConfirmPassword.setError("Password must be between 6 and 16 characters");
            etConfirmPassword.requestFocus();
            return false;
        } else if(!etConfirmPassword.getText().toString().trim().
                equals(etPassword.getText().toString().trim())) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    // check if EditText is empty
    private boolean isEmptyInput(EditText input) {
        return TextUtils.isEmpty(input.getText().toString().trim());
    }


    // check password input length is 6 - 16 characters
    private boolean isValidPasswordLength(EditText input) {
        return (input.getText().toString().trim().length() >= 6 &&
                input.getText().toString().trim().length() <= 16);
    }


    // update user that their account is being created
    private void updateProgress(){
        progressDialog.setMessage("Creating new account...");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    // get text from EditText and return as a String
    private String getTextAsString(EditText input) {
        return input.getText().toString().trim();
    }



    // send user to specified activity (after successful creation of new account)
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // THIS METHOD NEEDS TO BE ALTERED TO SEND USER TO WHICHEVER ACTIVITY THEY SHOULD BE SENT TO!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void sendUserToDestinationActivity() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}