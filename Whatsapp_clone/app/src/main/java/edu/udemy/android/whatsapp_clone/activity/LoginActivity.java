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
    private EditText edt_loginName;
    private EditText edt_loginPhone;
    private EditText edt_loginCod;
    private EditText edt_loginDDD;

    private Button btn_loginRegister;

    private String[] permissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        createComponents();
        setMaskFormatter();

        Permission.validatePermission( this, permissions, 1 );

        btn_loginRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sendToken();
            }
        } );
    }


    /*
     * If some permission is denied we must handle with this.
     *   For this, we use this method.
     * */
    public void onRequestPermissionResult( int requestCode, String[] allPermissions, int[] grantResult ) {
        super.onRequestPermissionsResult( requestCode, allPermissions, grantResult );

        for( int result : grantResult ) {
            if( result == PackageManager.PERMISSION_DENIED ) {
                alertPermissionValidation();
            }
        }
    }

    /**
     * Creates a Alert dialog  explaining to the user the
     *   consequences of not allowing the use of any
     *   resource.
     */
    private void alertPermissionValidation() {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder( LoginActivity.this );

        builderDialog.setTitle( "Denied Permissions" );
        builderDialog.setMessage( "For use this app, it necessary to accept the permissions" );

        builderDialog.setPositiveButton( "CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                finish();
            }
        } );

        AlertDialog alertDialog = builderDialog.create();
        alertDialog.show();
    }

    /**
     * Send the token to validation session with the user.
     */
    private void sendToken() {
        String userName = edt_loginName.getText().toString();
        String fullCellphone = getFullCellphone();

        String token = generateToken();

        Preferences preferences = new Preferences( LoginActivity.this );
        preferences.saveUserData( userName, fullCellphone, token );

        // To recover data of user do this.
        //   HashMap<String, String> userData = preferences.getUserData();

        String message = "Whatsapp confirmation code: " + token;

        boolean is_sent = sendSMS( "+" + fullCellphone, message );

        startValidatorActivity( is_sent );
    }

    /**
     * Start, from the LoginActivity, the Validator Activity.
     *   For this, there is a verification if the SMS was sent.
     *   In afirmative case, the ValidatorActivity is started.
     *   In negative case, no.
     *
     * @param is_sent a boolean indicate if the SMS was sent or not.
     */
    private void startValidatorActivity( boolean is_sent ) {
        if( is_sent ) {
            Intent startValidatorActivity =
                    new Intent( LoginActivity.this, ValidatorActivity.class );

            // To start a new Activity from this
            startActivity( startValidatorActivity );

            // To finish the current Activity
            finish();
        } else {
            Toast.makeText( LoginActivity.this, "Problem to send a SMS", Toast.LENGTH_SHORT )
                    .show();
        }
    }

    /**
     * Send a SMS to the cellphone of the user with the token.
     *
     * @return a confirmation. If the success of the send, it's true. False means fail.
     */
    private boolean sendSMS( String cellphone, String message ) {
        final boolean SUCCESS = true;
        final boolean FAIL = false;

        boolean is_sent;

        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage( cellphone, null, message, null, null );

            is_sent = SUCCESS;
        } catch( Exception e ) {
            e.printStackTrace();
            is_sent = FAIL;
        }

        return is_sent;
    }

    /**
     * Generate a token, with four digits, between 1000 and 9999.
     * This token never will be null.
     *
     * @return token with four digits
     */
    @NonNull
    private String generateToken() {
        Random random = new Random();
        int token = random.nextInt( 9999 - 1000 ) + 1000;

        return String.valueOf( token );
    }

    /**
     * Return the fullcellphone without formatter.
     *
     * @return is the cellphone without formatters.
     */
    private String getFullCellphone() {
        String cellphone = edt_loginCod.getText().toString() +
                edt_loginDDD.getText().toString() +
                edt_loginPhone.getText().toString();

        String fullCellphone = cellphone.replace( "+", "" );
        fullCellphone = fullCellphone.replace( "-", "" );

        return fullCellphone;
    }

    /**
     * Configure the editable fields of user interface with masks.
     */
    private void setMaskFormatter() {
        /*
        * In here we add a mask for field of phone.
        *   For this, we use the lib 'MaskFormatter'. To add this dependency
        *   follow the instructions in here - https://github.com/rtoshiro/MaskFormatter.
        * */
        SimpleMaskFormatter maskPhone = new SimpleMaskFormatter( "NNNNN-NNNN" );
        MaskTextWatcher maskWatcherPhone = new MaskTextWatcher( edt_loginPhone, maskPhone );
        edt_loginPhone.addTextChangedListener( maskWatcherPhone );

        SimpleMaskFormatter maskCod = new SimpleMaskFormatter( "+NN" );
        MaskTextWatcher maskWatcherCod = new MaskTextWatcher( edt_loginCod, maskCod );
        edt_loginCod.addTextChangedListener( maskWatcherCod );

        SimpleMaskFormatter maskDDD = new SimpleMaskFormatter( "NN" );
        MaskTextWatcher maskWatcherDDD = new MaskTextWatcher( edt_loginDDD, maskDDD );
        edt_loginDDD.addTextChangedListener( maskWatcherDDD );
    }

    /**
     * Create references to components of user interface.
     */
    private void createComponents() {
        edt_loginName = (EditText) findViewById( R.id.edt_loginName );
        edt_loginPhone = (EditText) findViewById( R.id.edt_loginPhone );
        edt_loginCod = (EditText) findViewById( R.id.edt_loginCod );
        edt_loginDDD = (EditText) findViewById( R.id.edt_loginDDD );

        btn_loginRegister = (Button) findViewById( R.id.btn_loginRegister );
    }
}
