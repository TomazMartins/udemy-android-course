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

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.helper.Permission;
import edu.udemy.android.whatsapp_clone.helper.Preferences;

import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_loginEmail;
    private EditText edt_loginPassword;

    private Button btn_loginLogin;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        createComponents();
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
