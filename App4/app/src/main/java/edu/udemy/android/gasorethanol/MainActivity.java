package edu.udemy.android.gasorethanol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_verify;
    private TextView txv_result;
    private EditText edt_ethanol;
    private EditText edt_gas;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_verify = (Button) findViewById( R.id.btn_verify );
        txv_result = (TextView) findViewById( R.id.txv_result );
        edt_ethanol = (EditText) findViewById( R.id.edt_ethanol );
        edt_gas = (EditText) findViewById( R.id.edt_gas );

        btn_verify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String edtGas = edt_gas.getText().toString();
                String edtEthanol = edt_ethanol.getText().toString();

                double gasPrize = Double.parseDouble( edtGas );
                double ethanolPrize = Double.parseDouble( edtEthanol );

                double result = calculate( gasPrize, ethanolPrize );

                verify_result( result );
            }
        } );
    }

    private double calculate( double gasPrize, double ethanolPrize ) {
        double result = 0;

        if( gasPrize <= 0 || ethanolPrize <= 0 ) {
            result = 0;
        } else {
            result = ethanolPrize/gasPrize;
        }

        return result;
    }

    private void verify_result( double result ) {
        if( result >= 0.7 ) {
            txv_result.setText( "Use Gas" );
        } else {
            txv_result.setText( "Use Ethanol" );
        }
    }
}
