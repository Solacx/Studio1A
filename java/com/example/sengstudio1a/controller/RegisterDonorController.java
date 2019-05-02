package com.example.sengstudio1a.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sengstudio1a.R;
import com.example.sengstudio1a.lib.Hash;
import com.example.sengstudio1a.lib.Validator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterDonorController extends AppCompatActivity {

    private static final String TAG = "REGISTER_DONOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_donor);

        /**
         * Visibility attribute doesn't seem to update properly so I
         * force the change here. */
        TextView tvFirstNameError = findViewById(R.id.tvFirstNameError);
        TextView tvPasswordHint = findViewById(R.id.tvPasswordHint);

        tvFirstNameError.setVisibility(View.INVISIBLE);
        tvPasswordHint.setVisibility(View.INVISIBLE);
    }

    /**
     * Create a new account.
     *
     * Check the completion of each required field and display the
     * appropriate hints/error messages. If no errors are found, create
     * a new account and add it to the database.
     *
     * @param v the view that received the onClick event
     */
    public void createDonorAccount(View v) {
        EditText inputFirstName = findViewById(R.id.inputFirstName);
        EditText inputLastName = findViewById(R.id.inputLastName);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPhone = findViewById(R.id.inputPhone);
        EditText inputPassword = findViewById(R.id.inputPassword);

        Validator validator = new Validator();
        boolean isValidFirstName = validator.isNameValid(inputFirstName);
        boolean isValidLastName = validator.isNameValid(inputLastName);
        boolean isValidEmail = validator.isEmailValid(inputEmail);
        boolean isValidPhone = validator.isPhoneValid(inputPhone);
        boolean isValidPassword = validator.isPasswordValid(inputPassword);

        if (
                isValidFirstName && isValidLastName && isValidEmail
                && isValidPhone && isValidPassword
            ) {
            Map<String, String> donor = new HashMap<>();
            donor.put("firstName", inputFirstName.getText().toString());
            donor.put("lastName", inputLastName.getText().toString());
            donor.put("email", inputEmail.getText().toString());
            donor.put("phone", inputPhone.getText().toString());
            donor.put("password", new Hash().hash(
                    inputPassword.getText().toString()));

            // Add to the database
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("donors")
                    .add(donor)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        } else {
            TextView tvPasswordHint = findViewById(R.id.tvPasswordHint);
            TextView tvFirstNameError = findViewById(R.id.tvFirstNameError);

            // Reset visibility
            tvFirstNameError.setVisibility(View.INVISIBLE);
            tvPasswordHint.setVisibility(View.INVISIBLE);

            // Display the appropriate hints/error messages
            if (!isValidFirstName) tvFirstNameError.setVisibility(View.VISIBLE);
            if (!isValidPassword) tvPasswordHint.setVisibility(View.VISIBLE);
        }
    }
}
