import android.content.Intent;


/**
End the current session.

We should be fine to just head back to the login activity, might have
overlooked something though. */
public void logout() {
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
}

/**
Not sure what the purpose of this one is, or how this one works in
general. */
// public ... search(...) {}

/**
Should probably be part of its own module, e.g. gps.java, which houses
all the relevant methods.

Thinking that this does one of two things:
    1. Takes latitude and longitude and returns the name of the
       location, e.g. Sydney
    2. Returns the physical address of a user (should be part of the
       User class then) */
public void locate() {}
