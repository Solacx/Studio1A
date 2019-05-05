package com.example.sengstudio1a.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sengstudio1a.R;

public class RegisterSuccessController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);
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

}
