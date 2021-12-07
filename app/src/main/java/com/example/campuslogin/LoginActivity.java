package com.example.campuslogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // UI variables
    private EditText etEmail, etPassword;
    private Button btnLogin;
    ProgressDialog progressDialog;

    // Firebase variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize UI variables
        etEmail = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        progressDialog = new ProgressDialog(this);

        // initialize Firebase variables
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        etEmail.addTextChangedListener(loginTextWatcher);
        etPassword.addTextChangedListener(loginTextWatcher);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameInput = etEmail.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            btnLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    // when user clicks "Create Account" TextView
    public void RegisterUser(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }


    // when user clicks "Login" button
    public void LoginToAccount(View view) {
        if(validateInputs()){
            updateProgress();

            String email = getTextAsString(etEmail);
            String password = getTextAsString(etPassword);
            mAuth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,
                                        "Login Successful", Toast.LENGTH_SHORT).show();
                                sendUserToDestinationActivity();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Oops...\n" + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    // validate all user EditText inputs
    private boolean validateInputs() {
        if(!isValidEmailAddress() || !isValidPassword()){
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
            etPassword.setError("Password must be between 6 and 16 characters");
            etPassword.requestFocus();
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


    // get text from EditText and return as a String
    private String getTextAsString(EditText input) {
        return input.getText().toString().trim();
    }


    // update user that their account is being created
    private void updateProgress(){
        progressDialog.setMessage("Signing into account...");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    // send user to specified activity (after successful creation of new account)
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // THIS METHOD NEEDS TO BE ALTERED TO SEND USER TO WHICHEVER ACTIVITY THEY SHOULD BE SENT TO!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void sendUserToDestinationActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}