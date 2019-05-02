package com.example.sengstudio1a;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sengstudio1a.lib.Hash;
import com.example.sengstudio1a.lib.Validator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Testing: RegisterCharityController.java

    private static final String TAG = "REGISTER_CHARITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_charity);

        /**
         * Visibility attribute doesn't seem to update properly so I
         * force the change here.
         */
        TextView tvOrganisationError = findViewById(R.id.tvOrganisationError);
        TextView tvPasswordHint = findViewById(R.id.tvPasswordHint);

        tvOrganisationError.setVisibility(View.INVISIBLE);
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
    public void createCharityAccount(View v) {
        EditText inputOrganisation = findViewById(R.id.inputOrganisation);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPhone = findViewById(R.id.inputPhone);
        EditText inputPassword = findViewById(R.id.inputPassword);

        Validator validator = new Validator();
        boolean isValidOrganisation = validator.isNameValid(inputOrganisation);
        boolean isValidEmail = validator.isEmailValid(inputEmail);
        boolean isValidPhone = validator.isPhoneValid(inputPhone);
        boolean isValidPassword = validator.isPasswordValid(inputPassword);

        if (
                isValidOrganisation && isValidEmail
                && isValidPhone && isValidPassword
            ) {
            Map<String, String> donor = new HashMap<>();
            donor.put("organisation", inputOrganisation.getText().toString());
            donor.put("email", inputEmail.getText().toString());
            donor.put("phone", inputPhone.getText().toString());
            donor.put("password", new Hash().hash(
                    inputPassword.getText().toString()));

            // Add to the database
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("charities")
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
            TextView tvOrganisationError = findViewById(R.id.tvOrganisationError);

            // Reset visibility
            tvOrganisationError.setVisibility(View.INVISIBLE);
            tvPasswordHint.setVisibility(View.INVISIBLE);

            // Display the appropriate hints/error messages
            if (!isValidOrganisation) tvOrganisationError.setVisibility(View.VISIBLE);
            if (!isValidPassword) tvPasswordHint.setVisibility(View.VISIBLE);
        }
    }

}
