package edu.udemy.android.whatsapp_clone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.config.FirebaseConfig;

public class MainActivity extends AppCompatActivity {
    private Button btn_mainExit;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        createComponents();

        btn_mainExit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                firebaseAuth = FirebaseConfig.getFirebaseAuthentication();
                firebaseAuth.signOut();

                Intent startLoginActivity = new Intent( MainActivity.this, LoginActivity.class );
                startActivity( startLoginActivity );
            }
        } );
    }

    private void createComponents() {
        btn_mainExit = (Button) findViewById( R.id.btn_mainExit );
    }
}
