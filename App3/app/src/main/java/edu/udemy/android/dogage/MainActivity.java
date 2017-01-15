package edu.udemy.android.dogage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button btn_calculate;
    private TextView txv_result;
    private EditText txf_dog_age;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_calculate = (Button) findViewById( R.id.btn_calculate );
        txv_result = (TextView) findViewById( R.id.txv_result );
        txf_dog_age = (EditText) findViewById( R.id.txf_dog_age );

        btn_calculate.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View view ) {
                String dogAge = txf_dog_age.getText().toString();
                String textResult = "";

                if( dogAge.isEmpty() ) {
                    textResult = "Do you enter some dog age?";
                } else {
                    int valueDogAge = Integer.parseInt( dogAge );
                    int result = valueDogAge * 7;

                    textResult = "Result: " + result;
                }

                txv_result.setText( textResult );
            }
        } );
    }
}
