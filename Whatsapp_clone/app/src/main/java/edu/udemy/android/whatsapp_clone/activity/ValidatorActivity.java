package edu.udemy.android.whatsapp_clone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import edu.udemy.android.whatsapp_clone.R;

public class ValidatorActivity extends AppCompatActivity {
    private EditText edt_validatorNumber;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_validator );

        createComponents();
        setMaskFormatter();
    }


    private void createComponents() {
        edt_validatorNumber = (EditText) findViewById( R.id.edt_validatorNumber );
    }

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
