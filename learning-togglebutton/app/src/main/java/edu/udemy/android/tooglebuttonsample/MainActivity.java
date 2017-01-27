package edu.udemy.android.tooglebuttonsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btt_sample;
    private TextView txv_result;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        txv_result = (TextView) findViewById( R.id.txv_result );
        btt_sample = (ToggleButton) findViewById( R.id.btt_sample );

        btt_sample.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                txv_result.setText( "" );

                if( isChecked ) {
                    txv_result.setText( "Mostrar..." );
                }
            }
        } );
    }
}
