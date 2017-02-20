package edu.udemy.android.whatsapp_clone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import edu.udemy.android.whatsapp_clone.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_loginPhone;
    private EditText edt_loginCod;
    private EditText edt_loginDDD;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        createComponents();
        setMaskFormatter();

    }


    private void createComponents() {
        edt_loginPhone = (EditText) findViewById( R.id.edt_loginPhone );
        edt_loginCod = (EditText) findViewById( R.id.edt_loginCod );
        edt_loginDDD = (EditText) findViewById( R.id.edt_loginDDD );
    }

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
