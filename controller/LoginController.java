package com.example.sengstudio1a.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sengstudio1a.R;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginController extends AppCompatActivity {

    public static HashMap<String, String> database;  // Dummy database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // TODO: Link to the correct view

        database = new HashMap<String, String>();
        database.put("Jun", "0b020f03");
        database.put("John", "0902");
    }

    /**
     * Make a login attempt.
     *
     * Search the database for an entry with the same username and password as
     * that supplied in the view. Display the appropriate error messages if no
     * matching entry is found. Otherwise, advance to the next activity.
     *
     * TODO:
     *  - Access the database
     *  - Launch the next activity
     *  - Display the appropriate error messages
     */
    public void loginOnClick(View v) {
        EditText editUsername = findViewById(R.id.editUsername);
        EditText editPassword = findViewById(R.id.editPassword);

        if (
                database.get(editUsername.getText().toString()) != null
                && database.get(editUsername.getText().toString()).equals(
                        hash(editPassword.getText().toString()))
        ) {
            // Launch the next activity
        } else {
            // Display the appropriate error messages
        }
    }

    /**
     * Return the hashed version of the given String.
     *
     * Hash the given String using SHA-256 and return its hexadecimal
     * representation. Return null when the hashing operation is not
     * found.
     *
     * @param s String to be hashed
     * @return  Hexadecimal representation of the hashed String
     */
    private String hash(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    s.getBytes(StandardCharsets.UTF_8));  // Requires Android 4.4 (KitKat)
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            // Log: NoSuchAlgorithmException when looking for SHA-256
            //      under MessageDigest object.
        }
        return null;
    }

    /**
     * Return the hexadecimal representation of the given byte array.
     *
     * @param bytes Byte array to be converted to hexadecimal
     * @return      Hexadecimal representation of the converted byte array
     */
    private String bytesToHex(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
