package edu.udemy.android.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    /*
     * In this step we use the method 'getInstance()' to recover
     *   the instance of database of the Firebase.
     *
     *   After that, we recover a reference of the root of the
     *   database NoSQL, in Firebase, with method 'getReference()'.
     *
     *   Because this, we must use the class 'DatabaseReference'.
    * */
    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        /*
        * In here, we create, as from the root, another node
        *   with method 'child()'.
        *
        *   This node calls 'points', and your value is '100'.
        *
        *   When the app is executed, the database in the Firebase
        *   will be updated with this data.
        * */
        firebaseReference.child( "points" ).setValue( "100" );
    }
}
