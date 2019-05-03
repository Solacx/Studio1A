package com.example.sengstudio1a.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sengstudio1a.Placeholder;
import com.example.sengstudio1a.R;
import com.example.sengstudio1a.lib.Hash;
import com.example.sengstudio1a.lib.observable.BooleanObserver;
import com.example.sengstudio1a.lib.observable.ObservableBoolean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Observable;

public class LoginController extends AppCompatActivity {

    private static final String TAG = "LOGIN_CONTROLLER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /**
         * Visibility attribute doesn't seem to update properly so I
         * force the change here. */
        TextView tvLoginHint = findViewById(R.id.tvLoginHint);
        tvLoginHint.setVisibility(View.INVISIBLE);
    }

    /**
     * Make a login attempt.
     *
     * Search the database for a matching set of credentials and
     * display the appropriate hints/error messages. Advance to the
     * next activity if a match is found.
     *
     * @param v the view that received the onClick event
     */
    public void loginOnClick(View v) {
        final EditText inputEmail = findViewById(R.id.inputEmail);
        final EditText inputPassword = findViewById(R.id.inputPassword);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ObservableBoolean isMatchFound = new ObservableBoolean(false);
                            isMatchFound.addObserver(new BooleanObserver() {
                                @Override
                                public void update(Observable o, Object arg) {
                                    if ((Boolean) arg) {
                                        Intent intent = new Intent(getApplicationContext(), Placeholder.class);
                                        startActivity(intent);
                                    } else {
                                        // Show login hint
                                        TextView tvLoginHint = findViewById(R.id.tvLoginHint);
                                        tvLoginHint.setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                            int count = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (
                                        document.get("email").equals(inputEmail.getText().toString())
                                        && document.get("password").equals(new Hash().hash(
                                                inputPassword.getText().toString()))
                                    ) {
                                    isMatchFound.setValue(true);
                                }

                                if (++count == task.getResult().size()) isMatchFound.setValue(false);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    /**
     * Go to the registration screen.
     *
     * @param v the view that received the onClick event
     */
    public void noAccountOnClick(View v) {
        Intent intent = new Intent(this, RegisterDonorController.class);
        startActivity(intent);
    }

}
