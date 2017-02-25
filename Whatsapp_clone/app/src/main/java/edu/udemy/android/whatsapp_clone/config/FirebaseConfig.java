package edu.udemy.android.whatsapp_clone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfig {
    private static DatabaseReference firebaseReference;
    private static FirebaseAuth firebaseAuthentication;

    public static DatabaseReference getFirebase() {
        if( firebaseReference == null ) {
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return firebaseReference;
    }

    public static FirebaseAuth getFirebaseAuthentication() {
        if( firebaseAuthentication == null ) {
            firebaseAuthentication = FirebaseAuth.getInstance();
        }

        return firebaseAuthentication;
    }
}
