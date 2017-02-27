package edu.udemy.android.whatsapp_clone.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.config.FirebaseConfig;
import edu.udemy.android.whatsapp_clone.helper.Permission;
import edu.udemy.android.whatsapp_clone.helper.Preferences;
import edu.udemy.android.whatsapp_clone.model.User;

import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_loginEmail;
    private EditText edt_loginPassword;

    private Button btn_loginLogin;

    private FirebaseAuth firebaseAuthentication;
    private User user;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        createComponents();

        btn_loginLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                validateUser();
            }
        } );
    }


    /**
     * Validate the login of the user. If the user exits, there is successful
     *   in login. If not, the user won't can do the login.
     */
    private void validateUser() {
        setUser();

        firebaseAuthentication = FirebaseConfig.getFirebaseAuthentication();
        firebaseAuthentication.signInWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( @NonNull Task<AuthResult> task ) {
                if( task.isSuccessful() ) {
                    Toast.makeText( LoginActivity.this, "Success in Login", Toast.LENGTH_SHORT )
                            .show();
                } else {
                    Toast.makeText( LoginActivity.this, "User didn't exist", Toast.LENGTH_SHORT )
                            .show();
                }
            }
        } );
    }

    /**
     * Configure the user with the collected data from the user.
     */
    private void setUser() {
        user = new User();
        user.setEmail( edt_loginEmail.getText().toString() );
        user.setPassword( edt_loginPassword.getText().toString() );
    }

    /**
     * Initiate the Activity of Register User.
     *
     * @param view
     */
    public void startRegister( View view ) {
        Intent startRegisterActivity = new Intent( LoginActivity.this, RegisterUserActivity.class );
        startActivity( startRegisterActivity );
    }


    /**
     * Create references to components of user interface.
     */
    private void createComponents() {
        edt_loginEmail = (EditText) findViewById( R.id.edt_loginEmail );
        edt_loginPassword = (EditText) findViewById( R.id.edt_loginPassword );

        btn_loginLogin = (Button) findViewById( R.id.btn_loginLogin );
    }
}
