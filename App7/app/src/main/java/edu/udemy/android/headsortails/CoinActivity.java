package edu.udemy.android.headsortails;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CoinActivity extends AppCompatActivity {

    private Button btn_back;
    private ImageView img_coin;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_coin );

        btn_back = (Button) findViewById( R.id.btn_back );
        img_coin = (ImageView) findViewById( R.id.img_coin );

        Bundle extra = getIntent().getExtras();
        String chosenOption = "";

        if( extra != null ) {
            chosenOption = extra.getString( "result" );
        }

        if( chosenOption.equals( "Head" ) ) {
            img_coin.setImageDrawable( ContextCompat.getDrawable( this, R.drawable.coin_face ) );
        } else if( chosenOption.equals( "Tail" ) ) {
            img_coin.setImageDrawable( ContextCompat.getDrawable( this, R.drawable.coin_value ) );
        }

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity( new Intent( CoinActivity.this, MainActivity.class ) );
            }
        } );
    }
}
