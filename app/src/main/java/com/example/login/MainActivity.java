package com.example.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    // UI variables
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ImageView btnFacebook, btnGithub, btnGoogle, btnPhone, btnTwitter;
    ProgressDialog progressDialog;

    // Firebase variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize UI variables
        etEmail = findViewById(R.id.email_input);
        etPassword = findViewById(R.id.password_input);
        btnLogin = findViewById(R.id.login_button);
        btnFacebook = findViewById(R.id.login_facebook);
        btnGithub = findViewById(R.id.login_github);
        btnGoogle = findViewById(R.id.login_google);
        btnPhone = findViewById(R.id.login_phone);
        btnTwitter = findViewById(R.id.login_twitter);
        progressDialog = new ProgressDialog(this);

        // initialize Firebase variables
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }


    // when user clicks "Create Account" TextView
    public void RegisterUser(View view) {
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
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
                        Toast.makeText(MainActivity.this,
                                "Login Successful", Toast.LENGTH_SHORT).show();
                        sendUserToDestinationActivity();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Oops... " + task.getException(), Toast.LENGTH_SHORT).show();
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


    // update user that their account is being created
    private void updateProgress(){
        progressDialog.setMessage("Signing into account...");
        progressDialog.setTitle("Login");
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
        Intent intent = new Intent(MainActivity.this, DestinationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void LoginWithGoogle(View view) {
        progressDialog.setMessage("Signing in With Google...");
        progressDialog.show();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "ERROR: " +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        // Facebook
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "ERROR: " +
                                    task.getException(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(MainActivity.this, DestinationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void LoginWithFacebook(View view) {
        progressDialog.setMessage("Signing in With Facebook...");
        progressDialog.show();
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions( this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error: " +
                                            task.getException(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}