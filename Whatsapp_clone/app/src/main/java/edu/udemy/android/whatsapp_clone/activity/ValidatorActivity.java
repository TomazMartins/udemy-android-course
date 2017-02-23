package edu.udemy.android.whatsapp_clone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import edu.udemy.android.whatsapp_clone.R;
import edu.udemy.android.whatsapp_clone.helper.Preferences;

import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class ValidatorActivity extends AppCompatActivity {
    private Button btn_validatorValidate;
    private EditText edt_validatorNumber;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_validator );

        createComponents();
        setMaskFormatter();

        btn_validatorValidate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                verifyToken();
            }
        } );
    }


    /**
     * Verify if the typed token is equals to generated token.
     */
    private void verifyToken() {
        Preferences preferences = new Preferences( ValidatorActivity.this );

        HashMap<String, String> userData = preferences.getUserData();

        String generatedToken = userData.get( Preferences.KEY_TOKEN );
        String typedToken = edt_validatorNumber.getText().toString();

        if( generatedToken.equals( typedToken ) ) {
            Toast.makeText( ValidatorActivity.this, "Token Validated", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( ValidatorActivity.this, "Token no Validated", Toast.LENGTH_SHORT )
                    .show();
        }
    }

    /**
     * Create references to components of user interface.
     */
    private void createComponents() {
        edt_validatorNumber = (EditText) findViewById( R.id.edt_validatorNumber );
        btn_validatorValidate = (Button) findViewById( R.id.btn_validatorValidate );
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
        SimpleMaskFormatter maskValidator = new SimpleMaskFormatter( "NNNN" );
        MaskTextWatcher maskWatcherValidator = new MaskTextWatcher( edt_validatorNumber, maskValidator );
        edt_validatorNumber.addTextChangedListener( maskWatcherValidator );
    }
}
