package edu.udemy.android.whatsapp_clone.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.config.FirebaseConfig;
import edu.udemy.android.whatsapp_clone.model.User;

public class RegisterUserActivity extends AppCompatActivity {
    private EditText edt_registerName;
    private EditText edt_registerEmail;
    private EditText edt_registerPassword;

    private Button btn_registerRegister;

    private User user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_user );

        createComponents();

        btn_registerRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                registerUser();
            }
        } );
    }

    /**
     * Register in the database a new user.
     */
    private void registerUser() {
        createUser();

        firebaseAuth = FirebaseConfig.getFirebaseAuthentication();

        firebaseAuth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener( RegisterUserActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( @NonNull Task<AuthResult> task ) {
                if( task.isSuccessful() ) {
                    Toast.makeText(
                            RegisterUserActivity.this, "User registered", Toast.LENGTH_SHORT )
                            .show();

                    FirebaseUser firebaseUser = task.getResult().getUser();
                    user.setId( firebaseUser.getUid() );
                    user.save();
                } else {
                    Toast.makeText(
                            RegisterUserActivity.this, "User not registered", Toast.LENGTH_SHORT )
                            .show();
                }
            }
        } );
    }

    /**
     * Create, from the editable data, a new user.
     */
    private void createUser() {
        user = new User();
        user.setName( edt_registerName.getText().toString() );
        user.setEmail( edt_registerEmail.getText().toString() );
        user.setPassword( edt_registerPassword.getText().toString() );
    }

    /**
     * Create references to components of user interface.
     */
    private void createComponents() {
        edt_registerName = (EditText) findViewById( R.id.edt_registerName );
        edt_registerEmail = (EditText) findViewById( R.id.edt_registerEmail );
        edt_registerPassword = (EditText) findViewById( R.id.edt_registerPassword );

        btn_registerRegister = (Button) findViewById( R.id.btn_registerRegister );
    }
}
