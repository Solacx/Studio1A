package com.example.sengstudio1a.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sengstudio1a.R;

public class RegisterSelectController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_select);
    }

    /**
     * Go to the donor registration screen.
     *
     * @param v the view that received the onClick event
     */
    public void donorOnClick(View v) {
        Intent intent = new Intent(this, RegisterDonorController.class);
        startActivity(intent);
    }

    /**
     * Go to the charity registration screen.
     *
     * @param v the view that received the onClick event
     */
    public void charityOnClick(View v) {
        Intent intent = new Intent(this, RegisterCharityController.class);
        startActivity(intent);
    }

    /**
     * Go to the login screen.
     *
     * @param v the view that received the onClick event
     */
    public void alreadyMemberOnClick(View v) {
        Intent intent = new Intent(this, LoginController.class);
        startActivity(intent);
    }

}
