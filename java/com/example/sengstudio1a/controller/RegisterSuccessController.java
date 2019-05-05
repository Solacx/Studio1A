package com.example.sengstudio1a.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.sengstudio1a.R;
import com.example.sengstudio1a.lib.Constant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterSuccessController extends AppCompatActivity {

    private static final String TAG = "REG_SUCCESS_CONTROLLER";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);

        mAuth = FirebaseAuth.getInstance();
        createUser(getIntent().getStringExtra(Constant.EMAIL),
                   getIntent().getStringExtra(Constant.PASSWORD));
    }

    /**
     * Create a new Firebase user.
     *
     * Create a new Firebase user then login the user to setup the
     * email verification process.
     *
     * @param email    email address
     * @param password password
     */
    private void createUser(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, String.format((
                                    "Successfully created a new user.\n"
                                    + "Email: %s\n"
                                    + "Password: %s\n"
                                    ), email, password
                            ));
                            login(email, password);
                        } else {
                            Log.w(TAG, "Failed to create user.", task.getException());
                        }
                    }
                });
    }

    /**
     * Login to Firebase.
     *
     * Login to Firebase then send the user a verification email.
     *
     * @param email    email address
     * @param password password
     */
    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Login succeeded.");
                            sendVerificationEmail();
                        } else {
                            Log.w(TAG, "Login failed.", task.getException());
                        }
                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Verification email sent.");
                        } else {
                            Log.w(TAG, "Failed to send verification email.", task.getException());
                        }
                    }
                });
    }

    /**
     * Return to the login activity.
     *
     * @param v the view that received the onClick event
     */
    public void returnOnClick(View v) {
        Intent intent = new Intent(this, LoginController.class);
        startActivity(intent);
    }

    /**
     * Send a verification email. Wraps sendVerificationEmail().
     *
     * @param v the view that received the onClick event
     */
    public void emailOnClick(View v) {
        sendVerificationEmail();
    }

}
