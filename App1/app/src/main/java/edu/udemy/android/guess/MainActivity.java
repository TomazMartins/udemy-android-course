package edu.udemy.android.guess;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button btn_play;
    private TextView txv_result;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_play = (Button) findViewById( R.id.btn_play );
        txv_result = (TextView) findViewById( R.id.txv_result );

        btn_play.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                txv_result.setText( "Result: changed text" );
            }
        });
    }
}
