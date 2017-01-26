package edu.udemy.android.alertdialogsample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final Boolean NOT_CANCELABLE = false;

    private Button btn_alert;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_alert = (Button) findViewById( R.id.btn_alert );

        btn_alert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                dialog = new AlertDialog.Builder( MainActivity.this );

                dialog.setIcon( android.R.drawable.ic_delete );
                dialog.setTitle( "Alert Dialog" );

                dialog.setMessage( "Hi! I'm a Alert Message!" );
                dialog.setCancelable( NOT_CANCELABLE );

                dialog.setNegativeButton( "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick( DialogInterface dialog, int which ) {
                                Toast.makeText( getApplicationContext(),
                                        "Button NO Clicked", Toast.LENGTH_SHORT ).show();
                            }
                        } );

                dialog.setPositiveButton( "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick( DialogInterface dialog, int which ) {
                                Toast.makeText( getApplicationContext(),
                                        "Button YES Clicked", Toast.LENGTH_SHORT ).show();
                            }
                        } );

                AlertDialog mainDialog = dialog.create();
                mainDialog.show();
            }
        } );
    }
}
