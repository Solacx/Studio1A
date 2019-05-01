package com.example.sengstudio1a.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.sengstudio1a.R;
import com.example.sengstudio1a.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class RegisterController extends AppCompatActivity {

    public static HashMap<String, String> database;  // Dummy database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dummy);  // TODO: Link to the correct view

        database = new HashMap<String, String>();
        database.put("Jun", "0b020f03");
        database.put("John", "0902");
    }

    /**
     * Register a new user.
     *
     * Check that each field has been properly completed, displaying
     * the appropriate error messages if not. Otherwise, create a new
     * User and add them to the database.
     *
     * TODO:
     *  - Display the appropriate error messages
     *  - Add the new User to the database
     */
    public void registerOnClick(View v) {
        boolean isValidUsername = verifyUsername();
        boolean isValidPassword = verifyPassword();
        boolean isValidPhone = verifyPhone();

        if (isValidUsername && isValidPassword && isValidPhone) {
            EditText editUsername = findViewById(R.id.editUsername);
            EditText editPassword = findViewById(R.id.editPassword);
            EditText editPhone = findViewById(R.id.editPhone);
            EditText editEmail = findViewById(R.id.editEmail);
            EditText editAddress = findViewById(R.id.editAddress);

            User user = new User(
                    editUsername.getText().toString(),
                    hash(editPassword.getText().toString()),
                    editPhone.getText().toString(),
                    editEmail.getText().toString(),
                    editAddress.getText().toString()
            );
            // Add the new User to the database
        }

        // Display the appropriate error messages
    }

    /**
     * Verify the username field.
     *
     * A username must contain at least one character and cannot begin
     * with a whitespace character.
     *
     * TODO:
     *  - Access the database
     *
     * @return True if valid; false otherwise
     */
    private boolean verifyUsername() {
        EditText editUsername = findViewById(R.id.editUsername);
        String username = editUsername.getText().toString();
        return (
                database.get(username) == null && username.length() > 0
                && !Character.isWhitespace(username.charAt(0))
        );
    }

    /**
     * Verify the password field.
     *
     * A password must be between 6-24 characters long and contain at
     * least one lowercase and uppercase character, one number and one
     * special character.
     *
     * @return True if valid; false otherwise
     */
    private boolean verifyPassword() {
        EditText editPassword = findViewById(R.id.editPassword);
        return (
                editPassword.getText().toString().length() >= 6
                && editPassword.getText().toString().length() <= 24
                && editPassword.getText().toString().matches(".*[a-z].*")
                && editPassword.getText().toString().matches(".*[A-Z].*")
                && editPassword.getText().toString().matches(".*[0-9].*")
                && editPassword.getText().toString().matches(".*[^a-zA-Z0-9 ].*")
        );
    }

    /**
     * Verify the phone field.
     *
     * A phone number be exactly 10 digits long (mobile phone numbers
     * only).
     *
     * @return True if valid; false otherwise.
     */
    private boolean verifyPhone() {
        EditText editPhone = findViewById(R.id.editPhone);
        return (
                editPhone.getText().toString().length() == 10
                && editPhone.getText().toString().matches("[0-9]+")
        );
    }

    // REMOVED - public boolean verifyEmail()

    // REMOVED - public boolean verifyAddress()


    // -----------------
    // Stolen from LoginController.java

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
