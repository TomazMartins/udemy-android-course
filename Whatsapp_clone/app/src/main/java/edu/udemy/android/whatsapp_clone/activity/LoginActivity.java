package edu.udemy.android.whatsapp_clone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.config.FirebaseConfig;
import edu.udemy.android.whatsapp_clone.model.User;

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

        verifyLoggedUser();

        btn_loginLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                validateUser();
            }
        } );
    }


    private void verifyLoggedUser() {
        firebaseAuthentication = FirebaseConfig.getFirebaseAuthentication();

        if( firebaseAuthentication.getCurrentUser() != null ) {
            startMainActivity();
        }
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
                    startMainActivity();

                    Toast.makeText( LoginActivity.this, "Success in Login", Toast.LENGTH_SHORT )
                            .show();
                } else {
                    String exceptionMessage = "";

                    try {
                        throw task.getException();
                    } catch( FirebaseAuthInvalidCredentialsException faice ) {
                        exceptionMessage = "E-mail or password invalid";
                    } catch( Exception e ) {
                        exceptionMessage = "Error while login";
                    }

                    Toast.makeText( LoginActivity.this, exceptionMessage, Toast.LENGTH_SHORT )
                            .show();
                }
            }
        } );
    }

    private void startMainActivity() {
        Intent startMainActivity = new Intent( LoginActivity.this, MainActivity.class );
        startActivity( startMainActivity );

        finish();
    }

    /**
     * Configure the user with the collected data from the user.
     */
    private void setUser() {
        user = new User();

        String email = edt_loginEmail.getText().toString();
        String password = edt_loginPassword.getText().toString();

        /*
        * TODO: Find a solution for the problem of IllegalArgumentException.
        *   If the Strings 'email' and 'password' are equals an empty
        *   or  null, the method signInWithEmailAndPassword(), used in
        *   method 'validateUser()', throw a exception:
        *
        *   IllegalArgumentException.
        *
        *   For a temporary solution, I fill this variables with a default
        *   value, indicate that there is a fail.
        *
        *   We must find a solution more elegant.
        * */
        if( email.isEmpty() || password.isEmpty() ) {
            email = "FAIL";
            password = "FAIL";
        }

        user.setEmail( email );
        user.setPassword( password );
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
