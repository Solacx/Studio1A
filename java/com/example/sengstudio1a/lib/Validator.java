package com.example.sengstudio1a.lib;

import android.widget.EditText;

public class Validator {

    public Validator() {}

    /**
     * Validate that a field contains a proper name.
     *
     * A name must be at least one character long, contain no digits
     * and cannot begin with a space.
     *
     * @param field the field to be validated
     * @return      true if the first name meets the requirements; false
     *              otherwise
     */
    public boolean isNameValid(EditText field) {
        String name = field.getText().toString();
        return (
                name.length() > 0 && name.matches("[^0-9]+")
                        && !Character.isWhitespace(name.charAt(0))
        );
    }

    /**
     * Validate that a field contains a proper email address.
     *
     * An email address cannot have a space and must include the @
     * symbol.
     *
     * @param field the field to be validated
     * @return      true if the email address meets the requirements;
     *              false otherwise
     */
    public boolean isEmailValid(EditText field) {
        String email = field.getText().toString();
        return (!email.matches("[ ]+") && email.matches(".*[@].*"));
    }

    // -------------
    // DOES NOT WORK
    // -------------
//    /**
//     * Check that the email is not already in use.
//     *
//     * Collect a list of the emails of the registered users (donors and
//     * charities), then compare the list to the email being checked
//     * for.
//     *
//     * @param email the email to check for
//     * @return      true if the email address is not already in use;
//     *              false otherwise
//     */
//    public boolean isEmailRegistered(String email) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference donorsCollectionRef = db.collection("donors");
//        CollectionReference charitiesCollectionRef = db.collection("charities");
//        final List<String> registeredEmails = new ArrayList<>();
//
//         // Search donors
//        Query donorsQuery = donorsCollectionRef
//                .whereEqualTo("email", email);
//        donorsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    System.out.println("1");
//                    for (QueryDocumentSnapshot doc : task.getResult()) {
//                        registeredEmails.add(("" + doc.get("email")));
//                    }
//                    System.out.println("2");
//                } else {
//                    Log.d(TAG, "Error getting records.", task.getException());
//                }
//            }
//        });
//        // Search charities
//
//        System.out.println("3");
//
//        return registeredEmails.size();
//    }

    /**
     * Validate that a field contains a proper phone number.
     *
     * A phone number must be exactly 10 digits long (mobile phone
     * numbers only).
     *
     * @param field the field to be validated
     * @return      true if the email address meets the requirements;
     *              false otherwise
     */
    public boolean isPhoneValid(EditText field) {
        String phone = field.getText().toString();
        return (
                phone.isEmpty()
                || (phone.length() == 10 && phone.matches("[0-9]+"))
        );
    }

    /**
     * Validate that a field contains a proper password.
     *
     * A password must be between 6-24 characters long and contain at
     * least one lowercase and uppercase character, one number and one
     * special character.
     *
     * @param field the field to be validated
     * @return      true if the email address meets the requirements;
     *              false otherwise
     */
    public boolean isPasswordValid(EditText field) {
        return (
                field.getText().toString().length() >= 6
                        && field.getText().toString().length() <= 24
                        && field.getText().toString().matches(".*[a-z].*")
                        && field.getText().toString().matches(".*[A-Z].*")
                        && field.getText().toString().matches(".*[0-9].*")
                        && field.getText().toString().matches(".*[^a-zA-Z0-9 ].*")
        );
    }
}
