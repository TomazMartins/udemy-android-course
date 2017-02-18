package edu.udemy.android.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.Button;

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

    /*
    * In here, we create, as from the root, another node
    *   with method 'child()'.
    *
    *   This node calls 'points', and your value is '100'.
    *
    *   When the app is executed, the database in the Firebase
    *   will be updated with this data.
    * */
    private DatabaseReference usersReference = firebaseReference.child( "users" );


    private EditText edt_firstName;
    private EditText edt_lastName;
    private EditText edt_age;
    private EditText edt_sex;

    private TextView txv_show;

    private Button btn_save;
    private Button btn_show;

    private User currentUser;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        create_components();

        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                currentUser = createUser();
            }
        } );
    }


    private User createUser() {
        User user = new User();

        user.setFirstName( edt_firstName.getText().toString() );
        user.setLastName( edt_lastName.getText().toString() );
        user.setAge( Integer.parseInt( edt_age.getText().toString() ) );
        user.setSex( edt_sex.getText().toString() );

        return user;
    }

    private void create_components() {
        edt_firstName = (EditText) findViewById( R.id.edt_firstName );
        edt_lastName = (EditText) findViewById( R.id.edt_lastName );
        edt_age = (EditText) findViewById( R.id.edt_age );
        edt_sex = (EditText) findViewById( R.id.edt_sex );

        txv_show = (TextView) findViewById( R.id.txv_show );

        btn_save = (Button) findViewById( R.id.btn_save );
        btn_show= (Button) findViewById( R.id.btn_show );
    }
}
