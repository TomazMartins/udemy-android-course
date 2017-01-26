package edu.udemy.android.dataactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends Activity {

    private TextView txv_passed_data;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        txv_passed_data = (TextView) findViewById( R.id.txv_passed_data );

        Bundle extra = getIntent().getExtras();

        if( extra != null ) {
            String passed_text = extra.getString( "nome" );

            txv_passed_data.setText( passed_text );
        }
    }
}
