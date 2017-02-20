package edu.udemy.android.whatsapp_clone.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import edu.udemy.android.whatsapp_clone.R;
import android.widget.Button;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_loginName;
    private EditText edt_loginPhone;
    private EditText edt_loginCod;
    private EditText edt_loginDDD;

    private Button btn_loginRegister;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        createComponents();
        setMaskFormatter();

        btn_loginRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                String userName = edt_loginName.getText().toString();
                String fullCellphone = getFullCellphone();

                String token = generateToken();
            }
        } );
    }


    /**
     * Generate a token, with fout digits, between 1000 and 9999.
     *    This token never will be null.
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
     * Create references to components of user interface.
     */
    private void createComponents() {
        edt_loginName = (EditText) findViewById( R.id.edt_loginName );
        edt_loginPhone = (EditText) findViewById( R.id.edt_loginPhone );
        edt_loginCod = (EditText) findViewById( R.id.edt_loginCod );
        edt_loginDDD = (EditText) findViewById( R.id.edt_loginDDD );

        btn_loginRegister = (Button) findViewById( R.id.btn_loginRegister );
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
}
