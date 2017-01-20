package edu.udemy.android.dataactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btn_pass_data;

    @Override
    protected void onCreate( final Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_pass_data = (Button) findViewById( R.id.btn_data_pass );

        btn_pass_data.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startSecondActivity = new Intent( MainActivity.this, SecondActivity.class );

                startSecondActivity.putExtra( "nome", "TomazMartins" );

                startActivity( startSecondActivity );
            }
        } );
    }
}
