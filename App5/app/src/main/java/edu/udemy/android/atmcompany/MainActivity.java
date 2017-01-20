package edu.udemy.android.atmcompany;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView btn_about;
    private ImageView btn_services;
    private ImageView btn_clients;
    private ImageView btn_contact;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_about = (ImageView) findViewById( R.id.btn_about );

        btn_about.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startAboutActivity = new Intent( MainActivity.this, AboutActivity.class );
                startActivity( startAboutActivity );
            }
        } );

        btn_services = (ImageView) findViewById( R.id.btn_services );

        btn_services.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startServicesActivity = new Intent( MainActivity.this, ServiceActivity.class );
                startActivity( startServicesActivity );
            }
        } );

        btn_contact = (ImageView) findViewById( R.id.btn_contact );

        btn_contact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startContactActivity = new Intent( MainActivity.this, ContactActivity.class );
                startActivity( startContactActivity );
            }
        } );

        btn_clients = (ImageView) findViewById( R.id.btn_clients );

        btn_clients.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent startClientActivity = new Intent( MainActivity.this, ClientActivity.class );
                startActivity( startClientActivity );
            }
        } );
    }
}
