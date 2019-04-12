import android.content.Intent;


/**
Make a login attempt.

Search the database for an entry with the same username and password as
that supplied in the view. Display the appropriate error messages if no
matching entry is found. Otherwise, advance to the next activity.

TODO:
    - Search the database for a matching set of login details
    - Password encryption
    - Display the appropriate error messages */
public void login() {
    EditText usernameField = (EditText) findViewByID(R.id.editUsername);
    EditText passwordField = (EditText) findViewByID(R.id.editPassword);
    String username = usernameField.getText().toString();
    /** Password needs to be encrypted */
    String password = passwordField.getText().toString();

    /**
    Search the database for a matching set of login details. Assuming
    that the database is returned as a list of lists with
    comma-separated values, we could do something like this.
    
    String[] db = getDatabase() // db = [[username, ...], [...]]
    for (record : db) {
        if (record[0] == username && record[1] == password) {
            // Launch the next activity
        }
    }
    
    We can just use a specific index instead of assigning each record
    to a key-value pair (in something like a dictionary) because we
    know the specified format. */
    if (
            // Able to find a matching set of login details
        ) {
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("package.prefix.USERNAME", username);
        startActivity(intent);
    } else {
        // Display the appropriate error messages
    }
}
