package edu.udemy.android.radiobuttonsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_select;
    private TextView txv_result;

    private RadioGroup rdg_options;
    private RadioButton btr_selected;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        rdg_options = (RadioGroup) findViewById( R.id.rdg_options );
        btn_select = (Button) findViewById( R.id.btn_select );
        txv_result = (TextView) findViewById( R.id.txv_result );

        btn_select.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                int idChosenOption = rdg_options.getCheckedRadioButtonId();

                if( idChosenOption > 0 ) {
                    btr_selected = (RadioButton) findViewById( idChosenOption );
                    txv_result.setText( btr_selected.getText() );
                }
            }
        } );
    }
}
