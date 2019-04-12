import android.widget.EditText;

import model.user.User;


/**
Attempt to register a new user.

Check that each field has been properly completed and display the
appropriate error messages if not. Otherwise, create a new User and add
them to the database.

Note that the stored password must be encrypted.

TODO:
    - Display the appropriate error messages
    - Add the new User to the database 
    - Password encryption */
public void register() {
    if (
            verifyUsername() && verifyPassword() && verifyPhone()
            && verifyEmail() && verifyAddress()
        ) {
        User user = new User(
            ((EditText) findViewByID(R.id.editUsername)).getText().toString(),
            /** Password needs to be encrypted */
            ((EditText) findViewByID(R.id.editPassword)).getText().toString(),
            ((EditText) findViewByID(R.id.editPhone)).getText().toString(),
            ((EditText) findViewByID(R.id.editEmail)).getText().toString(),
            ((EditText) findViewByID(R.id.editAddress)).getText().toString()
        );
        // Add the new User to the database
    } else {
        // Display the appropriate error messages
    }
}

/**
Check if the username field has been properly completed.

Usernames must contain at least one character and cannot begin with a
space. Usernames must be unique.

NB: Usernames must be unique because I have assumed that the login
    process uses their username and not their email. Whichever one is
    used to login must be a unique identifier.
    
TODO:
    - Check the database to see if that username has been taken */
public boolean verifyUsername() {
    EditText usernameField = (EditText) findViewByID(R.id.editUsername);
    String username = usernameField.getText().toString();
    /**
    Also need to check the database to see if the username has been
    taken. */
    return username.length() > 0 && username[i] != " ";
}

/**
Check if the password field has been properly completed.

Passwords must be between 6-24 characters long and contain at least one
uppercase and lowercase character, one number and one symbol.

TODO:
    - Character, number and symbol check */
public boolean verifyPassword() {
    EditText passwordField = (EditText) findViewByID(R.id.editPassword);
    return (
        passwordField.getText().toString().contains(...)
        && passwordField.getText().toString().length() >= 6
        && passwordField.getText().toString().length() <= 24
    );
}

/**
Check if the phone field has been properly completed.

Phone numbers must be 10 digits long (assuming that we're taking mobile
phone numbers only). */
public boolean verifyPhone() {
    String phone = ((EditText) findViewByID(R.id.editPhone)).getText().toString();
    boolean isNumeric = true;
    for (int i = 0; i < phone.length(); i++) {
        if (!phone[i].isNumeric()) {
            isNumeric = false;
        }
    }
    return phone.length() == 10 && isNumeric;
}

/**
Check if the email field has been properly completed.

Emails must ...

NB: I don't know of any way to really verify an email other than by
    sending an email. Most companies just send the email. */
public boolean verifyEmail() {
    return true;
}

/**
Check if the address field has been properly completed.

Addresses must ...

NB: Not sure how we're approaching this. */
public boolean verifyAddress() {
    return true;
}
